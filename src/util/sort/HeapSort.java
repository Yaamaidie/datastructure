package util.sort;

import java.util.Random;

/**
 * 堆排序的实现和测试
 */
public class HeapSort {
    public static void main(String[] args) {
        int len = 1000000;
        Integer[] a = new Integer[len];
        Integer[] b = new Integer[len];
        int random;
        for (int i = 0; i < len; i++) {
            random = new Random().nextInt(len);
            a[i] = random;
            b[i] = random;
        }

        long t = System.currentTimeMillis();
        heapSort(a);
        System.out.println(System.currentTimeMillis() - t);

        long t2 = System.currentTimeMillis();
        MergeSort.mergeSort(b);
        System.out.println(System.currentTimeMillis() - t2);
    }

    public static <T extends Comparable<? super T>> void heapSort(T[] a) {
        for (int i = a.length / 2 - 1; i > 0; i--) {
            percDown(a, i, a.length);
        }
        for (int i = a.length - 1; i > 0; i--) {
            QuickSort.swapReferences(a, 0, i);
            percDown(a, 0, i);
        }
    }

    private static <T extends Comparable<? super T>> void percDown(T[] a, int i, int n) {
        int child;
        T tmp;

        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0) {
                child++;
            }
            if (tmp.compareTo(a[child]) < 0) {
                a[i] = a[child];
            } else {
                break;
            }
        }
        a[i] = tmp;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }
}
