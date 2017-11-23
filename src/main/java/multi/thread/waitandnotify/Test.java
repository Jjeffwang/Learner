package multi.thread.waitandnotify;

/**
 * Created by ${WangChengYong} on 2017/11/23.
 */
public class Test {

    public static void main(String[] args) {
        PrintStr printStr = new PrintStr();
        for (int i = 0; i <= 10; i++) {
            MythreadA mythreadA = new MythreadA(printStr);
            Thread threadA = new Thread(mythreadA);
            threadA.start();
            MythreadB mythreadB = new MythreadB(printStr);
            Thread threadB = new Thread(mythreadB);
            threadB.start();
        }
    }
}
