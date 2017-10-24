package util.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序的实现
 */
public class MergeSort {

    public static void main(String[] args) {
        int len = 100000;
        //测试
        Integer[] a = new Integer[len];
        Integer[] b = new Integer[len];
        for (int i = len - 1; i >= 0; i--) {
            int tmp = new Random().nextInt(i + 1);
            a[i] = tmp;
            b[i] = tmp;
        }
        long t = System.currentTimeMillis();
        mergeSort(a);
        System.out.println("my merge sort for 10000 random integer costs: " + (System.currentTimeMillis() - t));

        long t2 = System.currentTimeMillis();
        Arrays.sort(b);
        System.out.println("sort in java lib costs: " + (System.currentTimeMillis() - t2));

    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        T[] tmpArray = (T[]) new Comparable[a.length];

        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmpArray, int leftPos, int rightPos,
                                                                int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        //主循环
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }

        //拷贝左半数组剩下的元素
        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = a[leftPos++];
        }

        //拷贝右半数组剩下的元素
        while (rightPos <= rightEnd) {
            tmpArray[tmpPos++] = a[rightPos++];
        }

        //拷贝tmpArray回a
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmpArray[rightEnd];
        }

    }
}
