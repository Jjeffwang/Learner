package multi.thread.threadLocal;


/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class ThreadA extends Thread {

    @Override
    public void run() {
       for(int i=0;i<10;i++){
           Tools.threadLocal.set("ThreadA "+i+1);
           System.out.println("ThreadA get "+Tools.threadLocal.get());
       }

    }
}

