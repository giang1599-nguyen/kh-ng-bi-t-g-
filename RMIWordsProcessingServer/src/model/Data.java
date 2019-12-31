package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
	ArrayList<User> listU = new ArrayList<>();

	public Data() {
	}

	void createData() {
		listU.add(new User("giang", "123"));
		listU.add(new User("quyen", "456"));
	}

	public ArrayList<User> listU() {
		createData();
		return listU;
	}

	public boolean checkUserExists(String username) {
		for (User u : listU) {
			if (u.getName().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkPass(String username, String pass) {
		for (User u : listU) {
			if (u.getName().equals(username) && u.getPassword().equals(pass))
				return true;

		}
		return false;
	}

	public static void main(String[] args) {
		Data d = new Data();
		ArrayList<User> l = d.listU();
		for (User string : l) {
			System.out.println(string);
		}
		System.out.println(d.checkUserExists("giang"));
		System.out.println(d.checkPass("giang", "123"));
	}
}
