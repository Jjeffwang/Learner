package multi.thread.p_r;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class Consumer {

    private String lock;

    public Consumer(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                while (ValueObject.value.equals("")) {
                    System.out.println("消费者" + Thread.currentThread().getName() + " waiting");
                    lock.wait();
                }
                System.out.println("消费者" + Thread.currentThread().getName() + " running");
                ValueObject.value = "";
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


