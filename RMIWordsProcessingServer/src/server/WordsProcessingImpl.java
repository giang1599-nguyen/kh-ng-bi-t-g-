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
import java.util.StringTokenizer;

import model.Data;
import model.User;

public class WordsProcessingImpl extends UnicastRemoteObject implements IWordsProcessing {
	private static final long serialVersionUID = 1L;
	String dir = "D:\\Test";

	File fileIn;
	Data data = new Data();
	ArrayList<User> listU = new ArrayList<>();
	static ArrayList<File> listFile = new ArrayList<>();
	private BufferedReader bis;
	private PrintWriter pw;
	int count;
	public WordsProcessingImpl() throws IOException {
		this.data = new Data();
		this.listU = data.listU();
		count = listFile.size();
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
			
			if (data.checkPass(username, pass)) {
				File temp = new File(dir + File.separator + "client-" + ++count + ".txt");
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
			bis = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(listFile.get(id), true)), true);
			String line;
			while ((line = bis.readLine()) != null) {
				pw.println(line);
				// System.out.println("line: " + line);
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
		int count = 0;
		try {
			bis = new BufferedReader(new InputStreamReader(new FileInputStream(listFile.get(id))));
			String line;
			while ((line = bis.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line);
				while (token.hasMoreTokens()) {
					String word = token.nextToken();
					try {
						Integer.parseInt(word);
						count++;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
			bis.close();
		} catch (Exception e) {
			throw new RemoteException(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int getsum(int id) throws RemoteException {
		int sum = 0;
		try {
			bis = new BufferedReader(new InputStreamReader(new FileInputStream(listFile.get(id))));
			String line;
			while ((line = bis.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line);
				while (token.hasMoreTokens()) {
					String word = token.nextToken();
					try {
						sum += Integer.parseInt(word);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
			bis.close();

		} catch (Exception e) {
			throw new RemoteException(e.getMessage(), e);
		}
		return sum;
	}

	@Override
	public int getWords(int id) throws RemoteException {
		int count = 0;
		try {
			bis = new BufferedReader(new InputStreamReader(new FileInputStream(listFile.get(id))));
			String line;
			while ((line = bis.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line);
				while (token.hasMoreTokens()) {
					String word = token.nextToken();
					try {
						Integer.parseInt(word);
					} catch (Exception e) {
						count++;
						System.out.println(e.getMessage());
					}
				}
			}
			bis.close();

		} catch (Exception e) {
			throw new RemoteException(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public ArrayList<Integer> getNumList(int id) throws RemoteException {
		ArrayList<Integer> listNum = new ArrayList<>();
		try {
			bis = new BufferedReader(new InputStreamReader(new FileInputStream(listFile.get(id))));
			String line;
			while ((line = bis.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line);
				while (token.hasMoreTokens()) {
					String word = token.nextToken();
					try {
						listNum.add(Integer.parseInt(word));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
			bis.close();

		} catch (Exception e) {
			throw new RemoteException(e.getMessage(), e);
		}
		return listNum;
	}

}
