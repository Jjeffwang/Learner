package nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ProcessJob implements Runnable {

	private Selector clientSeletor;

	public ProcessJob(Selector clientSeletor) {
		this.clientSeletor = clientSeletor;
	}

	@Override
	public void run() {

		int count = 0;
		ByteBuffer bb = ByteBuffer.allocate(1024);
		try {
			while (true) {
				int n = clientSeletor.selectNow();
				if (n <= 0)
					continue;
				Iterator<SelectionKey> it = clientSeletor.selectedKeys()
						.iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();
					if (key.isAcceptable()) {
						SocketChannel client = (SocketChannel) key.channel();
						while ((count = client.read(bb)) > 0) {
							System.out.write(bb.array(), 0, count);
							System.out.println(bb.array());
							bb.clear();
						}
						client.close();
					}
					it.remove();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
