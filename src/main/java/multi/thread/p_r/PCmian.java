package multi.thread.p_r;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class PCmian {

    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Productor productor = new Productor(lock);
        Consumer consumer = new Consumer(lock);
        ThreadP[] threadP = new ThreadP[2];
        ThreadC[] threadC = new ThreadC[2];
        for (int i = 0; i < 2; i++) {
            threadP[i] = new ThreadP(productor);
            threadP[i].setName("生产者" + i + 1);
            threadC[i] = new ThreadC(consumer);
            threadC[i].setName("消费者" + i + 1);
            threadP[i].start();
            threadC[i].start();
        }
        Thread.sleep(5000);
        Thread[] threads = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threads);
        for (int i = 0; i < threads.length; i++) {
            System.out.println(threads[i].getName() + "_" + threads[i].getState());
        }
    }
}
