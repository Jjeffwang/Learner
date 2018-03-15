package nio.server;

import org.apache.commons.io.filefilter.FalseFileFilter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by ${WangChengYong} on 2018/3/15.
 */
public class NioServer implements Runnable {

    private Selector selector;

    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public NioServer(int port) {
        try {
            //1 打开多复用器
            selector = Selector.open();
            //2 打开服务器通道
            ServerSocketChannel channel = ServerSocketChannel.open();
            //3 设置服务器通道为非阻塞方式
            channel.configureBlocking(false);
            //4 绑定地址
            channel.bind(new InetSocketAddress(port));
            //5 把服务器通道注册到多路复用选择器上，并监听阻塞状态
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server start, port：" + port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            //1 必须让多路复用选择器开始监听
            try {
                selector.select();
                //2 返回所有已经注册到多路复用选择器上的通道的SelectionKey
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                //遍历key
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    //key有效
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                           // key是阻塞状态，则调用accept()方法
                            accept(key);
                        }
                        if (key.isReadable()) {
                            ///如果key是可读状态，则调用read()方法
                            read(key);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void accept(SelectionKey key) {
        try {
            //1 获取服务器通道
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            //2 执行阻塞方法
            SocketChannel socketChannel = channel.accept();
            //3 设置阻塞模式为非阻塞
            socketChannel.configureBlocking(false);
            //4 注册到多路复用选择器上，并设置读取标识
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void read(SelectionKey key) {
        try {
            //1 清空缓冲区中的旧数据
            byteBuffer.clear();
            //2 获取之前注册的SocketChannel通道
            SocketChannel socketChannel = (SocketChannel) key.channel();
            //3 将sc中的数据放入buffer中
            int count = socketChannel.read(byteBuffer);
            //== -1表示通道中没有数据
            if (count == -1) {
                key.channel().close();
                key.cancel();
                return;
            }
            //读取到了数据，将buffer的position复位到0
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
            //将buffer中的数据写入byte[]中
            byteBuffer.get(bytes);
            String body = new String(bytes).trim();
            System.out.println("Server：" + body);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new Thread(new NioServer(8379)).start();

    }
}
