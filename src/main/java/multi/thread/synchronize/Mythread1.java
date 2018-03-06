package multi.thread.synchronize;

/**
 * Created by jeff on 2017/11/14.
 */
public class Mythread1 extends Thread {

    private Task task;

    public Mythread1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommounUtils.beginTime1 = System.currentTimeMillis();
        task.dolongTimeTask();
        CommounUtils.endTime1 = System.currentTimeMillis();
    }
}
