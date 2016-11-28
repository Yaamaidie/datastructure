package exercise.ch3;

import util.MyArrayList;

/**
 * 使用循环数组高效实现队列类
 * @author lee
 *
 */
public class Ex3_33 {
	public static void main(String[] args) {
		SingleQueueArray<Integer> queue = new SingleQueueArray<Integer>();
		for (int i = 0; i < 90; i++) {
			queue.enqueue(i);
		}
		while (!queue.empty()) {
			System.out.println(queue.dequeue());
		} 
	}

}

class SingleQueueArray<T> {
	/**
	 * 使用已有的动态数组，就不用自己再重复写什么扩容之类的东西
	 */
	private MyArrayList<T> elements;
	/**
	 * 队列容量，front mod maxSize 或者 rear mod maxSize 来定义新的front和rear
	 * 
	 */
	private int maxSize;
	private int front, rear;
	
	SingleQueueArray() {
		this(100);
	}
	
	SingleQueueArray(int s) {
		maxSize = s;
		elements = new MyArrayList<T>(s);
	}
	
	void enqueue(T x) {
		if (!full()) {
			if (elements.size() < maxSize) {//add直到ArrayList的size() == maxSize
				elements.add(x);
			} else {
				elements.set(rear, x);//
			}
			rear = (rear + 1) % maxSize;
		} else {
			throw new RuntimeException("队列满了");
		}
	}
	
	T dequeue() {
		T temp = null;
		if (!empty()) {
			temp = elements.get(front);
			front = (front + 1) % maxSize;
			return temp;
		} else {
			throw new RuntimeException("队列已空");
		}
	}
	boolean full() {
		return (rear + 1) % maxSize == front;
	}
	
	boolean empty() {
		return front == rear;
	}
}
