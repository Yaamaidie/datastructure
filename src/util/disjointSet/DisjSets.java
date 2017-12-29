package util.disjointSet;

import java.util.Arrays;

/**
 * 不相交集类
 * @author jun.li
 */
public class DisjSets {
    private int[] s;

    public DisjSets(int numElements) {
        s = new int[numElements];
        Arrays.fill(s, -1);
    }

    /**
     * 对两个集合求并
     * 简单起见，假设root1和root2是不同的并且分别代表集合的名字
     * @param root1 集合1的root
     * @param root2 集合2的root
     */
    public void union(int root1, int root2) {
        s[root2] = root1;
    }

    /**
     * 执行一次find
     * @param x 要搜索的元素
     * @return 包含元素x的集合
     */
    public int find(int x) {
        if (s[x] < 0) {
            return x;
        } else {
            return find(s[x]);
        }
    }
}
