package multi.thread.p_r;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class ThreadC extends Thread{
    private Consumer consumer;

    public ThreadC(Consumer consumer){
        this.consumer=consumer;
    }

    @Override
    public void run() {
        while (true){
            consumer.getValue();
        }
    }
}
