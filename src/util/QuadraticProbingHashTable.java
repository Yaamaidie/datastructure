package util;

/**
 * 二次探测法实现的HashTable
 * 
 * @author lee
 *
 */
public class QuadraticProbingHashTable<T> {

	public static void main(String[] args) {
		QuadraticProbingHashTable<String> H = new QuadraticProbingHashTable<>();

		long startTime = System.currentTimeMillis();

		final int NUMS = 2000000;
		final int GAP = 37;

		System.out.println("Checking... (no more output means success)");

		for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
			H.insert("" + i);
		for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
		for (int i1 = 1; i1 < NUMS; i1 += 2)
			H.remove("" + i1);

		for (int i = 2; i < NUMS; i += 2)
			if (!H.contains("" + i))
				System.out.println("Find fails " + i);

		for (int i = 1; i < NUMS; i += 2) {
			if (H.contains("" + i))
				System.out.println("OOPS!!! " + i);
		}

		long endTime = System.currentTimeMillis();

		System.out.println("Elapsed time: " + (endTime - startTime));

	}

	private static final int DEFAULT_TABLE_SIZE = 11;

	private int currentSize;
	private HashEntry<T>[] array;

	private static class HashEntry<E> {
		public E element;
		public boolean isActive;// 如果被标记删除，则为false

		public HashEntry(E e, boolean i) {
			element = e;
			isActive = i;
		}
	}

	public QuadraticProbingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public QuadraticProbingHashTable(int size) {
		allocateArray(size);
		makeEmpty();
	}

	public void makeEmpty() {
		currentSize = 0;
		for (int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}

	public boolean contains(T x) {
		int currentPos = findPos(x);
		return isActive(currentPos);
	}

	public void insert(T x) {
		int currentPos = findPos(x);
		if (isActive(currentPos)) {// 重复插入直接返回
			return;
		} else {
			array[currentPos] = new HashEntry<>(x, true);
			if (++currentSize > array.length / 2) {
				rehash();
			}
		}
	}

	public void remove(T x) {
		int currentPos = findPos(x);
		if (isActive(currentPos)) {
			array[currentPos].isActive = false;
		}
	}

	private void rehash() {
		HashEntry<T>[] oldArray = array;

		allocateArray(2 * oldArray.length);

		for (HashEntry<T> entry : oldArray) {
			if (entry != null && entry.isActive) {
				insert(entry.element);
			}
		}
	}

	// 平方函数的定义可得：f(i) = f(i - 1) + 2i - 1 = i ^ 2;
	// 避免了乘法和除法运算，更加快捷
	private int findPos(T x) {
		int offset = 1;
		int currentPos = myHash(x);

		// 要么找到了x，实际位置为x所在的位置，要么是没找到x，实际位置为的第一个null的位置，以备插入
		while (array[currentPos] != null
				&& !array[currentPos].element.equals(x)) {
			currentPos += offset;
			offset += 2;

			if (currentPos >= array.length) {// 越界后，拉回到数组
				currentPos -= array.length;
			}
		}

		return currentPos;
	}

	private boolean isActive(int currentPos) {
		return array[currentPos] != null && array[currentPos].isActive;
	}

	@SuppressWarnings("unchecked")
	private void allocateArray(int arraySize) {
		array = new HashEntry[nextPrime(arraySize)];
	}

	private int myHash(T x) {
		int hashVal = x.hashCode();

		hashVal %= array.length;
		if (hashVal < 0) {
			hashVal += array.length;
		}

		return hashVal;
	}

	private static int nextPrime(int n) {
		if (n % 2 == 0) {// 确保是奇数
			n++;
		}

		while (!isPrime(n)) {// 每次推进2个数
			n += 2;
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
