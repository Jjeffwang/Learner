package multi.test;

/**
 * Created by ${WangChengYong} on 2017/11/24.
 */
public class ThreadA extends Thread {

    @Override
    public void run() {
        for (int i = 1; i < 10; i++) {
            if (i == 5) {
                MyCondition myCondition = MyCondition.getInstance();
                myCondition.waitCondition();
                System.out.println(i + "=做处理");
            }
            System.out.println(i);
        }
    }
}
