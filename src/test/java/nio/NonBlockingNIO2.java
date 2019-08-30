package nio;

import org.junit.Test;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author abs
 * @Date 2019/8/25 - 16:40
 */
public class NonBlockingNIO2 {
    @Test
    public void send() throws IOException {
        DatagramChannel inChannel = DatagramChannel.open();
        inChannel.configureBlocking(false);

        ByteBuffer buff =ByteBuffer.allocate(1024);
        Scanner scanner  = new Scanner(System.in);
        while (scanner.hasNext()){
            String input = scanner.nextLine();
            buff.put((LocalDateTime.now().toString() + input).getBytes());
            buff.flip();
            inChannel.send(buff,new InetSocketAddress("127.0.0.1",9987));
            buff.clear();
        }
        inChannel.close();
    }

    @Test
    public void receive() throws IOException{
        DatagramChannel outChannel = DatagramChannel.open();
        outChannel.configureBlocking(false);

        ByteBuffer buff = ByteBuffer.allocate(1024);
        outChannel.bind(new InetSocketAddress(9987));

        Selector selector = Selector.open();

        outChannel.register(selector,SelectionKey.OP_READ);

        while(selector.select() > 0){
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                if(key.isReadable()){
                    outChannel.receive(buff);
                    buff.flip();
                    System.out.println(new String(buff.array(),0,buff.limit()));
                    buff.clear();
                }
            }
            it.remove();
        }

    }
}
