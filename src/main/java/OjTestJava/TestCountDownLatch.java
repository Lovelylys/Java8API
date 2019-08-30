package OjTestJava;

import java.util.concurrent.CountDownLatch;

/*
 * CountDownLatch ：闭锁，在完成某些运算是，只有其他所有线程的运算全部完成，当前运算才继续执行
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        CountDownLatchDemo demo = new CountDownLatchDemo(countDownLatch);

        Long startTime = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            new Thread(demo).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();
        System.out.println("耗费时间为： " + (endTime - startTime));
    }
}
class CountDownLatchDemo implements Runnable{
    private CountDownLatch countDownLatch;

    public CountDownLatchDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 45; i++) {
                if(i % 2 ==0){
                    System.out.println(i);
                }
            }
        } finally {
            countDownLatch.countDown();
        }
    }
}
