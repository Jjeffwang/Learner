package multi.thread.waitandnotify;

/**
 * Created by ${WangChengYong} on 2017/11/17.
 */
public class MythreadC implements Runnable{

    private PrintStr printStr;
    public MythreadC(PrintStr printStr){
        this.printStr=printStr;
    }
    @Override
    public void run() {
        printStr.read();
    }
}
