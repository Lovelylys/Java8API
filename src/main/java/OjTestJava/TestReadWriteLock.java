package OjTestJava;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author abs
 * @Date 2019/8/17 - 20:49
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteDemo demo = new ReadWriteDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.write((int)Math.random() * 232);
            }
        },"write").start();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.read();
                }
            },"read").start();
        }
    }
}
class ReadWriteDemo{
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Integer number;

    public void read(){
        try{
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " 读 " + number);
        }finally {
            lock.readLock().unlock();
        }
    }

    public void write(Integer number){
        try {
            lock.writeLock().lock();
            this.number = number;
            System.out.println(Thread.currentThread().getName() + " 写 " + this.number);
        }finally {
            lock.writeLock().unlock();
        }
    }
}
