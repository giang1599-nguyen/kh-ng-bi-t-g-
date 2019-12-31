// package client;
//
// import java.io.BufferedReader;
// import java.io.File;
// import java.io.InputStreamReader;
// import java.rmi.NotBoundException;
// import java.rmi.RemoteException;
// import java.rmi.registry.LocateRegistry;
// import java.rmi.registry.Registry;
// import java.util.StringTokenizer;
//
// import server.IWordsProcessing;
//
// public class Test {
// static final String HOST = "127.0.0.1";
// static final int PORT = 22222;
// IWordsProcessing word;
// private BufferedReader userIn;
// String command;
// String pagam;
//
// private enum userStatus {
// NOTLOGGEDIN, ENTEREDUSERNAME, LOGGEDIN
// };
//
// private userStatus currentUserStatus = userStatus.NOTLOGGEDIN;
//
// public static void main(String[] args) throws RemoteException,
// NotBoundException {
// new Test();
// }
//
// public Test() throws RemoteException, NotBoundException {
//
// try {
// Registry registry = LocateRegistry.getRegistry(HOST, PORT);
// word = (IWordsProcessing) registry.lookup("word");
// getWelcomeMessage();
//
// userIn = new BufferedReader(new InputStreamReader(System.in));
// System.out.println("Please, enter your input: ");
// String line;
// StringTokenizer token;
// String userInput = "";
// while (true) {
// line = userIn.readLine();
// token = new StringTokenizer(line);
// command = token.nextToken();
// // System.out.println("commad " + command + "listU " +
// // word.checkUserExists("giang"));
// // kết thúc
// if (command.equalsIgnoreCase("QUIT")) {
// registry.unbind("word");
// System.out.println("Stopped!");
// break;
// } else
// // chưa đăng nhập
//
// if (currentUserStatus == userStatus.NOTLOGGEDIN) {
// if (command.equalsIgnoreCase("USER")) {
// pagam = token.nextToken();
// if (word.checkUserExists(pagam) == true) {
// userInput = pagam;
// currentUserStatus = userStatus.ENTEREDUSERNAME;
// System.out.println("Next, Enter your pass:");
// } else
// System.out.println("Enter username failed!");
// }
// } else
// // đã nhập user
// if (currentUserStatus == userStatus.ENTEREDUSERNAME) {
// if (command.equalsIgnoreCase("PASSWORD")) {
// pagam = token.nextToken();
// if (word.checkPass(userInput, pagam) == true) {
// currentUserStatus = userStatus.LOGGEDIN;
// System.out.println("Login successfully!");
// } else
// System.out.println("Enter password failed!");
// }
// } else {
// // đã đăng nhập
// switch (command.toUpperCase()) {
// case "USER":
// System.out.println("You logged!");
// break;
// case "PASSWORD":
// System.out.println("You logged!");
// break;
// case "ADD_FILE":
// pagam = token.nextToken();
// System.out.println(pagam);
// File file = new File(pagam);
// if (word.addFile(file)) {
// System.out.println("Added File!");
// } else {
// System.out.println("Add File failed!");
// }
// break;
// case "ADD_TEXT":
// pagam = token.nextToken();
// String p = pagam.toString();
// System.out.println(pagam);
// word.addText("0", p);
// System.out.println("Added Text!");
// break;
//
// default:
// System.out.println("Command not found!");
// break;
// }
// }
// }
//
// } catch (Exception e) {
// System.out.println("Commad failed!");
// }
// }
//
// private void getWelcomeMessage() throws RemoteException {
// try {
// String s = word.getWelcomeMessage();
// System.out.println(s);
// } catch (RemoteException e) {
// throw new RemoteException(e.getMessage(), e);
// }
// }
// }
