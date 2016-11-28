package exercise.ch3;

public class Ex3_24<T> {
	private T[] theItems;
	private int theSize;
	private static boolean isFisrt;
	private int topOfFirstStack, topOfSecondStack;
	
	private static Ex3_24<Object> s =new Ex3_24<Object>();
	
	public Ex3_24() {
		ensureCapacity(10);
		clear();
	}
	
	public static Ex3_24<Object> getFirstStack() {
		isFisrt = true;
		return s;
	}
	
	public static Ex3_24<Object> getSecondStack() {
		isFisrt = false;
		return s;
	}
	
	public void clear() {
		topOfFirstStack = 0;
		topOfSecondStack = theItems.length - 1;
		theSize = 0;
	}
	
	public boolean empty() {
		return theSize == 0;
	}
	
	public T pop() {
		if (theSize == 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			theSize--;
			if (isFisrt) {
				return theItems[topOfFirstStack--];
			} else {
				return theItems[topOfSecondStack++];
			}
		}
	}
	
	public void push(T newValue) {
		if (theSize == theItems.length) {
			throw new RuntimeException("栈满");
		} else {
			theSize++;
			if (isFisrt) {
				theItems[++topOfFirstStack] = newValue;
			} else {
				theItems[--topOfSecondStack] = newValue;
			}
		}
	}
	
	public T peek() {
		if (theSize == 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			if (isFisrt) {
				return theItems[topOfFirstStack];
			} else {
				return theItems[topOfSecondStack];
			}
		}
	}
	@SuppressWarnings("unchecked")
	public void ensureCapacity(int newCapacity) {
		theItems = (T[]) new Object[newCapacity];
	}
	
}
