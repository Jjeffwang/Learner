package interview.CandP;

import multi.use.queue.BoundedQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/28 0028 上午 11:32
 * Description:
 */
public class BoundQueue<T> {

    private Object[] items;
    private int queueSize;
    private int addIndex;
    private int removeIndex;

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public BoundQueue(int size) {
        items = new Object[size];
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void add(T t) {
        lock.lock();
        try {
            while (queueSize == items.length) {
                notFull.await();
            }
            items[addIndex] = t;
            addIndex++;
            queueSize++;
            notEmpty.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T remove() {
        lock.lock();
        try {
            while (queueSize == 0) {
                notEmpty.await();
            }
            Object x = items[removeIndex];
            for (int i = 0; i < items.length - 1; i++) {
                items[i] = items[i + 1];
            }
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --addIndex;
            --queueSize;
            notFull.signalAll();
            return (T) x;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }

    public T get(int index) {
        return (T) items[index];
    }


    public static void main(String[] args) {
        BoundQueue boundedQueue = new BoundQueue(5);
        try {
            boundedQueue.add("1");
            boundedQueue.add("2");
            boundedQueue.add("3");
            boundedQueue.add("4");
            boundedQueue.add("5");
            boundedQueue.add("6");
            System.out.println(boundedQueue.remove());
            System.out.println(boundedQueue.get(0));
//            System.out.println(boundedQueue.get(0));
//            System.out.println(boundedQueue.get(1));
//            System.out.println(boundedQueue.get(2));
//            System.out.println(boundedQueue.get(3));
//            System.out.println(boundedQueue.get(4));
//            boundedQueue.add(6);
//            System.out.println("---------------------------------------");
//            System.out.println(boundedQueue.get(0));
//            System.out.println(boundedQueue.get(1));
//            System.out.println(boundedQueue.get(2));
//            System.out.println(boundedQueue.get(3));
//            System.out.println(boundedQueue.get(4));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
