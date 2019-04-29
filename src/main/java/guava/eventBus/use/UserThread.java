package guava.eventBus.use;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import sun.security.action.PutAllAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/17 0017 上午 10:56
 * Description:
 */
public class UserThread extends Thread {

    private Socket connection;
    private EventBus channel;
    private BufferedReader in;
    private PrintWriter out;

    public UserThread(Socket connection, EventBus channel) {
        this.connection = connection;
        this.channel = channel;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Subscribe
    public void receiveMessage(String message) {
        if (out != null) {
            out.println(message);
            System.out.println("receiveMessage:" + message);
        }
    }

    @Override
    public void run() {
        try {
            String input;
            while ((input = in.readLine()) != null) {
                channel.post(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        channel.unregister(this);
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        in = null;
        out = null;

    }
}
