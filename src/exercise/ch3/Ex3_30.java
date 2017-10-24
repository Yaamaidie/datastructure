package exercise.ch3;

import java.util.Iterator;

import util.MyLinkedList;

/**
 * 自调整表的数组和链表实现
 * @author lee
 *
 */
public class Ex3_30 {
	public static void main(String[] args) {
		//测试数组实现
		SelfAdjustArray<Integer> array = new SelfAdjustArray<Integer>();
		for (int i = 0; i < 10; i++) {
			array.add(i);
		}
		array.find(3);
		//测试链表实现
		SelfAdjustList<Integer> list = new SelfAdjustList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		list.find(3);
		System.exit(0);
		
	}
}
/**
 * 数组实现
 * @author lee
 *
 * @param <T>
 */
class SelfAdjustArray<T> implements Iterable<T> {
	public static final int DEFAULT_CAPACITY = 10;
	private T[] theItems;
	private int theSize, topOfList;
	
	@SuppressWarnings("unchecked")
	public SelfAdjustArray() {
		theSize = 0;
		topOfList = -1;
		theItems = (T[]) new Object[DEFAULT_CAPACITY];
	}
	
	/**
	 * 添加在数组前端进行
	 * @param x
	 */
	public void add(T x) {
		theItems[++topOfList] = x;
	}
	
	/**
	 * 当运行find例程时，find的元素将会被放到数组前端
	 * @param newValue
	 */
	public T find(int idx) {
		T need = theItems[idx];
		for (int i = idx; i < topOfList; i++) {
			theItems[i] = theItems[i + 1];
		}
		theItems[topOfList] = need;
		return theItems[idx];
	}
	
	@SuppressWarnings("unchecked")
	public void ensureCapacity(int newValue) {
		if (newValue < theItems.length) {
			return;
		} else {
			T[] old = theItems;
			theItems = (T[]) new Object[newValue];
			for (int i = 0; i < theSize; i++) {
				theItems[i] = old[i];
			}
		}
	}
	
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<T>{
		private int current = 0;
		public boolean hasNext() {
			return current < theSize; 
		}

		public T next() {
			return theItems[current++];
		}

		@Override
		public void remove() {

		}
	}
}
//链表实现
class SelfAdjustList<T> {
	private MyLinkedList<T> L;
	
	public SelfAdjustList() {
		L = new MyLinkedList<T>();
	}
	
	public void add(T x) {
		L.addLast(x);
	}
	
	public T find(int idx) {
		T need = L.get(idx);
		L.remove(idx);
		L.addLast(need);
		return need;
	}
	
}
