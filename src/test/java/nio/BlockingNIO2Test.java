package nio;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/25 - 13:41
 */
public class BlockingNIO2Test {
    BlockingNIO2 demo;

    @Before
    public void setUp(){
        demo = new BlockingNIO2();
    }
    @Test
    public void client() {
        demo.client();
    }

    @Test
    public void server() {
        demo.Server();
    }
}