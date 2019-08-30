package java8;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/22 - 23:10
 */
public class TestParallelTest {
    TestParallel demo;

    @Before
    public void setUp(){
        demo = new TestParallel();
    }

    @Test
    public void addByParallel() {
        demo.addByParallel();
    }
}