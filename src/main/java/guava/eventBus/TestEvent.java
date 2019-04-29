package guava.eventBus;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/17 0017 上午 10:25
 * Description:
 */
public class TestEvent {

    private final int message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:"+message);
    }

    public int getMessage(){
        return this.message;
    }
}
