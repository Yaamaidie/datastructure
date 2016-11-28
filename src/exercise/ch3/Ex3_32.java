package exercise.ch3;
/**
 * 使用单链表高效实现队列类，不用头节点和尾节点
 * @author lee
 *
 */
public class Ex3_32 {
	public static void main(String[] args) {
		SingleQueue<Integer> sq = new SingleQueue();
		sq.enqueue(2);
		System.out.println(sq.dequeue());
	}
}
class SingleQueue<T> {
	private Node<T> front, rear;
	
	private class Node<T> {
		private T data;
		private Node next;
		
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
	
	SingleQueue() {
		front = null;
		rear = null;
	}
	
	void enqueue(T x) {
		Node<T> p = new Node(x, null);
		if (rear != null) {
			rear = rear.next = p;
		} else {
			front = rear = p;
		}
	} 
	
	T dequeue() {
		if (front == null) {
			throw new RuntimeException("队列空，不可出队！");
		} 
		T data = front.data;
		if (front.next == null) {//只有一个节点时
			front = rear = null;
		} else {
			front = front.next;
		}
		return data;
	}
	
	
}
