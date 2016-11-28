package demo.ch4;

import java.io.File;

/**
 * 先序遍历：打印文件系统
 * @author lee
 *
 */
public class PreorderTraversal {
	public void listAll(File file) {
		listAll(file, 0);
	}
	
	private void listAll(File file, int depth) {
		printName(file, depth);
		if (file.isDirectory()) {
			depth++;
			for (File sub : file.listFiles()) {
				listAll(sub, depth);
			}
		}
	}
	
	private void printName(File file, int depth) {
		System.out.println();
		for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
		System.out.print(file.getName());
	}
	
	public static void main(String[] args) {
		File file = new File("D:\\BaiduYunDownload");
		PreorderTraversal pt = new PreorderTraversal();
		pt.listAll(file);
	}
	
}

