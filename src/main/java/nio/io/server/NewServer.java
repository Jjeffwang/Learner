package nio.io.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 通过添加线程池来执行服务端线程
 * Created by ${WangChengYong} on 2018/3/15.
 */
public class NewServer {

    private static int PORT = 8379;

    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket=new ServerSocket(PORT);
            //进行阻塞
            Socket socket = serverSocket.accept();
            HandlerExecutorPool handlerExecutorPool=new HandlerExecutorPool();
            handlerExecutorPool.execute(new ServerHandler(socket));
            //启动一个线程来处理客户端请求

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
