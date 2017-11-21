package multi.thread.block;

/**
 * Created by jeff on 2017/11/15.
 */
public class Mythread1 extends  Thread {

    private MyOneList myOneList;

    public Mythread1(MyOneList myOneList){
        this.myOneList=myOneList;
    }

    @Override
    public void run() {
        MyService myService=new MyService();
        myService.addService(myOneList,"A");

    }
}
