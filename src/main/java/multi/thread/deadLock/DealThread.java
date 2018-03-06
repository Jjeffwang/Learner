package multi.thread.deadLock;

/**
 * 模拟死锁，使用jdk中jps命令，查询线程id，在使用jstack查看是否出现死锁。
 * <p>
 * <p>
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class DealThread implements Runnable {

    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setFlag(String username) {
        this.username = username;
    }

    public void run() {
        if (username.equals("a")) {
            synchronized (lock1) {
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("lock1>lock2");
                }
            }

        }

        if (username.equals("b")) {
            synchronized (lock2) {
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("");
                }
                synchronized (lock1) {
                    System.out.println("lock2>lock1");
                }
            }

        }
    }


    public static void main(String[] args) {

        try {
            DealThread thread1 = new DealThread();
            thread1.setFlag("a");
            Thread thread = new Thread(thread1);
            thread.start();
            Thread.sleep(100);
            thread1.setFlag("b");
            Thread thread2 = new Thread(thread1);
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
