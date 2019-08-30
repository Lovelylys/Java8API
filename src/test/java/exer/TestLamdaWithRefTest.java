package exer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/21 - 7:05
 */
public class TestLamdaWithRefTest {
    TestLamdaWithRef demo;

    @Before
    public void setUp(){
        demo = new TestLamdaWithRef();
    }
    @Test
    public void changeStr() {
        demo.changeStr();
    }

    @Test
    public void calculateSum() {
        demo.calculateSum(2L,33L,Math::addExact);
        demo.calculateSum(2L,33L,Math::multiplyExact);
    }
}