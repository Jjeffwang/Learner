package guava.currentLimiting;


import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/21 0021 下午 3:58
 * Description:
 */
public class CurrentLimitDemo {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1000);
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            list.add(new myTask(i));
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (Runnable task : list) {
            System.out.println("等待时间："+rateLimiter.acquire());
            executorService.execute(task);
        }

    }

    private static class myTask implements Runnable {

        private int id;

        public myTask(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println(id);
        }
    }
}
