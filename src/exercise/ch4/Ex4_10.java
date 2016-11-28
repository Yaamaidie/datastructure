package exercise.ch4;

import java.io.File;

import demo.ch4.PostorderTraversal;

public class Ex4_10 {
	public static void main(String[] args) {
		File file = new File("D:\\BaiduYunDownload\\photos\\keetoo");
		PostorderTraversal pt = new PostorderTraversal();
		pt.size(file);
	}
}
