package nio.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 服务端工具类
 * Created by ${WangChengYong} on 2018/3/15.
 */
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //autoFlush要设置为true，或者在finally中进行flush，不然会使输出流阻塞
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            while (true) {
                String request = bufferedReader.readLine();
                if (null == request)
                    break;
                System.out.println("客户端发送的消息：" + request);
                printWriter.println("服务器端响应了客户端请求....");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (printWriter != null) {
                try {
//                    printWriter.flush();
                    printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                socket = null;
        }

    }
}
