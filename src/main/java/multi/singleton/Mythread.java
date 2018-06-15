package multi.singleton;

/**
 * Created by jeff on 2017/11/22.
 */
public class Mythread extends Thread {

    @Override
    public void run() {
//        System.out.println(MyObject.getInstance().hashCode());
//        System.out.println(MyStaticObject.getInstance().hashCode());
        System.out.println(StaticObject.getInstance().hashCode());
    }

    public static void main(String[] args) {
        Mythread t1 = new Mythread();
        Mythread t2 = new Mythread();
        t1.start();
        t2.start();
    }
}
