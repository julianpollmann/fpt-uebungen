package warehouse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {

	private ExecutorService threadPool;
	private ServerSocket tcpServer;
	private int port;

	public TCPServer () {
		threadPool = Executors.newCachedThreadPool();

		startServer();
	}

	private void startServer() {
		try (ServerSocket tcpServer = new ServerSocket(6666)) {
			System.out.println("[TCPServer] Server lauscht auf Port: " + tcpServer.getLocalPort());

			int connections = 0;
			while (true) {
				try {
					Socket socket = tcpServer.accept();
					System.out.println("[TCPServer] Neue Verbindung");
					connections++;
//					new Incoming(connections, socket).start();
//					new Outgoing(connections, socket).start();
					threadPool.execute(new Incoming(connections, socket));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
