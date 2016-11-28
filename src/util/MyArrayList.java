package util;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T> {
	
	// 数组初始容量
	private static final int DEFAULT_CAPACITY = 10;
	// 数组结构改变次数：remove和add
	private int modCount = 0;
	// 存储在数组中的当前项数
	private int theSize;
	// 当前数组
	private T[] theItems;

	// 构造器
	public MyArrayList() {
		doClear();
	}
	
	public MyArrayList(int s) {
		theSize = 0;
		ensureCapacity(s);
	}
	// 初始化
	public void clear() {
		doClear();
	}
	
	public void print() {
		System.out.print(java.util.Arrays.toString(toArray()));
	}
	
	public Object[] toArray() {
		Object[] result = new Object[theSize];
		for (int i = 0; i < theSize; i++) {
			result[i] = theItems[i];
		}
		return result;
	}
	
	private void doClear() {
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}

	// 数组拷贝
	@SuppressWarnings("unchecked")
	public void ensureCapacity(int newCapacity) {
		if (newCapacity < theSize) {
			return;
		} else {
			T[] old = theItems;
			theItems = (T[]) new Object[newCapacity];
			for (int i = 0; i < size(); i++) {
				theItems[i] = old[i];
			}
		}
	}

	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return theSize == 0;
	}

	public T get(int idx) {
		if (idx < 0 || idx >= theItems.length) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			return theItems[idx];
		}
	}

	public T set(int idx, T newVal) {
		if (idx < 0 || idx >= theItems.length) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			T old = theItems[idx];
			theItems[idx] = newVal;
			return old;
		}
	}

	public boolean add(T t) {
		add(size(), t);
		return true;
	}

	public void add(int idx, T t) {
		if (theItems.length == theSize) {
			ensureCapacity(size() * 2 + 1);
		}
		for (int i = theSize; i > idx; i--) {
			theItems[i] = theItems[i - 1];
		}
		theItems[idx] = t;
		theSize++;
		modCount++;
	}

	public T remove(int idx) {
		T removedTtem = theItems[idx];
		for (int i = idx; i < size() - 1; i++) {
			theItems[i] = theItems[i + 1];
		}
		theSize--;
		modCount++;
		return removedTtem;
	}

	// 习题3.13 现存的方法iterator可以放回新构造的ListIterator
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

	// 习题3.13 listIterator方法将返回新构造的ListIterator
	public ListIterator<T> listIterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements ListIterator<T> {
		private int current = 0;
		private int expectedModCoutn = modCount;
		private boolean okToRemove = false;
		// 是否倒着遍历
		boolean backwards = false;

		public boolean hasNext() {
			return current < size();
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (expectedModCoutn != modCount) {
				throw new ConcurrentModificationException();
			}
			backwards = false;
			okToRemove = true;
			return theItems[current++];// 返回current元素然后将其加一
		}

		@Override
		public boolean hasPrevious() {
			return current > 0;
		}

		@Override
		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			if (expectedModCoutn != modCount) {
				throw new ConcurrentModificationException();
			}
			backwards = true;
			okToRemove = true;
			return theItems[--current];
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T newVal) {
			if (expectedModCoutn != modCount) {
				throw new ConcurrentModificationException();
			}
			MyArrayList.this.set(current, newVal);
		}

		@Override
		public void add(T x) {
			if (expectedModCoutn != modCount) {
				throw new ConcurrentModificationException();
			}
			MyArrayList.this.add(current++, x);
			expectedModCoutn++;
		}

		// remove方法的原则就是不改变下一项
		public void remove() {
			if (expectedModCoutn != modCount) {
				throw new ConcurrentModificationException();
			}
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			if (backwards) {
				MyArrayList.this.remove(current--);
			} else {
				MyArrayList.this.remove(--current);
			}
			expectedModCoutn++;
			okToRemove = false;
		}
	}

	// 习题3.9 时间复杂度为O(n)
	public void addAll(Iterable<? extends T> items) {
		Iterator<? extends T> iterator = items.iterator();
		while (iterator.hasNext()) {
			add(iterator.next());
		}
	}

	// 习题3.10 时间复杂度为O(MN)
	public void removeAll(Iterable<? extends T> items) {
		T item, element;
		Iterator<? extends T> iterItems = items.iterator();
		while (iterItems.hasNext()) {
			item = iterItems.next();
			Iterator<? extends T> iter = iterator();
			while (iter.hasNext()) {
				element = iter.next();
				if (element.equals(item)) {
					iter.remove();
				}
			}
		}
	}

	// 习题3.16反向iterator()
	public Iterator<T> reverseIterator() {
		return new ArrayListReverseIterator();
	}

	private class ArrayListReverseIterator implements Iterator<T> {
		private int current = size() - 1;
		private int expectedCount = modCount;
		private boolean okToRemove = false;
		
		public boolean hasNext() {
			return current >= 0;
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (expectedCount != modCount) {
				throw new ConcurrentModificationException();
			}
			okToRemove = true;
			return theItems[current--];
		}

		public void remove() {
			if (expectedCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			MyArrayList.this.remove(--current);
			expectedCount++;
			okToRemove = false;
		}
	}
}
