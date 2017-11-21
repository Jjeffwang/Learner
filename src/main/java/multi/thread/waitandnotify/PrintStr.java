package multi.thread.waitandnotify;

/**
 * Created by ${WangChengYong} on 2017/11/17.
 */
public class PrintStr {

    volatile private boolean flag = false;

    synchronized public void printA() {
        try {
            while (flag == true) {
                wait();
            }
            System.out.println("A");
            flag = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void printB() {
        try {
            while (flag == false) {
                wait();
            }
            System.out.println("B");
            flag = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
