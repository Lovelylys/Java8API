package nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author abs
 * @Date 2019/8/25 - 16:54
 */
public class PiepNIO {

    @Test
    public void piep() throws IOException {
        Pipe pipe = Pipe.open();

        ByteBuffer buff = ByteBuffer.allocate(1024);

        Pipe.SinkChannel sinkChannel = pipe.sink();
        buff.put("通过管道发送数据".getBytes());
        buff.flip();
        sinkChannel.write(buff);

        Pipe.SourceChannel sourceChannel = pipe.source();
        buff.flip();
        sourceChannel.read(buff);
        System.out.println(new String(buff.array(),0,buff.limit()));

        sourceChannel.close();
        sinkChannel.close();
    }
}
