package nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class AcceptJob implements Runnable {

	private Selector clientSeletor;
	private Selector serverSeletor;

	public AcceptJob(Selector clientSeletor, Selector serverSeletor) {
		this.clientSeletor = clientSeletor;
		this.serverSeletor = serverSeletor;
	}

	@Override
	public void run() {
		try {
			while (true) {
				int n = serverSeletor.selectNow();
				if (n <= 0)
					continue;
				Iterator<SelectionKey> it = serverSeletor.selectedKeys()
						.iterator();
				while (it.hasNext()) {

					SelectionKey key = it.next();
					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key
								.channel();
						SocketChannel channel = server.accept();
						channel.configureBlocking(false);
						channel.register(clientSeletor, SelectionKey.OP_READ);
					}
					it.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
