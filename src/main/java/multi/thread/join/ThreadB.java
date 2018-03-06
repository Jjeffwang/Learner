package multi.thread.join;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class ThreadB extends Thread {

    @Override
    synchronized public void run() {
        try {
            System.out.println("begin   " + Thread.currentThread().getName() + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end   " + Thread.currentThread().getName() + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
