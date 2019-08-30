package nio;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/25 - 13:17
 */
public class BlockingNIOTest {
    BlockingNIO demo;

    @Before
    public void setUp(){
        demo = new BlockingNIO();
    }

    @Test
    public void client() {
        demo.Client();
    }

    @Test
    public void server() {
        demo.Server();
    }
}