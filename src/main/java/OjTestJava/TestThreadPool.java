package OjTestJava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 三、工具类 : Executors
 * ExecutorService newFixedThreadPool() : 创建固定大小的线程池
 * ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 * ExecutorService newSingleThreadExecutor() : 创建单个线程池。线程池中只有一个线程
 *
 * ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务。
 */
public class TestThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        /*ThreadPoolDemo demo = new ThreadPoolDemo();
        for (int i = 0; i < 10; i++) {
            pool.submit(demo);
        }
        pool.shutdown();*/
        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.submit(new Callable<Integer>() {
                int sum = 0;
                @Override
                public Integer call() throws Exception {
                    for (int j = 0; j <= 100; j++) {
                        sum += j;
                    }
                    return sum;
                }
            });
            list.add(future);
        }
        pool.shutdown();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).get());
        }
    }
}
class ThreadPoolDemo implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

