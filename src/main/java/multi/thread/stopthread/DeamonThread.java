package multi.thread.stopthread;

/**
 * Created by ${WangChengYong} on 2017/11/6.
 */
public class DeamonThread extends Thread {

    private int i = 0;

    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i=" + (i));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            DeamonThread deamonThread = new DeamonThread();
            deamonThread.setDaemon(true);
            deamonThread.start();

            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
