package interview.CandP;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/28 0028 下午 3:01
 * Description:
 */
public class ConsumerThread extends Thread{

    private Consumer consumer;

    public ConsumerThread(Consumer consumer){
        this.consumer=consumer;
    }

    @Override
    public void run() {
        while (true){
            consumer.getValue();
        }
    }
}
