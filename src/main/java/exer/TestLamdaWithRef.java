package exer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author abs
 * @Date 2019/8/21 - 7:00
 */
public class TestLamdaWithRef {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(20,555.55,"张三"));
        list.add(new Employee(22,666.66,"李四"));
        list.add(new Employee(2,777.77,"王五"));
        list.add(new Employee(30,8888.8,"赵六"));
        list.add(new Employee(18,999.99,"田七"));

    }

    public void changeStr(){
        Function<String,String> fun2 = String::toUpperCase;
        String result = fun2.apply("strasd");
        System.out.println(result);
    }

    public void calculateSum(Long L1,Long L2,BiFunction<Long,Long,Long> fun){
        System.out.println(fun.apply(L1, L2));
    }
}
