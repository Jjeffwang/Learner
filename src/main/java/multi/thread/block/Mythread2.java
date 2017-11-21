package multi.thread.block;

/**
 * Created by jeff on 2017/11/15.
 */
public class Mythread2 extends Thread {

    private MyOneList myOneList;

    public Mythread2(MyOneList myOneList){
        this.myOneList=myOneList;
    }

    @Override
    public void run() {
        MyService myService=new MyService();
        myService.addService(myOneList,"B");

    }
}
