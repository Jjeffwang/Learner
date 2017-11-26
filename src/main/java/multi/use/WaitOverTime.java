package multi.use;

/**
 * Created by jeff on 2017/11/26.
 * 等待超时模式
 */
public class WaitOverTime {

    private String result;

    public WaitOverTime(String result){
        this.result=result;
    }

    public synchronized Object get(long mills) {
        try {
            long future = System.currentTimeMillis() + mills;
            long remaining = mills;
            while (result == null && remaining > 0) {
                wait(remaining);
                remaining=future-System.currentTimeMillis();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  result;
    }


    public static void main(String[] args) {
        String result="test";
        WaitOverTime waitOverTime=new WaitOverTime(result);
        System.out.println(waitOverTime.get(5000));

    }
}
