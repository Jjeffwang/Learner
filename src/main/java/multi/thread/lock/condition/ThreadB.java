package multi.thread.lock.condition;

/**
 * Created by ${WangChengYong} on 2017/11/22.
 */
public class ThreadB extends Thread {

    private Myservice myservice;

    public ThreadB(Myservice myservice){
        this.myservice=myservice;
    }

    @Override
    public void run() {
        for(int i=0;i<=10;i++){
            myservice.setB();
        }
    }
}
