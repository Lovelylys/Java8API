package OjTestJava;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author abs
 * @Date 2019/8/18 - 0:11
 */
public class TestForkJoinPool {
    public static void main(String[] args) {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinSumCalculator = new ForkJoinSumCalculator(0L,100000000L);
        Long sum = pool.invoke(forkJoinSumCalculator);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("消耗的时间为： "+ Duration.between(start,end).toMillis());
    }
}
class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private static final long serialVersionUID = -259195479995561737L;
    private long start;
    private long end;
    private final Long THURSHOLD = 1000L;

    public ForkJoinSumCalculator(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if(length <= THURSHOLD){
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else {
            long mid = (end+start)/2;

            ForkJoinSumCalculator left = new ForkJoinSumCalculator(start,mid);
            left.fork();

            ForkJoinSumCalculator right = new ForkJoinSumCalculator(mid+1,end);
            right.fork();

            return left.join()+right.join();
        }
    }
}
