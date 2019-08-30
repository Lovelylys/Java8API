package OjTestJava;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/14 - 23:29
 */
public class AddTest {
    Add add;

    @Before
    public void setUp(){
         add = new Add();
    }

    @Test
    public void add() {
        assertEquals(4,add.add(2,2));
    }
}