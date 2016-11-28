package exercise.ch3;

import util.MyLinkedList;

/**
 * 如果从某个节点p开始，接着跟有足够的数目的next链将我们带回到节点p，那么这个链表包含一个循环。
 * p不必是该表的第一个节点。假设给你一个链表，它包含N个节点；不过N的值是不知道的。
 * a.设计一个O(N)算法以确定该表是否包含有循环。你可以使用O(N)的额外空间。
 * b.重复(a)部分，但是只使用O(1)的额外空间。(提示：使用两个迭代器，他们最初在表的开始处，但以不同的速度推进。)
 * @author lee
 *
 */
public class Ex3_34 {
	
	static <T> boolean check1(MyLinkedList<T> linkedList) {
		return false;
	}
	
	//追赶法
	static <T> boolean check2(MyLinkedList<T> linkedList) {
		return false;
	}
}

