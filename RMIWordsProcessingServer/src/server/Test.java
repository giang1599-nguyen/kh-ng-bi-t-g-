package server;

import java.io.File;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		IWordsProcessing e = new WordsProcessingImpl();
		System.out.println(e.addFile(new File("D:\\Test\\server.txt")));
		System.out.println(e.addText("0", "nguyễn thị giang"));
	}
}
