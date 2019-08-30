package OjTestJava;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 这个类是为了实现三个线程，分别打印ABC，递归地打印十次，每个线程打自己地名字
 *
 * @author abs
 * @Date 2019/8/17 - 19:09
 */
public class TestABCAlternate {
    public static void main(String[] args) {
        AlternateDemo demo = new AlternateDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    demo.printA(i);
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    demo.printB(i);
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    demo.printC(i);
                }
            }
        }, "C").start();
    }
}

class AlternateDemo {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA(int i) {
        try {
            lock.lock();
            if (num != 1) {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 第" + i + "轮");
            num = 2;
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printB(int i) {
        try {
            lock.lock();
            if (num != 2) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 第" + i + "轮");
            num = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printC(int i) {
        try {
            lock.lock();
            if (num != 3) {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 第" + i + "轮");
            System.out.println("---------------");
            num = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}