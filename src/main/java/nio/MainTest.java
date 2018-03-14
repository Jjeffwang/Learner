package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class MainTest {

	public static void main(String[] args) throws IOException {
		
		Selector clientSelector= Selector.open();
		Selector serverSelector= Selector.open();
		ServerSocketChannel serverChannel= ServerSocketChannel.open();
		ServerSocket serverSocket=serverChannel.socket();
		serverSocket.bind(new InetSocketAddress(9999));
		serverChannel.configureBlocking(false);
		serverChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
		 
		AcceptJob acceptJob=new AcceptJob(clientSelector,serverSelector);
		ProcessJob processJob=new ProcessJob(clientSelector);
		Thread acceptThread=new Thread(acceptJob);
		Thread aprocessThread=new Thread(processJob);
		acceptThread.start();
		aprocessThread.start();
		
	}
}
