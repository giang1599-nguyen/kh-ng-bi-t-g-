package client;

import java.io.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.StringTokenizer;

import server.IWordsProcessing;

public class Client {
	static final String HOST = "127.0.0.1";
	static final int PORT = 22222;
	IWordsProcessing word;
	private BufferedReader userIn;
	String command;
	String pagam;
	int id;

	private enum userStatus {
		NOTLOGGEDIN, ENTEREDUSERNAME, LOGGEDIN
	};

	private userStatus currentUserStatus = userStatus.NOTLOGGEDIN;

	public static void main(String[] args) throws NotBoundException, IOException {
		new Client();
	}

	public Client() throws RemoteException, NotBoundException {

		try {
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			word = (IWordsProcessing) registry.lookup("word");
			getWelcomeMessage();

			userIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please, enter your input: ");
			String line;
			StringTokenizer token;
			String userInput = "";
			while (true) {
				line = userIn.readLine();
				token = new StringTokenizer(line);
				command = token.nextToken();
				pagam = token.nextToken();
				// kết thúc
				if (command.equalsIgnoreCase("QUIT")) {
					registry.unbind("word");
					System.out.println("Stopped!");
					break;
				} else
				// chưa đăng nhập

				if (currentUserStatus == userStatus.NOTLOGGEDIN) {
					if (command.equalsIgnoreCase("USER")) {
						if (word.checkUserExists(pagam) == true) {
							userInput = pagam;
							currentUserStatus = userStatus.ENTEREDUSERNAME;
							System.out.println("Next, Enter your pass:");
						} else
							System.out.println("Enter username failed!");
					}
				} else
				// đã nhập user
				if (currentUserStatus == userStatus.ENTEREDUSERNAME) {
					if (command.equalsIgnoreCase("PASSWORD")) {
						id = word.checkPass(userInput, pagam);
						if (id != -1) {
							// set id
							currentUserStatus = userStatus.LOGGEDIN;
							System.out.println("Login successfully!");
						} else
							System.out.println("Enter password failed!");
					}
				} else {
					// đã đăng nhập
					switch (command.toUpperCase()) {
					case "USER":
						System.out.println("You logged!");
						break;
					case "PASSWORD":
						System.out.println("You logged!");
						break;
					case "ADD_FILE":
						addFile();
						break;
					case "ADD_TEXT":
						addText();
						break;

					default:
						System.out.println("Command not found!");
						break;
					}
				}
			}

		} catch (IOException e) {
			System.out.println("Commad failed!");
			System.out.println(e.getMessage());
		}
	}

	private void addText() throws RemoteException {
		String p = pagam.toString();
		System.out.println(pagam);
		word.addText(id, p);
		System.out.println("Added Text!");
	}

	private void addFile() throws RemoteException {
		// System.out.println(pagam);
		File file = new File(pagam);
		if (word.addFile(id, file)) {
			System.out.println("Added File!");
		} else {
			System.out.println("Add File failed!");
		}
	}

	private void getWelcomeMessage() throws RemoteException {
		try {
			String s = word.getWelcomeMessage();
			System.out.println(s);
		} catch (RemoteException e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}
}
