package guava.eventBus.use;

import com.google.common.eventbus.EventBus;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/17 0017 上午 11:27
 * Description:
 */
public class MainTest {

    public static void main(String[] args) {
        EventBus channel = new EventBus();
        ServerSocket socket;
        try {
            socket = new ServerSocket(4444);
            while (true) {
                Socket connection = socket.accept();
                UserThread newUser = new UserThread(connection, channel);
                channel.register(newUser);
                newUser.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
