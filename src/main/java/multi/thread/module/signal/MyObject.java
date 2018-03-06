package multi.thread.module.signal;

/**
 * Created by jeff on 2017/11/22.
 */
public class MyObject {
    private volatile static MyObject myObject;

    public static MyObject getInstance() {
        try {
            //等同于 synchronized public static MyObject getInstance
            //全部代码被上锁，效率低
            // synchronized (MyObject.class)
            if (myObject != null) {
            } else {
                Thread.sleep(3000);
            }
            //使用双检测机制，保证不需要同步代码的异步执行和单例的效果
            synchronized (MyObject.class) {
                if (myObject == null) {
                    myObject = new MyObject();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myObject;
    }


}
