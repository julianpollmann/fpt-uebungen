package warehouse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Warehouse {

	private ServerSocket server;
	private Incoming iThread;

	public Warehouse() {

	}

	public static void main(String[] args) {


		try (ServerSocket server = new ServerSocket(6666);) {
			int connections = 0;

			server.setSoTimeout(60000);
			while (true) {
				try {
					Socket socket = server.accept();
					connections++;
					new Incoming(connections, socket).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
