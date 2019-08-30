package OjTestJava;

/**
 * @author abs
 * @Date 2019/8/17 - 12:48
 */
public class TestProductAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(producer,"生产者A").start();
        new Thread(consumer,"消费者B").start();
        new Thread(producer,"生产者C").start();
        new Thread(consumer,"消费者D").start();
    }
}
class Clerk{
    private int product;

    public synchronized void set(){ // 生产者方法，如果个数不足了就可以添加，否则跳过输出已满
        while(product >= 1){
            System.out.println("产品已经满了");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 生产 " + ++product);
        this.notifyAll();
    }

    public synchronized void sale(){ // 消费者方法，当个数不足了就提示卖完了，否则继续消费
        while(product <= 0){
            System.out.println("卖完了");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 消费了 " + --product);
        this.notifyAll();
    }
}
class Producer implements Runnable{
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.set();
        }
    }
}
class Consumer implements Runnable{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
