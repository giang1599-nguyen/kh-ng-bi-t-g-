package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import server.IWordsProcessing;

public class Test {
	static final String HOST = "127.0.0.1";
	static final int PORT = 22222;
	IWordsProcessing word;
	private BufferedReader userIn;
	String command;
	String pagam;

	public static void main(String[] args) throws RemoteException, NotBoundException {
		new Test();
	}

	public Test() throws RemoteException, NotBoundException {

		try {
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			word = (IWordsProcessing) registry.lookup("word");
			getWelcomeMessage();

			//
			userIn = new BufferedReader(new InputStreamReader(System.in));

		} catch (Exception e) {
		}
	}

	private void getWelcomeMessage() throws RemoteException {
		try {
			String s = word.getWelcomeMessage();
			System.out.println("Test" + s);
		} catch (RemoteException e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}
}
