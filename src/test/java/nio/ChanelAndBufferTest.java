package nio;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/25 - 9:45
 */
public class ChanelAndBufferTest {
    ChanelAndBuffer demo;

    @Before
    public void setUp(){
        demo = new ChanelAndBuffer();
    }

    @Test
    public void nonDirect() {
        demo.nonDirect();
    }

    @Test
    public void testDirectBuffer() throws IOException {
        demo.directBuffer();
    }

    @Test
    public void testDirectBuffer2() throws IOException {
        demo.directBuffer2();
    }

    @Test
    public void  testforkJoin(){
        demo.forkJoin();
    }

    @Test
    public void code(){
        demo.code();
    }
}