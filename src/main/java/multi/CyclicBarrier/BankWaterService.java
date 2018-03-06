package multi.CyclicBarrier;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 银行流水处理服务类
 * Created by ${WangChengYong} on 2017/12/4.
 */
public class BankWaterService implements Runnable {

    /**
     * =
     * 创建4个屏障，处理完之后执行当前类的run方法
     */
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4, this);
    /**
     * 启动4个线程
     */
    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String, Integer> countMap = new ConcurrentHashMap<String, Integer>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    countMap.put(Thread.currentThread().getName(), 1);
                    try {
                        //4个线程计算4次，每计算完成一次插入一个屏障
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String, Integer> sheet : countMap.entrySet()) {
            result += sheet.getValue();
        }
        countMap.put("result", result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }

}
