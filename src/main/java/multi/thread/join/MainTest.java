package multi.thread.join;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class MainTest {

    public static void main(String[] args) {
        try {
            ThreadB b = new ThreadB();
            ThreadA a = new ThreadA(b);
            a.start();
            b.start();
//            b.join(1000);由于sleep（5000）所以join(1000)后会释放锁
            b.join();
            System.out.println("after join");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
