package guava.eventBus;

import com.google.common.eventbus.Subscribe;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/17 0017 上午 10:34
 * Description:
 */
public class EventListener {

    public int lastMessage;

    public Long longMessage;
    @Subscribe
    public void listen(TestEvent testEvent){
        lastMessage=testEvent.getMessage();
        System.out.println("message:"+lastMessage);
    }

    @Subscribe
    public void listen(long testEvent){
        longMessage=testEvent;
        System.out.println("longMessage:"+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
