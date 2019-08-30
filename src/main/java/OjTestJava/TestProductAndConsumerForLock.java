package OjTestJava;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author abs
 * @Date 2019/8/17 - 17:25
 */
public class TestProductAndConsumerForLock {
    public static void main(String[] args) {
        Cleark cleark = new Cleark();
        Producer2 producer = new Producer2(cleark);
        Consumer2 consumer = new Consumer2(cleark);
        new Thread(producer,"生产者A").start();
        new Thread(producer,"生产者C").start();
        new Thread(consumer,"消费者B").start();
        new Thread(consumer,"消费者D").start();
    }
}

class Cleark {
    private int product;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void get() {
        try {
            lock.lock();
            while (product >= 1) {
                System.out.println("产品已满");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 生产 " + ++product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void sale(){
        try {
            lock.lock();
            while(product <= 0){
                System.out.println("卖完了");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 消费了 " + --product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
class Producer2 implements Runnable{
    private Cleark cleark;

    public Producer2(Cleark cleark) {
        this.cleark = cleark;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cleark.get();
        }
    }
}
class Consumer2 implements Runnable{
    private Cleark cleark;

    public Consumer2(Cleark cleark) {
        this.cleark = cleark;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            cleark.sale();
        }
    }
}