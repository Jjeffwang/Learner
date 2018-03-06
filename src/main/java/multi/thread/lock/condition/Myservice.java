package multi.thread.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ${WangChengYong} on 2017/11/22.
 */
public class Myservice {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void setA() {
        try {
            lock.lock();
            while (hasValue == true) {
                condition.await();
            }
            System.out.println("A");
            hasValue = true;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void setB() {
        try {
            lock.lock();
            while (hasValue == false) {
                condition.await();
            }
            System.out.println("B");
            hasValue = false;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
