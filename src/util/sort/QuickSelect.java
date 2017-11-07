package util.sort;

import java.util.Random;

/**
 * 快速选择的实现和测试
 */
public class QuickSelect {

    public final static int CUTTOFF = 10;

    public static void main(String[] args) {
        int len = 100000;
        Integer[] a = new Integer[len];
        Integer[] b = new Integer[len];
        int random;
        for (int i = 0; i < len; i++) {
            random = new Random().nextInt(len);
            a[i] = random;
            b[i] = random;
        }

        int k = new Random().nextInt(len);

        long t = System.currentTimeMillis();
        Integer x = quickSelect(a, k);
        System.out.println("the " + k + "-th min is: " + x + " and it costs: " + (System.currentTimeMillis() - t) + " ms");

        MergeSort.mergeSort(b);
        System.out.println("the " + k + "-th min by sort is: " + b[k - 1]);
    }

    /**
     * 使用快速选择算法从输入数组中查找第k个最小的值
     * @param a
     * @param k
     * @param <T>
     */
    public static <T extends Comparable<? super T>> T quickSelect(T[] a, int k) {
        return quickSelect(a, 0, a.length - 1, k);
    }

    /**
     * 快速选择的主例程
     * @param left
     * @param right
     * @param k
     * @param <T>
     */
    private static <T extends Comparable<? super T>> T quickSelect(T[] a, int left, int right, int k) {
        if (left + CUTTOFF <= right) {
            T pivot = QuickSort.media3(a, left, right);

            //开始分割
            int i = left, j = right - 1;
            for (;;) {
                while (a[++i].compareTo(pivot) < 0) {}
                while (a[--j].compareTo(pivot) > 0) {}

                if (i < j) {
                    QuickSort.swapReferences(a, i, j);
                } else {
                    break;
                }
            }

            QuickSort.swapReferences(a, i, right - 1); //恢复pivot的位置

            if (k <= i) {
                return quickSelect(a, left, i - 1, k);
            } else if (k > i + 1) {
                return quickSelect(a, i + 1, right, k);
            } else {
                return a[i];
            }

        } else {
            InsertionSort.insertionSort(a, left, right);
            return a[k-1];
        }
    }
}

