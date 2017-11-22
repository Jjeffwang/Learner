package multi.thread.lock.ReadAndWrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ${WangChengYong} on 2017/11/22.
 */
public class RWservice {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println("获取读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        try {
            lock.writeLock().lock();
            System.out.println("获取写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

}
