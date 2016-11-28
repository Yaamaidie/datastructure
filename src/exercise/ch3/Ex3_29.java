package exercise.ch3;

import java.util.Iterator;

import util.MyLinkedList;

/**
 * 编写以倒序打印双链表的算法，只是用常数的附加空间。意味着不可以用递归
 * @author lee
 *
 */
public class Ex3_29 {
	/**
	 * 见MyLinkedList类
	 */
	public static void main(String[] args) {
		MyLinkedList<Integer> l = new MyLinkedList<>();
		for (int i = 0; i < 10; i++) {
			l.add(i);
		}
		l.reverseList();
	}
}
