package nio.io.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 以上的代码有个问题，就是每次有客户端请求服务器端都会创建一个线程，
 * 当线程过多时，服务器端可能会宕机。解决这个问题，可以使用JDK提供的线程池（伪异步）。
 * Created by ${WangChengYong} on 2018/3/15.
 */
public class Server {

    private static int PORT = 8379;

        public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket=new ServerSocket(PORT);
            //进行阻塞
            Socket socket = serverSocket.accept();
            //启动一个线程来处理客户端请求
            new Thread(new ServerHandler(socket)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
