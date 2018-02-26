package distributeLock.redis;

/**
 * Created by ${WangChengYong} on 2018/2/26.
 */
public class MyThread extends Thread {

    private Service service;

    public MyThread(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.secondKill();
    }
}
