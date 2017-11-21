package multi.thread.stopthread;

/**
 * Created by ${WangChengYong} on 2017/11/6.
 */
public class SleepThread extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("begin");
            Thread.sleep(200000);
            System.out.println("end");
        } catch (InterruptedException e) {
            System.out.println("sleep end !!!进入catch");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            SleepThread sleepThread = new SleepThread();
            sleepThread.start();

            Thread.sleep(200);
            sleepThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
    }
}
