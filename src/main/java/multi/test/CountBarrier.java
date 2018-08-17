package multi.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/13 0013 下午 5:18
 * Description:
 */
public class CountBarrier {

    private static final int THREAD_COUNT_NUM = 7;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("7人分队集结，同事出发，分头寻找龙珠");
                findDragon();
            }
        });

        for (int i = 1; i <= THREAD_COUNT_NUM; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println("召集第" + index + "分队成员");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * 这里也可以用CyclicBarrier
     */
    public static void findDragon() {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT_NUM);
        try {
            for (int i = 1; i <= THREAD_COUNT_NUM; i++) {
                int index = i;
                new Thread(() -> {
                    System.out.println("第" + index + "颗龙珠已经收集齐");
                    countDownLatch.countDown();
                }).start();

            }
            countDownLatch.await();
            System.out.println("召唤神龙");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
