package exercise.ch3;

/**
 * 使用单链表高效实现栈类，不用头节点和尾节点
 * @author lee
 *
 */
public class Ex3_31 {
	
}
class SingleStack<T> {
	Node<T> head;
	
	private class Node<T> {
		T data;
		Node<T> next;
		Node() {
			this(null, null);
		}
		Node(T x) {
			this(x, null);
		}
		Node(T x, Node<T> p) {
			data = x;
			next = p;
		}
	}
	
	SingleStack() {
		head = null;
	}
	
	void push(T x) {
		Node<T> p = new Node(x, head);
		head = p;
	}
	
	T top() {
		return head.data;
	}
	
	T pop() {
		T data = head.data;
		head = head.next;
		return data;
	}
	
}
