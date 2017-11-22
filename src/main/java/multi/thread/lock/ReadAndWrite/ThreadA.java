package multi.thread.lock.ReadAndWrite;

/**
 * Created by ${WangChengYong} on 2017/11/22.
 */
public class ThreadA extends Thread {
    private RWservice service;

    public ThreadA(RWservice service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.read();
    }
}
