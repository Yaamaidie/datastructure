package util;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

	// 节点类
	private static class Node<T> {
		public Node(T d, Node<T> p, Node<T> n) {
			data = d;
			prev = p;
			next = n;
		}

		public T data;
		public Node<T> prev;
		public Node<T> next;
	}

	private int theSize;
	private int modCount = 0;
	private Node<T> beginMarker;
	private Node<T> endMarker;

	// 初始化
	public MyLinkedList() {
		doClear();
	}

	public void clear() {
		doClear();
	}

	private void doClear() {
		theSize = 0;
		modCount = 0;
		beginMarker = new Node<T>(null, null, null);
		endMarker = new Node<T>(null, beginMarker, null);
		beginMarker.next = endMarker;
	}

	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return theSize == 0;
	}
	
	public void print() {
		System.out.print(java.util.Arrays.toString(toArray()));
	}
	
	public Object[] toArray() {
		Object[] result = new Object[theSize];
		int i = 0;
		for (Node<T> x = beginMarker.next; x != null && x != endMarker; x = x.next) {
			result[i++] = x.data;
		}
		return result;
	}
	
	public boolean add(T t) {
		add(size(), t);
		return true;
	};

	public void add(int idx, T t) {
		addBefore(getNode(idx, 0, size()), t);
	}
	
	public T get(int idx) {
		return getNode(idx).data;
	}
	
	public T set(int idx, T newVal) {
		Node<T> node = getNode(idx);
		T oldVal = node.data;
		node.data = newVal;
		return oldVal;
	}
	
	public T remove(int idx) {
		return remove(getNode(idx));
	}
	
	public Node<T> getNode(int idx) {
		return getNode(idx, 0, theSize -1);
	}
	
	//按idx的位置，决定从前还是后遍历list
	private Node<T> getNode(int idx, int lower, int upper) {
		Node<T> p;//要获取的节点
		if (idx < 0 || idx > upper) {
			throw new IndexOutOfBoundsException();
		} else {
			if (idx < size() / 2) {//beginMarker开始
				p = beginMarker.next;
				for (int i = 0; i < idx; i++) {
					p = p.next;
				}
			} else {
				p = endMarker;
				for (int i = size(); i > idx; i--) {
					p = p.prev;
				}
			}
			return p;
		}
	}
	
	private void addBefore(Node<T> p, T t) {
		Node<T> newNode = new Node<T>(t, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}
	
	private T remove(Node<T> p) {
		p.prev.next = p.next;
		p.next.prev = p.prev;
		theSize--;
		modCount++;
		return p.data;
	}
	/**
	 * 习题3.14
	 * 现存的方法iterator可以放回新构造的ListIterator
	 * listIterator方法将返回新构造的ListIterator
	 */
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	public ListIterator<T> listIterator() {
		return new LinkedListIterator();
	}
	/**
	 * 注意调用previous方法并不会改变current变量的意义，current始终代表当前位置
	 * next方法和previous方法的区别在于，next方法调用时，返回的始终是current.data
	 * 而previous方法返回的是始终是current.prev.data
	 */
	private class LinkedListIterator implements ListIterator<T>{
		
		private Node<T> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		
		public boolean hasNext() {
			return current != endMarker;
		}

		public T next() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			T nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			
			MyLinkedList.this.remove(current.prev);
			expectedModCount++;
			okToRemove = false;
		}

		@Override
		public boolean hasPrevious() {
			return current.prev != beginMarker;
		}

		@Override
		public T previous() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			current = current.prev;
			T previousItem =current.data;
			okToRemove = true;
			return previousItem;
		}
		@Override
		public void set(T newVal) {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			current.data = newVal;
		}

		@Override
		public void add(T e) {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			MyLinkedList.this.addBefore(current, e);
		}
		
		@Override
		public int nextIndex() {
			throw new  UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new  UnsupportedOperationException();
		}
	}
	
	/**
	 * 练习3.3
	 */
	public boolean contains(T t) {
		Node<T> p = beginMarker.next;
		while (p != endMarker && !(p.data.equals(t))) {
			p = p.next;
		}
		
		return (p != endMarker);
	}
	
	/**
	 * 练习3.18 调用已有的add， remove，getNode例程实现 addFirst， addLast，removeFirst，removeLast，getFirst，getLast
	 */
	public void addFirst(T data) {
		addBefore(beginMarker.next, data);
	}
	
	public void addLast(T data) {
		addBefore(endMarker, data);
	}
	
	public T removeFirst() {
		return remove(beginMarker.next);
	}
	
	public T removeLast() {
		return remove(endMarker.prev);
	}
	
	public T getFirst() {
		return getNode(0).data;
	}
	
	public T getLast() {
		return getNode(size() - 1).data;
	}
	
	/**
	 * 练习3.29
	 */
	public void reverseList() {
		Node<T> prevPos, nextPos, currPos;
		
		prevPos = null;
		currPos = beginMarker.next;
		nextPos = currPos.next;
		
		while (nextPos != null) {
			currPos.next = prevPos;
			prevPos = currPos;
			currPos = nextPos;
			nextPos = nextPos.next;
		}
		
		currPos.next = prevPos;
		beginMarker.next = currPos;
	}
}
