package multi.thread.block;

/**
 * Created by jeff on 2017/11/15.
 */
public class MyService {

     public synchronized MyOneList addService(MyOneList list,String data){

        try {
//            synchronized (list) {
                if (list.getSize() < 1) {
                    //模拟远程花费2秒返回
                    Thread.sleep(2000);
                    list.add(data);

                }
//            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;

    }

    public static void main(String[] args) throws InterruptedException {
        MyOneList list=new MyOneList();
        Mythread1 mythread1=new Mythread1(list);
        mythread1.setName("A");
        mythread1.start();
        Mythread2 mythread2=new Mythread2(list);
        mythread2.setName("B");
        mythread2.start();
        Thread.sleep(3000);
        System.out.println(list.getSize());
    }
}
