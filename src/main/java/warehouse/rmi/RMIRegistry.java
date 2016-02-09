package warehouse.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRegistry {

	public RMIRegistry() {
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new NullSecurityManager());
		}
	}

	public Registry createRegistry() {
		try {
			return LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

}
