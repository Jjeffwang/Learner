package guava.eventBus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/17 0017 上午 10:37
 * Description:
 */
public class MainTest {

    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new Long(800990));

        System.out.println("LastMessage:"+listener.getLastMessage());
    }
}
