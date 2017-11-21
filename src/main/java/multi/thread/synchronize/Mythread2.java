package multi.thread.synchronize;

/**
 * Created by jeff on 2017/11/14.
 */
public class Mythread2 extends Thread {
    private Task task;

    public Mythread2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommounUtils.beginTime2 = System.currentTimeMillis();
        task.dolongTimeTask();
        CommounUtils.endTime2 = System.currentTimeMillis();
    }
}
