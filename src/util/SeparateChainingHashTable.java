package util;

import java.util.LinkedList;
import java.util.List;

/**
 * 使用分离链接法的HashTable
 * 
 * @author lee
 *
 */
public class SeparateChainingHashTable<T> {

	public static void main(String[] args) {
		SeparateChainingHashTable<Integer> H = new SeparateChainingHashTable<>();

		long startTime = System.currentTimeMillis();

		final int NUMS = 2000000;
		final int GAP = 37;

		System.out.println("Checking... (no more output means success)");

		for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
			H.insert(i);
		for (int i = 1; i < NUMS; i += 2)
			H.remove(i);

		for (int i = 2; i < NUMS; i += 2)
			if (!H.contains(i))
				System.out.println("Find fails " + i);

		for (int i = 1; i < NUMS; i += 2) {
			if (H.contains(i))
				System.out.println("OOPS!!! " + i);
		}

		long endTime = System.currentTimeMillis();

		System.out.println("Elapsed time: " + (endTime - startTime));

	}

	private static final int DEFAULT_TABLE_SIZE = 101;

	private List<T>[] theLists;
	private int currentSize;

	// 构造器
	public SeparateChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	/**
	 * 
	 * @param size
	 *            最接近的素数作为tableSize
	 */
	@SuppressWarnings("unchecked")
	public SeparateChainingHashTable(int size) {
		theLists = new LinkedList[nextPrime(size)];
		for (int i = 0; i < theLists.length; i++) {
			theLists[i] = new LinkedList<>();// 将数组中每一个链表初始化
		}
	}

	public int size() {
		return currentSize;
	}

	public void makeEmpty() {
		for (int i = 0; i < theLists.length; i++) {
			theLists[i].clear();
		}

		currentSize = 0;
	}

	public boolean contains(T x) {
		List<T> whichList = theLists[myHash(x)];
		return whichList.contains(x);
	}

	public void insert(T x) {
		List<T> whichList = theLists[myHash(x)];

		if (!whichList.contains(x)) {
			whichList.add(x);
			if (++currentSize > theLists.length) {
				rehash();
			}
		}
	}

	public void remove(T x) {
		List<T> whichList = theLists[myHash(x)];
		if (whichList.contains(x)) {
			whichList.remove(x);
			currentSize--;
		}
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		List<T>[] oldLists = theLists;

		// Create new double-sized, empty table
		theLists = new List[nextPrime(2 * theLists.length)];
		for (int j = 0; j < theLists.length; j++)
			theLists[j] = new LinkedList<>();

		// Copy table over
		currentSize = 0;
		for (List<T> list : oldLists)
			for (T item : list)
				insert(item);
	}

	private int myHash(T x) {
		int hashVal = x.hashCode();

		hashVal %= theLists.length;
		if (hashVal < 0) {
			hashVal += theLists.length;
		}

		return hashVal;
	}

	private static int nextPrime(int n) {
		if (n % 2 == 0) {
			n++;
		}

		for (; !isPrime(n); n += 2) {
			;
		}

		return n;
	}

	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;

		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}

}

class Employee {
	private String name;
	@SuppressWarnings("unused")
	private int age;

	public Employee(String n, int a) {
		name = n;
		age = a;
	}

	public int hashCode() {
		return name.hashCode();
	}

	public boolean equals(Employee other) {
		if (other == null) {
			return false;
		} else {
			return other.equals(name);
		}
	}

}
