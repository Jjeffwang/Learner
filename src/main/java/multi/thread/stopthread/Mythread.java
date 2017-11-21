package multi.thread.stopthread;

/**
 * Created by ${WangChengYong} on 2017/11/6.
 */
public class Mythread extends Thread {

    @Override
    public void run() {
        super.run();

        for (int i = 0; i <= 50000; i++) {
            if (this.interrupted()) {
                System.out.println("interrupt!!!!!");
                break;
            }
            System.out.println("i=" + i + 1);
        }
        System.out.println("unInterrupted!!!!!!");
    }

    public static void main(String[] args) {
        try {
            Mythread mythread = new Mythread();
            mythread.start();
            Thread.sleep(1000);
            mythread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
