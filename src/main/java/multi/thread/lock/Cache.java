package multi.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Created by ${WangChengYong} on 2017/11/28.
 * 使用读写锁自定义缓存
 */
public class Cache {

    static Map<String,Object> map=new HashMap<String,Object>();
    static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    static Lock write=reentrantReadWriteLock.writeLock();
    static Lock read=reentrantReadWriteLock.readLock();

    public static final Object get(String key){
        read.lock();
        try{
            return map.get(key);
        }finally {
            read.unlock();
        }
    }

    public static final Object save(String key,Object value){
        write.lock();
        try{
            return map.put(key,value);
        }finally {
            read.unlock();
        }
    }

    public static final void clear(){
        write.lock();
        try{
            map.clear();
        }finally {
            write.unlock();
        }
    }

}
