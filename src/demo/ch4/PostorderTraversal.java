package demo.ch4;

import java.io.File;
import java.math.BigDecimal;

/**
 * 后序遍历：计算文件大小 
 */
public class PostorderTraversal {
	
	public void size(File file) {
		size(0, file);
	}
	
	private BigDecimal size(int depth, File file) {
		BigDecimal totalSize = new BigDecimal(file.length());//目录或文件的本身大小
		if (file.isDirectory()) {
			depth++;
			for (File sub : file.listFiles()) {
				totalSize = totalSize.add(size(depth, sub));
			}
			printName(depth - 1, file);
			printSize(totalSize);
		} else {
			printName(depth, file);
			printSize(totalSize);
		}
		return totalSize;
	}
	
	private void printName(int depth, File file) {
		System.out.println();
		for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
		System.out.print(file.getName());
		System.out.print("\t" + file.length());
	}
	
	private void printSize(BigDecimal size) {
		System.out.print(size);
	}
	
	public static void main(String[] args) {
		File file = new File("D:\\BaiduYunDownload");
		PostorderTraversal pt = new PostorderTraversal();
		pt.size(file);
	}
}
