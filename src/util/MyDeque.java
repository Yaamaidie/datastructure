package util;
/**
 * 双端队列 deque
 * 提供4个例程，push(X), pop(); inject(X); eject();
 * 均花费O(1)时间
 * @author lee
 *
 */
public class MyDeque<T> {
	/**
	 * 用linkdeList实现
	 */
	MyLinkedList<T> L;
	
	public MyDeque() {
		L = new MyLinkedList<>();
	}
	
	public void push(T x) {
		L.addFirst(x);
	}
	
	public T pop() {
		return L.removeFirst();
	}
	
	public void inject(T x) {
		L.addLast(x);
	}
	
	public T eject() {
		return L.removeLast();
	}
}
