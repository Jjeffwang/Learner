package interview.CandP;

import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/28 0028 下午 2:57
 * Description:
 */
public class Produce {
    private List list;

    public Produce(List list) {
        this.list = list;
    }

    public void setValue() throws InterruptedException {
        synchronized (list) {
            while (list.size() == 5) {
                list.wait();
                System.out.println("list is full");
            }
            list.add(RandomUtils.nextInt(0, 100));
            list.notifyAll();
        }
    }
}
