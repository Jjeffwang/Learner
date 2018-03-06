package multi.thread.threadLocal;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class MainTest {

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadA.start();
        threadB.start();
    }
}
