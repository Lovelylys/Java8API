package exer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/19 - 0:03
 */
public class TestLamdaTest {
    TestLamda lamda;

    @Before
    public void setUp(){
        lamda = new TestLamda();
    }

    @After
    public void tearDown(){
        lamda = null;
    }
    @Test
    public void changeStr() {
        System.out.println(lamda.changeStr("STR",x -> x.toLowerCase()));
        assertEquals("str",lamda.changeStr("STR",x -> x.toLowerCase()));
        System.out.println(lamda.changeStr("你爱李燕珊",x -> x.substring(1,5)));
    }

    @Test
    public void opLong() {
        System.out.println(lamda.opLong(100L,2000L,(x,y) -> x+y));
        System.out.println(lamda.opLong(100L,2000L,(x,y) -> x*y));
    }
}