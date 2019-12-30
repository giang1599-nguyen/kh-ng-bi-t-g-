package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	static final int PORT = 22222;
	Registry regis;
	boolean running = true;

	public Server() throws RemoteException {
		try {
			regis = LocateRegistry.createRegistry(PORT);
		} catch (Exception e) {
			System.out.println("Coundn't creat port!");
			System.exit(-1);
		}
		try {
			System.out.println("Server running with port: " + PORT);

			while (running) {
				IWordsProcessing word = new WordsProcessingImpl();
				regis.rebind("word", word);
			}

		} catch (Exception e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}

	public static void main(String[] args) throws RemoteException {
		new Server();
	}
}
