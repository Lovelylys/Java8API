package java8;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/23 - 22:55
 */
public class TestOptionalTest {
    TestOptional demo;

    @Before
    public void setUp(){
        demo = new TestOptional();
    }

    @Test
    public void testOf() {
        demo.testOf();
    }

    @Test
    public void testEmpty(){
        demo.testEmpty();
    }

    @Test
    public void testOfNullable(){
        demo.testOfNullable();
    }
}