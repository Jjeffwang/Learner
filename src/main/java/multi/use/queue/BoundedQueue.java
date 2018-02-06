package multi.use.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jeff on 2017/11/29.
 * <p>
 * 自定义有界队列ß
 */
public class BoundedQueue<T> {

    private Object[] items;
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    //添加一个元素，如果数组满，则添加线程进入等待状态，知道有空位

    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[addIndex] = t;
            ++addIndex;
//            if (++addIndex == items.length) {
//              addIndex=0;
//            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    //由头部删除一个元素，如果数组为空则删除线程进入等待；知道有新添加元素
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[removeIndex];
            for (int i = 1; i < items.length; i++) {
                items[i - 1] = items[i];
                items[i] = null;
            }
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --addIndex;
            --count;
            notFull.signal();
            return (T) x;
        } finally {
            lock.unlock();
        }
    }

    public T get(int index) {
        return (T) items[index];
    }
}
