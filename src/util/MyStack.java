package util;

public class MyStack<T> extends MyLinkedList<T> {
	public static void main(String[] args) {
		MyStack<Integer> s = new MyStack<>();
		s.push(1);
		s.push(2);
		int a = s.pop();
		int b = s.pop();
		System.out.println("a:" + a + "b:" + b);
	}
	private MyLinkedList<T> theItems;
	public MyStack() {
		theItems = new MyLinkedList<T>();
	}
	
	public void push(T t) {
		theItems.addFirst(t);
	}
	
	public T pop() {
		T removedItem = theItems.getFirst();
		theItems.removeFirst();
		return removedItem;
	}
	
	public T peek() {
		return theItems.getFirst();
	}
	
	public boolean empty() {
		return theItems.size() == 0;
	}
}