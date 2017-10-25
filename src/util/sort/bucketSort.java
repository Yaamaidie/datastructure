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
        final int LEN = 1000000;
        final int P = 6;
        String[] arr = new String[LEN];
        for (int i = 0; i < LEN; i++) {
            arr[i] = getRandomString(P);
        }

        long t = System.currentTimeMillis();
        radixSortA(arr, P);
//        Arrays.sort(arr);
        System.out.println(System.currentTimeMillis() - t);
        System.out.println(Arrays.toString(arr));
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
