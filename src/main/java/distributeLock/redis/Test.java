package distributeLock.redis;

import org.jboss.netty.util.internal.ConcurrentHashMap;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ${WangChengYong} on 2018/2/26.
 */
public class Test {

    public static void main(String[] args) {

        ConcurrentHashMap map=new ConcurrentHashMap();
        map.put("","");
        map.get("");
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            MyThread myThread = new MyThread(service);
            myThread.start();
        }
    }


}
