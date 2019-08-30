package exer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 这个类是为了测试，体验lamda表达式
 * lamda表达式就是左侧为 参数
 * 右侧 为 方法体
 * 参数 多个用小括号括起来
 * 方法 多条语句则使用大括号括起来，一条不需要，且不需要返回return 标志
 * @author abs
 * @Date 2019/8/18 - 23:47
 */
public class TestLamda {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(20,555.55,"张三"));
        list.add(new Employee(22,666.66,"李四"));
        list.add(new Employee(2,777.77,"王五"));
        list.add(new Employee(30,8888.8,"赵六"));
        list.add(new Employee(18,999.99,"田七"));

        Collections.sort(list,(e1,e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    public String changeStr(String str,MyFunction myFunction){
        return myFunction.getValue(str);
    }

    public Long opLong(Long L1,Long L2,MyFunction2<Long,Long> myFunction2){
        return myFunction2.getValue(L1,L2);
    }
}
