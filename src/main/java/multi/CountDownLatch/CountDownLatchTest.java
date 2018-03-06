package multi.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ${WangChengYong} on 2017/12/4.
 */
public class CountDownLatchTest {

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                countDownLatch.countDown();
                System.out.println("2");
                countDownLatch.countDown();
            }
        }).start();
        //await()会阻塞当前线程直到CountDownLatch中计数为0；
        countDownLatch.await();
        System.out.println("3");
    }
}
