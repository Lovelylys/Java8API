package exer;

import java8.Employee;
import java8.TestStreamAPI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

/**
 * @author abs
 * @Date 2019/8/22 - 7:24
 */
public class TestTransaction {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    List<java8.Employee> emps = Arrays.asList(
            new java8.Employee(102, "李四", 79, 6666.66, java8.Employee.Status.BUSY),
            new java8.Employee(101, "张三", 18, 9999.99, java8.Employee.Status.FREE),
            new java8.Employee(103, "王五", 28, 3333.33, java8.Employee.Status.VOCATION),
            new java8.Employee(104, "赵六", 8, 7777.77, java8.Employee.Status.BUSY),
            new java8.Employee(104, "赵六", 8, 7777.77, java8.Employee.Status.FREE),
            new java8.Employee(104, "赵六", 8, 7777.77, java8.Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    public void testPow(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.stream()
                .map(e -> e*e)
                .forEach(System.out::println);

    }
    public void countEm(){
        Optional<Integer> op = emps.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(op.get());
    }
    //1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
    public void testSorted2011(){
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted((t1,t2) -> Double.compare(t1.getValue(),t2.getValue()))
                .forEach(System.out::println);
    }
    //2. 交易员都在哪些不同的城市工作过？
    public void showCities(){
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);
    }
    //3. 查找所有来自剑桥的交易员，并按姓名排序
    public void sortedTrader(){
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted((t1,t2) -> t1.getName().compareTo(t2.getName()))
                .distinct()
                .forEach(System.out::println);
    }

    //4. 返回所有交易员的姓名字符串，按字母顺序排序
    public void sortedName(){
        Optional<String> op = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted(String::compareTo)
                .reduce(String::concat);
        System.out.println(op.get());

        System.out.println("=======================");
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .flatMap(TestStreamAPI::filterCharater)
                .sorted((s1,s2) -> s1.compareTo(s2))
                .forEach(System.out::println);
    }

    //5. 有没有交易员是在米兰工作的？
    public void findMilan(){
        boolean b = transactions.stream()
                .anyMatch(t ->t.getTrader().getCity().equals("Milan"));
        System.out.println(b);
    }

    //6. 打印生活在剑桥的交易员的所有交易额
    public void printCambridgeValue(){
        Optional<Integer> op = transactions.stream()
                .filter(e -> e.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(op.get());
    }
    //7. 所有交易中，最高的交易额是多少
    public void maxValue(){
        Optional<Integer> value = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);
        System.out.println(value.get());
    }
    //8. 找到交易额最小的交易
    public void minValue(){
        Optional<Transaction> t =transactions.stream()
                .collect(Collectors.minBy(comparing(Transaction::getValue)));
        System.out.println(t.get().getValue());
    }
}
