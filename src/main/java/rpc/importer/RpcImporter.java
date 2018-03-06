package rpc.importer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 将本地的接口调用转换成jdk动态代理，在动态代理中实现接口的远程调用
 * 创建socket客户端，根据指定地址连接远程服务
 * 将远程服务调用所需的接口类、方法名、参数列表等编码后发送给服务的提供者
 * 同步阻塞等待服务端的返回应答，获取应答后返回
 * <p>
 * Created by ${WangChengYong} on 2018/1/19.
 */
public class RpcImporter<S> {

    public S importer(final Class<?> serviceClass, final InetSocketAddress address) {

        return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class<?>[]{serviceClass.getInterfaces()[0]}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = null;
                ObjectOutputStream output = null;
                ObjectInputStream input = null;
                try {
                    socket = new Socket();
                    socket.connect(address);
                    output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeUTF(serviceClass.getName());
                    output.writeUTF(method.getName());
                    output.writeObject(method.getParameterTypes());
                    output.writeObject(args);
                    input = new ObjectInputStream(socket.getInputStream());
                    return input.readObject();
                } finally {
                    if (socket != null) {
                        socket.close();
                    }
                    if (output != null) {
                        output.close();
                    }
                    if (input != null) {
                        input.close();
                    }
                }
            }
        });
    }
}
