package rpc.exporter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 服务的发布者
 * 监听客户端的TCP连接，接收到新的客户端连接之后，将其封装成Task，由线程池执行
 * 将客户端发送的码流反序列化成对象，发射调用服务实现者，获取执行结果
 * 将执行结果反序列化，通过socket发送到客户端
 * 远程调用服务后，释放socket等连接资源
 * <p>
 * Created by ${WangChengYong} on 2018/1/19.
 */
public class RpcExporter {

    static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void exeporter(String hostName, int port) throws Exception {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(hostName, port));
        try {
            while (true) {
                executor.execute(new ExeporterTask(server.accept()));
            }
        } finally {
            server.close();
        }
    }

    private static class ExeporterTask implements Runnable {

        Socket client = null;

        public ExeporterTask(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {

            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {
                input = new ObjectInputStream(client.getInputStream());
                String interfaceName = input.readUTF();
                System.out.println(interfaceName);
                Class<?> service = Class.forName(interfaceName);
                String methodName = input.readUTF();
                System.out.println(methodName);
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Method method = service.getMethod(methodName, parameterTypes);
                Object result = method.invoke(service.newInstance(), arguments);
                output = new ObjectOutputStream(client.getOutputStream());

                output.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
