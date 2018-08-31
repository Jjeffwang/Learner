package interview.CandP;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/28 0028 下午 3:03
 * Description:
 */
public class ProduceThread extends Thread{

    private Produce produce;

    public ProduceThread(Produce produce){
        this.produce=produce;
    }

    @Override
    public void run() {
        try {
            produce.setValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
