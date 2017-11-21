package multi.thread.threadLocal;


/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class ThreadB extends Thread{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            Tools.threadLocal.set("ThreadB "+i+1);
            System.out.println("ThreadB get "+Tools.threadLocal.get());
        }
    }
}
