package util.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 桶排序和基数排序
 * 通过对字符串排序，演示了基数排序的两种实现，一种使用ArrayList作为桶，另一种使用计数器
 */
public class bucketSort {

    public static void main(String[] args) {
        final int LEN = 10000000;
        final int P = 6;
        String[] arr = new String[LEN];
        String[] arr2 = new String[LEN];
        String random;
        for (int i = 0; i < LEN; i++) {
            random = getRandomString(P);
            arr[i] = random;
            arr2[i] = random;
        }

        long t = System.currentTimeMillis();
        radixSortA(arr, P);
//        Arrays.sort(arr);
        System.out.println(System.currentTimeMillis() - t);

        long t2 = System.currentTimeMillis();
        countingRadixSort(arr, P);
        System.out.println(System.currentTimeMillis() - t2);

//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 使用ArrayList作为桶
     * @param arr
     * @param stringLen
     */
    public static void radixSortA(String[] arr, int stringLen) {
        //假设所有字符都是ASCII码，位于Unicode字符集的前256位
        final int BUCKETS = 256;
        List[] buckets = new ArrayList[BUCKETS]; //256个桶的数组，每个桶是一个list

        for (int i = 0; i < BUCKETS; i++) {
            buckets[i] = new ArrayList<String>();
        }

        for (int pos = stringLen - 1; pos >= 0; pos--) {//p趟
            for (String s : arr) {
                buckets[s.charAt(pos)].add(s);
            }

            //将结果写回
            int idx = 0;
            for (List<String> thisBucket : buckets) {
                for (String s : thisBucket) {
                    arr[idx++] = s;
                }

                //每写回完一个桶，将桶清空
                thisBucket.clear();
            }
        }
    }

    /**
     * 使用计数器
     * @param stringLen
     */
    public static void countingRadixSort(String[] arr, int stringLen) {
        final int BUCKETS = 256;

        int N = arr.length;
        String[] buffer = new String[N];

        String[] in = arr;
        String[] out = buffer;

        for (int pos = stringLen - 1; pos >= 0; pos--) {
            //计数器，count[i + 1]表示桶i中元素的个数
            int[] count = new int[BUCKETS + 1];

            //扫描一遍输入数组，设置各个桶在计数器中的值
            for (int i = 0; i < N; i++) {
                count[in[i].charAt(pos) + 1]++; //i + 1 表示第i个桶的元素的个数
            }
            //让count[i]的值表示严格小于i的元素的个数
            for (int b = 1; b <= BUCKETS; b++) {
                count[b] += count[b - 1];
            }
            //最后的扫描。第一次见到i时，count[i]告诉我们将要写入的数组位置，然后count[i]++
            for (int i = 0; i < N; i++) {
                out[count[in[i].charAt(pos)]++] = in[i];
            }

            //交换in和out的角色
            String[] tmp = in;
            in = out;
            out = tmp;
        }

        //如果趟数是偶数次，最后out引用的是arr，直接返回；否则out引用的是临时数组buffer，需要将buffer复制回arr
        if (stringLen % 2 == 1) {
            for (int i = 0; i < arr.length; i++) {
                in[i] = out[i];
            }
        }
    }

    /**
     * 帮助函数，用来生成指定长度的随机字符串
     * @param length
     * @return
     */
    private static String getRandomString(int length) { //length表示生成字符串的长度
        int left = 65;
        int right = 90;
        int numLetter = right - left + 1;

        final char[] ASCII = new char[numLetter];
        for (char i = 0; i < ASCII.length; i++) {
            ASCII[i] = (char)(left + i);
        }
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(numLetter);
            sb.append(ASCII[number]);
        }
        return sb.toString();
    }
}
