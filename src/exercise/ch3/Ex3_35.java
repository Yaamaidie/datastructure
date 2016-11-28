package exercise.ch3;
/**
 * 选b，迭代器对应表的最后一项，因为它指向了第一个节点
 * @author lee
 *
 */
public class Ex3_35 {
	public static void main(String[] args) {
		circleList<Integer> list = new circleList<>();
		for (int i = 0; i < 60; i++) {
			list.enqueue(i);
		}
		while (!list.empty()) {
			System.out.println(list.dequeue());
		}
	}
}
class circleList<T> {
	private Node<T> rear;
	private int maxSize, theSize;
	private class Node<O> {
		O data;
		Node<O> next;
		Node(O x) {
			this(x, null);
		}
		Node(O x, Node<O> p) {
			data = x;
			next = p;
		}
		
	}
	
	circleList() {
		this(100);
	}
	
	circleList(int capcity) {
		maxSize = capcity;
		theSize = 0;
		rear = null;
	}
	
	boolean empty() {
		return theSize  == 0;
	}
	
	boolean full() {
		return maxSize == theSize;
	}
	
	void enqueue(T x) {
		if (!full()) {
			Node<T> node = new Node<>(x);
			if (rear != null) {
				Node<T> temp = rear.next;
				node.next = temp;
				rear.next = node;
				rear = rear.next;
			} else {
				rear = node;
				rear.next = rear;
			}
			theSize++;
		} else {
			throw new RuntimeException("队列已满");
		}
	}
	
	T dequeue() {
		if (!empty()) {
			T temp;
			if (rear.next == null) {
				temp = rear.data;
				rear = null;
				theSize--;
				return temp;
			} else {
				temp = rear.next.data;
				rear.next = rear.next.next;
				theSize--;
				return  temp;
			}
		} else {
			throw new RuntimeException("队列已空");
		}
	}
	
}