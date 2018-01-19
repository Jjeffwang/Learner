package rpc.test;

import rpc.exeporter.RpcExeporter;
import rpc.importer.RpcImporter;
import rpc.service.EchoService;
import rpc.service.impl.EchoServiceImpl;

import java.net.InetSocketAddress;

/**
 * Created by ${WangChengYong} on 2018/1/19.
 */
public class RpcTest {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RpcExeporter.exeporter("localhost", 8088);
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
