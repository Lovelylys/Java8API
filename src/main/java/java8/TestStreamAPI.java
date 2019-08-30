package java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java8.Employee.Status;
import org.junit.jupiter.api.Test;

import static java8.Employee.Status;

/**
 * @author abs
 * @Date 2019/8/21 - 23:57
 */
public class TestStreamAPI {
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );
    ;

    public void testReduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("--------------------");

        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    //需求：搜索名字中 “六” 出现的次数
    public void searchForSix() {
        Optional<Integer> sum = emps.stream()
                .map(Employee::getName)
                .flatMap(TestStreamAPI::filterCharater)
                .map(c -> {
                    if (c == '六') {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .reduce(Integer::sum);
        System.out.println(sum.get());
    }

    public static Stream<Character> filterCharater(String name) {
        List<Character> list = new ArrayList<>();
        for (Character c : name.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    public void getMax() {
        Optional<Employee> op = emps.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(op.get());
    }

    public void getMatch() {
        boolean b = emps.stream()
                .allMatch(e1 -> e1.getStatus().equals(Status.FREE));
        System.out.println(b);
    }

    public void middleOp() {
        Optional<Employee> em = emps.stream()
                .filter(e -> e.getStatus().equals(Status.FREE))
                .findAny();
        System.out.println(em.get());
    }

    public void count() {
        long count = emps.stream()
                .filter(e -> e.getStatus().equals(Status.BUSY))
                .count();
        System.out.println(count);
    }

    public void collectToCollection() {
        HashSet<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        set.forEach(System.out::println);
    }

    public void collectOP() {
        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
        System.out.println("===========================");
        DoubleSummaryStatistics statics = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(statics.getMax());
    }

    public void collectGroup() {
        Map<Status,Map<String,List<Employee>>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if(e.getAge() >= 60){
                        return "老年";
                    }else if(e.getAge() >= 30){
                        return "中年";
                    }else {
                        return "青年";
                    }
                })));
        System.out.println(map);

        Map<Status,List<Employee>> map2 = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
    }
    public void partition(){
        Map<Boolean,List<Employee>> map =emps.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 1000));
        System.out.println(map);
    }

    public void reduce(){
        Optional<Double> op =emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(Double::sum));
        System.out.println(op.get());
    }
}
