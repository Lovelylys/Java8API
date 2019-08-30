package nio;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/25 - 14:08
 */
public class NonBlockingNIOTest {
    NonBlockingNIO demo;

    @Before
    public void setUp(){
        demo = new NonBlockingNIO();
    }

    @Test
    public void client() {
        demo.client();
    }

    @Test
    public void server() throws IOException {
        demo.server();
    }
}