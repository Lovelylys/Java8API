package OjTestJava;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author abs
 * @Date 2019/8/17 - 12:25
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket,"售票窗口1").start();
        new Thread(ticket,"售票窗口2").start();
        new Thread(ticket,"售票窗口3").start();
    }
}
class Ticket implements Runnable{
    private int ticket = 100;
    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        while(true){
            lock.lock();
            try {
                if(ticket > 0){
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() +" 完成售票，余票："+--ticket);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
