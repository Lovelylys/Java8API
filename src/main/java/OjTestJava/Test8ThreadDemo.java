package OjTestJava;

/**
 * @author abs
 * @Date 2019/8/17 - 21:05
 */
public class Test8ThreadDemo {
    public static void main(String[] args) {
        Thread8Demo demo = new Thread8Demo();
        Thread8Demo demo2 = new Thread8Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.getOne();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo2.getTwo();
//                demo.getTwo();
            }
        }).start();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                demo.getThree();
            }
        }).start();*/
    }
}
class Thread8Demo{

    public static synchronized void getOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }
    public static synchronized void getTwo(){
        System.out.println("two");
    }
    public void getThree(){
        System.out.println("three");
    }
}