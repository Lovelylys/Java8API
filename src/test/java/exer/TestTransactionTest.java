package exer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/22 - 7:34
 */
public class TestTransactionTest {
    TestTransaction demo;

    @Before
    public void setUp(){
        demo = new TestTransaction();
    }
    @Test
    public void testPow() {
        demo.testPow();
    }
    @Test
    public void testCountEm(){
        demo.countEm();
    }

    @Test
    public void testSorted2011(){
        demo.testSorted2011();
    }

    @Test
    public void testShowCities(){
        demo.showCities();
    }

    @Test
    public void testSortedTrader(){
        demo.sortedTrader();
    }

    @Test
    public void testsortedName(){
        demo.sortedName();
    }
    @Test
    public void testfindMilan(){
        demo.findMilan();
    }
    @Test
    public void testprintCambridgeValue(){
        demo.printCambridgeValue();
    }
    @Test
    public void testmaxValue(){
        demo.maxValue();
    }

    @Test
    public void testminValue(){
        demo.minValue();
    }
}