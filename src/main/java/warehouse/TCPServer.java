package warehouse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public TCPServer () {

	}

	private void startServer() {
		try (ServerSocket tcpServer = new ServerSocket(6666);) {
			int connections = 0;

			tcpServer.setSoTimeout(60000);
			while (true) {
				try {
					Socket socket = tcpServer.accept();
					connections++;
					new Incoming(connections, socket).start();
					new Outgoing(connections, socket).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
