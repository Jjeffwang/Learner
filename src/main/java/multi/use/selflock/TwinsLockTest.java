package multi.use.selflock;


import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * Created by jeff on 2017/11/27.
 */
public class TwinsLockTest {

    @Test
    public void test(){
        final Lock lock=new TwinsLock();
        class Worker extends Thread{

            @Override
            public void run() {
                while(true){
                    lock.lock();try{
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                        //共停了2秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }
        for(int i=0;i<10;i++){
            Worker worker=new Worker();
            worker.setDaemon(true);
            worker.start();
        }

        for(int i=0 ;i<10;i++){
            try {
                Thread.sleep(1000);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
