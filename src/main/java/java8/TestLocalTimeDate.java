package java8;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.text.DateFormatter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author abs
 * @Date 2019/8/23 - 23:12
 */
public class TestLocalTimeDate {
    //6.ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
    public void test6(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);

        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(zdt);
    }
    public void test7(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }
    //5. DateTimeFormatter : 解析和格式化日期或时间
    public void test5(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime ldt = LocalDateTime.now();

        String strDate = ldt.format(dtf);
        System.out.println(strDate);

        LocalDateTime newLdt = ldt.parse("2019-11-08",dtf);
        System.out.println(newLdt);
    }

    //4. TemporalAdjuster : 时间校正器
    public void test4(){
        LocalDateTime ldt1 = LocalDateTime.now();

        LocalDateTime ldt2 = ldt1.withDayOfMonth(31);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt1.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(ldt3);

        // 实现返回下一个工作日
        LocalDateTime ldt5 = ldt1.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dow = ldt4.getDayOfWeek();

            if(dow.equals(DayOfWeek.FRIDAY)){
                return ldt4.plusDays(3);
            }else if(dow.equals(DayOfWeek.SATURDAY)){
                return ldt4.plusDays(2);
            }else{
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }
    //3.
    //Duration : 用于计算两个“时间”间隔
    //Period : 用于计算两个“日期”间隔
    public void test3(){
        Instant start = Instant.now();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start,end));

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2011,1,1);
        Period period = Period.between(ld2,ld1);
        System.out.println(period.getYears());
    }
    //2. Instant : 时间戳。 （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
    public void test2(){
        Instant ins = Instant.now();
        System.out.println(ins);

        OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        // 从元年开始经过五秒
        Instant ins2 = Instant.ofEpochSecond(5);
        System.out.println(ins2);
    }

    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2016,11,21,10,10,10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt2.plusYears(2);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt3.minusMonths(2);
        System.out.println(ldt4);
    }
}
