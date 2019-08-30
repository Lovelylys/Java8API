package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/*
 * 一、使用 NIO 完成网络通信的三个核心：
 *
 * 1. 通道（Channel）：负责连接
 *
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 *
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 *
 * 2. 缓冲区（Buffer）：负责数据的存取
 *
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 */
public class BlockingNIO {
    public void Client(){
        SocketChannel sChannel = null;
        try {
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9987));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileChannel inChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("e:/1.jpg"), StandardOpenOption.READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteBuffer buff = ByteBuffer.allocate(1024);

        try {
            while(inChannel.read(buff) != -1){
                buff.flip();
                sChannel.write(buff);
                buff.clear();
            }
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
        try {
            ssChannel = ServerSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 绑定连接
        try {
            ssChannel.bind(new InetSocketAddress(9987));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileChannel outChannel = null;
        SocketChannel sChannel = null;
        try {
            outChannel = FileChannel.open(Paths.get("e:/5.jpg"), StandardOpenOption.CREATE,StandardOpenOption.WRITE);

            sChannel = ssChannel.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ByteBuffer buff =ByteBuffer.allocate(1024);
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
            outChannel.close();
            sChannel.close();
            ssChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
