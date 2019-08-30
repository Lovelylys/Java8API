package OjTestJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * CopyOnWriteArrayList/CopyOnWriteArraySet : “写入并复制”
 * 注意：添加操作多时，效率低，因为每次添加时都会进行复制，开销非常的大。并发迭代操作多时可以选择。
 */
public class TestCopyOnWriteArray {
    public static void main(String[] args) {
        HelloDemo helloDemo = new HelloDemo();
        for (int i = 0; i < 1; i++) {
            new Thread(helloDemo).start();
        }
    }
}
class HelloDemo implements Runnable{
//    private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
//    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
      private static List<String> list = new ArrayList<>();
    static{
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }
    @Override
    public void run() {
        Iterator<String> iterable = list.iterator();
        while(iterable.hasNext()){
            System.out.println(iterable.next());
            list.remove("CC");
        }
    }
}
