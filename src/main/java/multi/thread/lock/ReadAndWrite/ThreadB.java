package multi.thread.lock.ReadAndWrite;

/**
 * Created by ${WangChengYong} on 2017/11/22.
 */
public class ThreadB extends Thread {
    private RWservice service;

    public ThreadB(RWservice service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.write();
    }
}
