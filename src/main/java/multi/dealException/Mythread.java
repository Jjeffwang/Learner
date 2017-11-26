package multi.dealException;

/**
 * Created by jeff on 2017/11/25.
 */
public class Mythread extends Thread{

    @Override
    public void run() {
        String name=null;
        System.out.println(name.hashCode());
    }

    public static void main(String[] args) {
        Mythread mythread=new Mythread();
        mythread.setName("mythread");
        //给所有线程设置异常处理器
//        Mythread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//
//            }
//        });
        mythread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName()+"出现了异常");
                e.printStackTrace();
            }
        });
        mythread.start();
    }
}
