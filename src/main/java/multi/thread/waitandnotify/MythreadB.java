package multi.thread.waitandnotify;

/**
 * Created by ${WangChengYong} on 2017/11/17.
 */
public class MythreadB implements Runnable{

    private PrintStr printStr;
    public MythreadB(PrintStr printStr){
        this.printStr=printStr;
    }
    @Override
    public void run() {
        printStr.printB();
    }
}
