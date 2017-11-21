package multi.thread.p_r;


/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class Productor {

    private String lock;

    public Productor(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                while (!ValueObject.value.equals("")) {
                    System.out.println("生产者" + Thread.currentThread().getName() + " waiting");
                    lock.wait();
                }
                System.out.println("生产者" + Thread.currentThread().getName() + " running");
                String value = System.currentTimeMillis() + "_";
                ValueObject.value = value;
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
