package server;

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
import model.Data;
import model.User;

public class WordsProcessingImpl extends UnicastRemoteObject implements IWordsProcessing {
	private static final long serialVersionUID = 1L;
	String dir = "D:\\Test";

	File fileIn;
	Data data = new Data();
	ArrayList<User> listU = new ArrayList<>();
	ArrayList<File> listFile = new ArrayList<>();

	public WordsProcessingImpl() throws IOException {
		this.data = new Data();
		this.listU = data.listU();
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
	public int checkPass(String username, String pass) throws RemoteException {
		try {
			int count = 0;
			if (data.checkPass(username, pass)) {
				File temp = new File(dir + File.separator + "client-" + count++ + ".txt");
				temp.createNewFile();
				listFile.add(temp);
			}
			return listFile.size() - 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	@Override
	public boolean addFile(int id, File file) throws RemoteException {
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(listFile.get(id), true)),
					true);
			String line;
			while ((line = bis.readLine()) != null) {
				pw.println(line);
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
	public boolean addText(int id, String text) throws RemoteException {
		PrintWriter pw;
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(listFile.get(id), true)), true);
			pw.println(text);
			pw.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public int getNums(int id) throws RemoteException {

		return 0;
	}

	@Override
	public int getsum(int id) throws RemoteException {

		return 0;
	}

	@Override
	public int getWords(int id) throws RemoteException {

		return 0;
	}

	@Override
	public ArrayList<Integer> getNumList(int id) throws RemoteException {

		return null;
	}

}
