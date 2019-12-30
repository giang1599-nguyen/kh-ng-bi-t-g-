package server;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IWordsProcessing extends Remote {

	String getWelcomeMessage() throws RemoteException;// gửi lời chào

	boolean checkUserExists(String username) throws RemoteException;

	boolean checkPass(String username, String pass) throws RemoteException;

	boolean addFile(File file) throws RemoteException;// dowload khi client upload

	boolean addText(String id, String text) throws RemoteException; // thêm 1 dòng văn bản vào file

	int getNums(String id) throws RemoteException;// trả về số lượng các từ là số có trong file

	int getsum(String id) throws RemoteException;// trả về tổng giá trị các từ là số có trong file

	int getWords(String id) throws RemoteException;// trả về số lượng các từ k là số có trong file

	ArrayList<Integer> getNumList(String id) throws RemoteException; // trả về ds các từ số , mỗi số hiển thị 1 dòng
}
