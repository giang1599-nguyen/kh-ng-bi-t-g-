package server;

import java.io.IOException;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) throws IOException {
		IWordsProcessing e = new WordsProcessingImpl();
		System.out.println(e.checkPass("giang", "123"));
		// System.out.println(e.addFile(0, new File("D:\\Test\\server.txt")));
//		 System.out.println(e.addText(0, "xin chào 1234"));
//		System.out.println(e.getWords(0));
//		System.out.println(e.getsum(0));
		ArrayList<Integer> list= e.getNumList(0);
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}
}
