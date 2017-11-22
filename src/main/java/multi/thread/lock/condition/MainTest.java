package multi.thread.lock.condition;

/**
 * Created by ${WangChengYong} on 2017/11/22.
 */
public class MainTest {

    public static void main(String[] args)  {
        Myservice myservice = new Myservice();
        ThreadA threadA = new ThreadA(myservice);
        ThreadB threadB = new ThreadB(myservice);
        threadA.start();
        threadB.start();

    }
}
