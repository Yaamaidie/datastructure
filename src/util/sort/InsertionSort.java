package util.sort;

import java.util.Random;

/**
 * 插入排序的实现和测试
 */
public class InsertionSort {

    public static void main(String[] args) {
        final int LEN = 10000;
        Integer[] a = new Integer[LEN];
        for (int i = 0; i < LEN; i++) {
            a[i] = new Random().nextInt(LEN);
        }

        long t = System.currentTimeMillis();
        insertionSort(a);
        System.out.println("insertion sort for integer array consisting of N numbers costs: " + (System.currentTimeMillis() - t));
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] arr, int left, int right) {
        int j;

        for (int p = left + 1; p <= right; p ++) {
            T tmp = arr[p];
            for (j = p; j > 0 && tmp.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
        insertionSort(arr, 0, arr.length - 1);
    }
}
