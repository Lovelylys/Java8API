package nio;

import sun.misc.CharacterEncoder;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;

/*
 * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 * 	java.nio.channels.Channel 接口：
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 *
 * 三、获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 		本地 IO：
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 *
 * 		网络IO：
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 *
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 *
 */
public class ChanelAndBuffer {
    // 关于编解码
    public void code(){
        Charset cs1 = Charset.forName("GBK");
        CharsetEncoder en = cs1.newEncoder();
        CharsetDecoder de = cs1.newDecoder();

        CharBuffer chars = CharBuffer.allocate(1024);
        chars.put("李燕珊你好呀");

        // 编码
        chars.flip();
        ByteBuffer buffs = null;
        try {
            buffs = en.encode(chars);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < buffs.limit(); i++) {
            System.out.println(buffs.get());
        }

        // 解码
        buffs.flip();
        CharBuffer  chars2 = null;
        try {
            chars2 = de.decode(buffs);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        System.out.println(chars2.toString());
    }
    // 分散，聚合读写
    public void forkJoin() {
        RandomAccessFile raf = null;
        RandomAccessFile outRaf = null;
        try {
            raf = new RandomAccessFile("e:/1.jpg","rw");
            outRaf = new RandomAccessFile("e:/4.jpg","rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        inChannel = raf.getChannel();
        outChannel = outRaf.getChannel();

        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);

        ByteBuffer[] bufs = {buffer1,buffer2};

        try {
            while(inChannel.read(bufs) != -1){
                for (int i = 0; i < bufs.length; i++) {
                    bufs[i].flip();
                    outChannel.write(bufs[i]);
                    bufs[i].clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outChannel.close();
                inChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 直接缓冲区 通道之间的直接传输
    public void directBuffer2() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("e:/1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("e:/3.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);
//        outChannel.transferFrom(inChannel,0,inChannel.size());
    }

    public void directBuffer() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("e:/ROBINSON.mp3"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("e:/ROBINSON2.mp3"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        MappedByteBuffer intBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] bytes = new byte[intBuffer.limit()];
        intBuffer.get(bytes);
        outBuffer.put(bytes);

        inChannel.close();
        outChannel.close();
    }

    // 先测试一下非直接缓冲区的功能
    public void nonDirect() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("e:/1.jpg");
            fos = new FileOutputStream("e:/2.jpg");
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (inChannel.read(buffer) != -1) {
                buffer.flip();

                outChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (outChannel != null) {
                outChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inChannel != null) {
            try {
                inChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
