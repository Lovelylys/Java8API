package OjTestJava;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author abs
 * @Date 2019/8/17 - 23:59
 */
public class TestScheduleExcutorPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Future<Integer> future = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + " " + num);
                    return num;
                }
            },1,TimeUnit.SECONDS);
            System.out.println(future.get());
        }
        pool.shutdown();
    }
}
