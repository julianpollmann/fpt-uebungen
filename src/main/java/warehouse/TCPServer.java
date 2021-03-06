package warehouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fpt.com.Order;
import fpt.com.Product;
import io.net.tcp.TCPClientConnector;

public class TCPServer {

	private final static int port = 6666;
	private ExecutorService threadPool;
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private InputStream inStream;
	private OutputStream outStream;
	private Order orderList;
	private TCPServerConnector connector;

	public TCPServer () {
		threadPool = Executors.newCachedThreadPool();

		startServer();
	}

	private void startServer() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("[TCPServer] Server lauscht auf Port: " + serverSocket.getLocalPort());
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		while(true) {
			try {
				clientSocket = serverSocket.accept();
				System.out.println("[TCPServer] Neue Verbindung");
				inStream = clientSocket.getInputStream();
				outStream = clientSocket.getOutputStream();

				// List for all orders
				orderList = new model.Order();

				if(clientSocket != null && outStream != null && inStream != null) {
					connector = new TCPServerConnector(inStream, outStream, orderList);
					connector.bindStreams();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//		try (ServerSocket tcpServer = new ServerSocket(port)) {
//			System.out.println("[TCPServer] Server lauscht auf Port: " + tcpServer.getLocalPort());
//
//			int connections = 0;
//			while (true) {
//				try {
//					Socket socket = tcpServer.accept();
//					System.out.println("[TCPServer] Neue Verbindung");
//					InputStream inStream = socket.getInputStream();
//					OutputStream outStream = socket.getOutputStream();
//					connections++;
////					new Incoming(connections, socket).start();
////					new Outgoing(connections, socket).start();
//					threadPool.execute(new Incoming(connections, socket));
////					threadPool.execute(new TCPServerWorkerThread(inStream, outStream));
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
	}

}
