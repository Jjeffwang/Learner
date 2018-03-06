package multi.thread.lock.ReadAndWrite;

/**
 * Created by ${WangChengYong} on 2017/11/22.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        RWservice service = new RWservice();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("A");
        threadA.start();
//        Thread.sleep(1000);
        ThreadB threadB = new ThreadB(service);
        threadB.setName("A");
        threadB.start();
    }
}
