package multi.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 虽然有30个线程在执行，但是只允许10个并发执行
 * Created by jeff on 2017/12/4.
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;
    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        semaphore.acquire();
                        System.out.println("save data over");
                        Thread.sleep(3000);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }

}