package interview.CandP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/28 0028 下午 3:07
 * Description:
 */
public class MainTest {

    public static void main(String[] args) {
        List list = new ArrayList();

        Produce produce = new Produce(list);
        ProduceThread[] threadP = new ProduceThread[10];
        for (int i = 0; i < 10; i++) {
            threadP[i] = new ProduceThread(produce);
            threadP[i].setName("p");
            threadP[i].start();
        }
        Consumer consumer = new Consumer(list);
        ConsumerThread[] threadC = new ConsumerThread[5];
        for (int i = 0; i < 5; i++) {
            threadC[i] = new ConsumerThread(consumer);
            threadC[i].setName("c");
            threadC[i].start();
        }
    }
}
