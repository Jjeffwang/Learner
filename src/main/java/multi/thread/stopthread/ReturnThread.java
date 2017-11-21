package multi.thread.stopthread;

/**
 * Created by ${WangChengYong} on 2017/11/6.
 */
public class ReturnThread extends Thread {

    @Override
    public void run() {
        while (true) {
            if (this.isInterrupted()) {
                System.out.println("停止了！！！");
                return;
            }
            System.out.println("timer:" + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        try {
            ReturnThread returnThread = new ReturnThread();
            returnThread.start();
            Thread.sleep(1000);
            returnThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
