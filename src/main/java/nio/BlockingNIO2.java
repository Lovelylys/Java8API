package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * @author abs
 * @Date 2019/8/25 - 13:29
 */
public class BlockingNIO2 {
    public void client(){
        SocketChannel sChannel = null;
        FileChannel inChannel = null;
        try {
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9987));
            inChannel = FileChannel.open(Paths.get("e:/1.jpg"), StandardOpenOption.READ);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteBuffer buff = ByteBuffer.allocate(1024);
        try {
            while (inChannel.read(buff) != -1){
                buff.flip();
                sChannel.write(buff);
                buff.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sChannel.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            sChannel.read(buff);
            buff.flip();
            System.out.println(new String(buff.array(),0,buff.limit()));
            buff.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inChannel.close();
            sChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Server(){
        ServerSocketChannel ssChannel = null;
        FileChannel outChannel = null;
        SocketChannel sChannel = null;
        ByteBuffer buff = null;
        try {
            ssChannel = ServerSocketChannel.open();

            ssChannel.bind(new InetSocketAddress(9987));
            outChannel = FileChannel.open(Paths.get("e:/6.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);

            sChannel = ssChannel.accept();
            buff = ByteBuffer.allocate(1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while(sChannel.read(buff) != -1){
                buff.flip();
                outChannel.write(buff);
                buff.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            buff.put("服务端已经接受数据".getBytes());
            buff.flip();
            sChannel.write(buff);
            buff.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outChannel.close();
            sChannel.close();
            ssChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
