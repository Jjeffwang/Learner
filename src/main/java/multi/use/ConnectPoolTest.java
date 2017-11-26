package multi.use;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jeff on 2017/11/26.
 */
public class ConnectPoolTest {

    static ConnectionPool pool=new ConnectionPool(10);
    //保证所有connectionRunner能够同开始
    static CountDownLatch start=new CountDownLatch(1);
    //main线程将会等待所有connectionRunner结束后才能将继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {

        //线程数量
        int threadCount=500;
        end=new CountDownLatch(threadCount);
        int count=20;
        AtomicInteger got=new AtomicInteger();
        AtomicInteger nogot=new AtomicInteger();
        for(int i=0;i<threadCount;i++){
            Thread thread=new Thread(new ConnectionRunner(count,got,nogot),"ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke  "+threadCount*count);
        System.out.println("got connection  "+got);
        System.out.println("nogot connection  "+nogot);
    }
    static class ConnectionRunner implements Runnable{

        int count;
        AtomicInteger got;
        AtomicInteger nogot;

        public ConnectionRunner(int count,AtomicInteger got,AtomicInteger nogot){
            this.count=count;
            this.got=got;
            this.nogot=nogot;
        }

        @Override
        public void run() {

            try {
                start.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (count>0){
                try {
                    Connection connection=pool.fetchConnetion(1000);
                    if(connection!=null){
                        try {
                            connection.createStatement();
                            connection.commit();
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else {
                        nogot.incrementAndGet();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    count --;
                }
            }
            end.countDown();
        }
    }
}
