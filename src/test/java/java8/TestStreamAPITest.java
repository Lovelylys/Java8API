package java8;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/22 - 0:11
 */
public class TestStreamAPITest {
    TestStreamAPI demo;

    @Before
    public void setUp(){
        demo = new TestStreamAPI();
    }

    @Test
    public void testReduce() {
        demo.testReduce();
    }

    @Test
    public void searchForSix() {
        demo.searchForSix();
    }

    @Test
    public void getMax() {
        demo.getMax();
    }

    @Test
    public void getMatch() {
        demo.getMatch();
    }

    @Test
    public void middleOp() {
        demo.middleOp();
    }

    @Test
    public void count() {
        demo.count();
    }

    @Test
    public void collectToCollection() {
        demo.collectToCollection();
    }

    @Test
    public void collectOP() {
        demo.collectOP();
    }

    @Test
    public void collectGroup() {
        demo.collectGroup();
    }

    @Test
    public void partition() {
        demo.partition();
    }

    @Test
    public void reduce() {
        demo.reduce();
    }
}