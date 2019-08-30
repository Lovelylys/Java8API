package java8;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/24 - 0:32
 */
public class TestLocalTimeDateTest {
    TestLocalTimeDate demo;

    @Before
    public void setUp(){
        demo = new TestLocalTimeDate();
    }

    @Test
    public void test1() {
        demo.test1();
    }

    @Test
    public void test2(){
        demo.test2();
    }

    @Test
    public void test3(){
        demo.test3();
    }

    @Test
    public void test4(){
        demo.test4();
    }

    @Test
    public void test5(){
        demo.test5();
    }

    @Test
    public void test6(){
        demo.test6();
    }
}