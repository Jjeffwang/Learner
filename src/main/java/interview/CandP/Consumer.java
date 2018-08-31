package interview.CandP;

import java.util.List;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/28 0028 上午 10:56
 * Description:
 */
public class Consumer {
    private List list;
    public Consumer(List list){
        this.list=list;
    }

    public void getValue(){
        synchronized (list){
            while (list.isEmpty()){
                try {
                    System.out.println("消费者没有数据可以消费" + Thread.currentThread().getName() + " waiting");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费者" + Thread.currentThread().getName() + "消费了"+list.get(0));
            list.remove(0);
            list.notifyAll();

        }
    }
}
