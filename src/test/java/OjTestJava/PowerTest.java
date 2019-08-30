package OjTestJava;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author abs
 * @Date 2019/8/13 - 23:44
 */
public class PowerTest {
    Power power = null;

    @BeforeClass
    public static void setUpBeforeClass(){
        System.out.println("set up before class");
    }
    @AfterClass
    public static void tearDownAfterClass(){
        System.out.println("tear down after class");
    }
    @Before
    public void setUp(){
        power = new Power();
        System.out.println("set up");
    }

    @After
    public void tearDown(){
        power = null;
        System.out.println("tear down");
    }

    @Test
    public void power() {
        assertEquals(8,new Power().Power(2,3),0.00);
    }

    @Test
    public void power2() {
        assertEquals(27,new Power().Power(3,3),0.00);
    }
}