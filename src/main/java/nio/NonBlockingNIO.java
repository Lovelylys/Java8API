package nio;

import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author abs
 * @Date 2019/8/25 - 13:48
 */
public class NonBlockingNIO {

    public void client(){
        SocketChannel sChannel = null;
        try {
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9987));
            sChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        ByteBuffer buff = ByteBuffer.allocate(1024);
//        while(scanner.hasNext()){
//            String input = scanner.nextLine();
            buff.put((LocalDateTime.now().toString()).getBytes());
            buff.flip();
            try {
                sChannel.write(buff);
            } catch (IOException e) {
                e.printStackTrace();
            }
            buff.clear();
//        }

        /*try {
            sChannel.read(buff);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buff.flip();
        System.out.println(new String(buff.array(),0,buff.limit()));*/

        try {
            sChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void server(){
        // 创建连接，修改为非阻塞模式，绑定端口，获取请求连接，获取请求连接中的内容到字节缓冲区，打印
        // 多路选择器的控制，绑定了端口后，服务端连接注册到选择器中，选择器中迭代获取选择键，如果为可接受状态
        // 服务端连接获取连接，然后连接注册到选择器中，选择器继续获取到可输入的选择键，然后输出信息，
        ServerSocketChannel ssChannel = null;
        Selector selector = null;
        try {
            ssChannel = ServerSocketChannel.open();
            ssChannel.configureBlocking(false);

            ssChannel.bind(new InetSocketAddress(9987));

            selector = Selector.open();
            // 指定监听事件，监听是否可以接收
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while(selector.select() > 0){
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while(it.hasNext()){
                    SelectionKey key = it.next();
                    if(key.isAcceptable()){
                        SocketChannel sChannel = ssChannel.accept();
                        // 也要设置非阻塞模式
                        sChannel.configureBlocking(false);
                        sChannel.register(selector,SelectionKey.OP_READ);
                    }else if(key.isReadable()){
                        ByteBuffer buff = ByteBuffer.allocate(1024);
                        SocketChannel sChannel = (SocketChannel) key.channel();
                        int len = 0;

                        while((len = sChannel.read(buff)) > 0){
                            buff.flip();
                            System.out.println(new String(buff.array(),0,buff.limit()));
                            buff.clear();
                        }
                    }
                    // 用完需要销毁selectionKey
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
