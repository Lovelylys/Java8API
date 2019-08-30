package java8;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.LongStream;

/**
 * @author abs
 * @Date 2019/8/22 - 23:07
 */
public class TestParallel {

    public void addByParallel(){

        Instant start =  Instant.now();
        OptionalLong op = LongStream.rangeClosed(0L,100000000000L)
                .parallel()
                .reduce(Long::sum);
        System.out.println(op.getAsLong());

        Instant end =Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
    }
}
