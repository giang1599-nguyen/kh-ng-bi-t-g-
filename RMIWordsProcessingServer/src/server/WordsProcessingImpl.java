package server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import model.Data;
import model.User;

public class WordsProcessingImpl extends UnicastRemoteObject implements IWordsProcessing {
	private static final long serialVersionUID = 1L;
	String dir = "D:\\Test";
	// HashMap<String, File> file;
	// HashMap<String, Text> text;

	File fileIn;
	BufferedReader read;
	PrintWriter pw;
	Data data = new Data();
	ArrayList<User> listU = new ArrayList<>();

	public WordsProcessingImpl() throws IOException {
		this.data = new Data();
		this.listU = data.listU();
		try {
			this.fileIn = new File(dir + File.separator + "data.txt");
			this.fileIn.createNewFile();
			this.read = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileIn)));
			this.pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.fileIn, true)), true);
		} catch (FileNotFoundException e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}

	@Override
	public String getWelcomeMessage() throws RemoteException {
		try {
			return "welcome to Words Processing";
		} catch (Exception e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}

	@Override
	public boolean checkUserExists(String username) throws RemoteException {
		return data.checkUserExists(username);
	}

	@Override
	public boolean checkPass(String username, String pass) throws RemoteException {
		return data.checkPass(username, pass);
	}

	@Override
	public boolean addFile(File file) throws RemoteException {
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line;
			while ((line = bis.readLine()) != null) {
				pw.println(line);
				pw.flush();
				System.out.println("line: " + line);
			}
			bis.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addText(String id, String text) throws RemoteException {
		try {
			pw.println(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int getNums(String id) throws RemoteException {

		return 0;
	}

	@Override
	public int getsum(String id) throws RemoteException {

		return 0;
	}

	@Override
	public int getWords(String id) throws RemoteException {

		return 0;
	}

	@Override
	public ArrayList<Integer> getNumList(String id) throws RemoteException {

		return null;
	}

}
