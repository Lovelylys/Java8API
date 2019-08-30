package OjTestJava;


import java.util.concurrent.atomic.AtomicInteger;

//* 二、原子变量：在 java.util.concurrent.atomic 包下提供了一些原子变量。
//        * 		1. volatile 保证内存可见性
//        * 		2. CAS（Compare-And-Swap） 算法保证数据变量的原子性
//        * 			CAS 算法是硬件对于并发操作的支持
//        * 			CAS 包含了三个操作数：
//        * 			①内存值  V
//        * 			②预估值  A
//        * 			③更新值  B
//        * 			当且仅当 V == A 时， V = B; 否则，不会执行任何操作。
//        */
public class TestAtomic {
    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }
    }
}
class AtomicDemo implements Runnable{
//    private volatile int seriaNumber = 0;
    private AtomicInteger seriaNumber = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getSeriaNumber());
    }

    public int getSeriaNumber() {
        return seriaNumber.incrementAndGet();
    }
}
