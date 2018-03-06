package multi.thread.lock.condition;

/**
 * Created by ${WangChengYong} on 2017/11/22.
 */
public class ThreadA extends Thread {

    private Myservice myservice;

    public ThreadA(Myservice myservice) {
        this.myservice = myservice;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            myservice.setA();
        }
    }
}
