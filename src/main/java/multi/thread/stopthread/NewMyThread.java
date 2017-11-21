package multi.thread.stopthread;

/**
 * Created by ${WangChengYong} on 2017/11/6.
 */
public class NewMyThread extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i <= 50000; i++) {
                if (this.interrupted()) {
                    System.out.println("interrupt!!!!!");
                    throw new InterruptedException();
                }
                System.out.println("i=" + i + 1);
            }
            System.out.println("unInterrupted!!!!!!");
        } catch (InterruptedException e) {
            System.out.println("new mythread catch......");
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        try {
            NewMyThread myThread = new NewMyThread();
            myThread.start();
            myThread.sleep(1000);
            myThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
