package rpc.test;

import rpc.exporter.RpcExporter;
import rpc.importer.RpcImporter;
import rpc.service.EchoService;
import rpc.service.impl.EchoServiceImpl;

import java.net.InetSocketAddress;

/**
 * 服务的提供者，运行在服务端，负责提供服务接口定义和服务实现类：service
 * 服务发布者，运行在rpc服务端，通过代理将本地服务发布成远程服务，供其他消费者调用：exporter
 * 本地服务代理，运行在rpc客户端，通过代理调用远程服务提供者，然后将服务结果进行封装返回给本地消费者：importer
 *
 * Created by ${WangChengYong} on 2018/1/19.
 */
public class RpcTest {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RpcExporter.exeporter("localhost", 8088);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        RpcImporter<EchoService> importer = new RpcImporter<EchoService>();
        EchoService echo = importer.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8088));
        System.out.println(echo.echo("Are you ok ?"));
    }
}
