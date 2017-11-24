package multi.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ${WangChengYong} on 2017/11/24.
 */
public class MyCondition {


    private volatile static MyCondition myCondition;

    public static MyCondition getInstance() {

        if (myCondition == null) {
            synchronized (MyCondition.class) {
                if (null == myCondition)
                    myCondition = new MyCondition();
            }
        }
        return myCondition;
    }

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public void waitCondition() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void nofigyCondition() {
        try {
            lock.lock();
            condition.signal();
        } finally {
            lock.unlock();
        }

    }

}
