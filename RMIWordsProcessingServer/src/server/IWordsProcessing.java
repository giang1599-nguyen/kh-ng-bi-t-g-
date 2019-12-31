package server;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IWordsProcessing extends Remote {

	String getWelcomeMessage() throws RemoteException;// gửi lời chào

	boolean checkUserExists(String username) throws RemoteException;

	int checkPass(String username, String pass) throws RemoteException;

	boolean addFile(int id, File file) throws RemoteException;// download khi client upload

	boolean addText(int id, String text) throws RemoteException; // thêm 1 dòng văn bản vào file

	int getNums(int id) throws RemoteException;// trả về số lượng các từ là số có trong file

	int getsum(int id) throws RemoteException;// trả về tổng giá trị các từ là số có trong file

	int getWords(int id) throws RemoteException;// trả về số lượng các từ k là số có trong file

	ArrayList<Integer> getNumList(int id) throws RemoteException; // trả về ds các từ số , mỗi số hiển thị 1 dòng
}
