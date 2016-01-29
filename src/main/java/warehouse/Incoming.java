package warehouse;

import java.net.Socket;

public class Incoming extends Thread {

	public Incoming(int name, Socket socket) {
		// TODO Auto-generated constructor stub
	}

	public Incoming(Runnable target) {
		super(target);
		// TODO Auto-generated constructor stub
	}

	public Incoming(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Incoming(ThreadGroup group, Runnable target) {
		super(group, target);
		// TODO Auto-generated constructor stub
	}

	public Incoming(ThreadGroup group, String name) {
		super(group, name);
		// TODO Auto-generated constructor stub
	}

	public Incoming(Runnable target, String name) {
		super(target, name);
		// TODO Auto-generated constructor stub
	}

	public Incoming(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		// TODO Auto-generated constructor stub
	}

	public Incoming(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
		// TODO Auto-generated constructor stub
	}

}
