package OjTestJava;

/**
 * @author abs
 * @Date 2019/8/15 - 23:35
 */
public class TestVolatile2 {
    public static void main(String[] args) {
        ThreadDemo2 threadDemo2 = new ThreadDemo2();
        new Thread(threadDemo2).start();
        while (true){
            if(threadDemo2.isFlag()){
                System.out.println("break....");
                break;
            }
        }
    }
}

class ThreadDemo2 implements Runnable {
    private boolean flag = false;

    @Override
    public void run() {
        flag = true;
        System.out.println("flag is " + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
