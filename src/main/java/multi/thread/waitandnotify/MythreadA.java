package multi.thread.waitandnotify;

/**
 * Created by ${WangChengYong} on 2017/11/17.
 */
public class MythreadA implements Runnable {

    private PrintStr printStr;

    public MythreadA(PrintStr printStr) {
        this.printStr = printStr;
    }

    @Override
    public void run() {
        printStr.printA();
    }
}
