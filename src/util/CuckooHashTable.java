package util;

import java.util.Random;

/**
 * Created by lee on 2017/7/25.
 */
public class CuckooHashTable <T> {
    private static final double MAX_LOAD = 0.4;
    private static final int DEFAULT_TABLE_SIZE = 101;
    private static final int ALLOWED_REHASHES = 1; //替换时间过长，将执行rehash的次数

    private final HashFamily<? super T> hashFunctions;
    private final int numHashFunctions;
    private T[] array;
    private int currentSize;

    public CuckooHashTable(HashFamily<? super T> hf) {
        this(hf, DEFAULT_TABLE_SIZE);
    }

    public CuckooHashTable(HashFamily<? super T> hf, int size) {
        allocateArray(nextPrime(size)); //分配数组
        hashFunctions = hf;
        numHashFunctions = hf.getNumberOfFunctions();
    }

    /**
     * @param x the item to search for
     * @return true if item is founc
     */
    public boolean contains(T x) {
        return findPos(x) != -1;
    }

    /**
     *
     * @param x the item to remove
     * @return ture if item was found
     */
    public boolean remove(T x) {
        int pos = findPos(x);

        if (pos != -1) {
            array[pos] = null;
            currentSize--;
        }

        return pos != -1;
    }

    public boolean insert(T x) {
        if (contains(x)) {
            return false;
        }

        if (currentSize >= array.length * MAX_LOAD) {
            expand();
        }

        return insertHelper1(x);
    }

    //跟踪已经为某一次插入尝试了多少次再散列
    private int rehashes = 0;
    private Random r = new Random();

    private boolean insertHelper1(T x) {
        final int COUNT_LIMIT = 100; //每次插入最多进行100次替换

        while (true) {
            int lastPos = -1;
            int pos;

            for (int count = 0; count < COUNT_LIMIT; count++) {
                for (int i = 0; i < numHashFunctions; i++) { //算出所有有效位置
                    pos = myHash(x, i);

                    if (array[pos] == null) { //该有效位置空着，则插进去，并返回true
                        array[pos] = x;
                        currentSize++;
                        return true;
                    }
                }

                //没有任何一个位置是空着的，替换一个随机的位置
                int i = 0;
                do {
                    pos = myHash(x, r.nextInt(numHashFunctions));
                } while (pos == lastPos && i++ < 5);

                T tmp = array[lastPos = pos];
                array[pos] = x;
                x = tmp;
            }

            if (++rehashes > ALLOWED_REHASHES) {
                expand();
                rehashes = 0;
            } else {
                rehash(); //相同的table大小，新的hash函数
            }
        }
    }

    //扩展后的最大currentsize为当前的length
    private void expand() {
        rehash((int) (array.length / MAX_LOAD));
    }

    private void rehash() {
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }

    private void rehash(int newLength) {
        T[] oldArray = array;
        allocateArray(nextPrime(newLength));
        currentSize = 0;

        for (T str : oldArray) {
            if (str != null) {
                insert(str);
            }
        }
    }

    private int findPos(T x) {
        for (int i = 0; i < numHashFunctions; i++) {
            int pos = myHash(x, i);
            if (array[pos] != null && array[pos].equals(x)) {
                return pos;
            }
        }
        return -1;
    }

    private int myHash(T x, int which) {
        int hash = hashFunctions.hash(x, which);

        hash %= array.length;
        if (hash < 0) {
            hash += array.length;
        }

        return hash;
    }

    //分配数组
    private void allocateArray(int arraySize) {
        array = (T[]) new Object[arraySize];
    }

    //下一个最小的素数
    private int nextPrime(int n) {
        if (n % 2 == 0) {
            n++;
        }
        while (!isPrime(n)) {
            n += 2;
        }
        return n;
    }

    private boolean isPrime(int n) {
        if (n ==2 || n == 3) {
            return true;
        }
        if (n == 1 || n % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= n; i += 2) { //奇数没有偶数的因子
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
