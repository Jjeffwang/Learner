package distributeLock.redis;

/**
 * Created by ${WangChengYong} on 2018/2/26.
 */
public class Test {

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            MyThread myThread = new MyThread(service);
            myThread.start();
        }
    }
}
