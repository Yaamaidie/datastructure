package util.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序的实现
 */
public class quickSort {

    public static int CUTOFF = 20; //截止范围

    public static void main(String[] args) {
        int len = 10000000;
        Integer[] a = new Integer[len];
        Integer[] b = new Integer[len];
        int random;
        for (int i = 0; i < len; i++) {
            random = new Random().nextInt(len);
            a[i] = random;
            b[i] = random;
        }

        long t = System.currentTimeMillis();
        quickSort(a, 0, len - 1);
        System.out.println(System.currentTimeMillis() - t);

        long t2 = System.currentTimeMillis();
        MergeSort.mergeSort(b);
        System.out.println(System.currentTimeMillis() - t2);
    }

    /**
     * 快速排序主例程
     * @param a 数组对象
     * @param <T> 数组类型
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] a, int left, int right) {
        if (left + CUTOFF <= right) {
            T pivot = media3(a, left, right);

            //开始分割
            int i = left;
            int j = right - 1;
            for (;;) {
                while (a[++i].compareTo(pivot) < 0) {}
                while (a[--j].compareTo(pivot) > 0) {}
                if (i < j) {
                    swapReferences(a, i, j);
                } else {
                    break;
                }
            }

            //pivot回到中间
            swapReferences(a, i, right - 1);

            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        } else {
            insertionSort(a, left, right);
        }
    }


    //三数值中分法（Median-of-Three Partitioning）获得pivot
    //副作用是为分割策略做准备，包括保持a[left]，a[right]，a[center]有序，
    //然后交换a[center]和a[right-1]使得pivot放在适当的位置
    private static <T extends Comparable<? super T>> T media3(T[] a, int left, int right) {
        int center = (left + right) / 2;

        if (a[center].compareTo(a[left]) < 0) {
            swapReferences(a, left, center);
        }
        if (a[right].compareTo(a[left]) < 0) {
            swapReferences(a, left, right);
        }
        if (a[right].compareTo(a[center]) < 0) {
            swapReferences(a, center, right);
        }

        //将pivot放在right-1的位置
        swapReferences(a, center, right - 1);

        return a[right - 1];
    }

    //交换
    private static <T extends Comparable<? super T>> void swapReferences(T[] a, int idx1, int idx2) {
        T tmp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = tmp;
    }

    //插入排序
    private static <T extends Comparable<? super T>> void insertionSort(T[] a, int left, int right) {
        int j;

        for (int i = left + 1; i <= right; i++) {
            T tmp = a[i];
            for (j = i; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }
}
