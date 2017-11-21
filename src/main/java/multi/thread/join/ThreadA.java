package multi.thread.join;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class ThreadA extends Thread{

    private ThreadB threadB;
    public ThreadA(ThreadB threadB){
        this.threadB=threadB;
    }

    @Override
    public void run() {
        try{
            synchronized (threadB){
                System.out.println("begin  A    "+Thread.currentThread().getName() + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("end   A   "+Thread.currentThread().getName() + System.currentTimeMillis());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
