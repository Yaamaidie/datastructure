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
        clear();
    }

    public void clear() {
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
     * 按树的大小求并
     * @param root1
     * @param root2
     */
    public void union2(int root1, int root2) {
        if (s[root1] <= s[root2]) { //root1的节点更多
            s[root1] += s[root2];
            s[root2] = root1;
        } else {
            s[root2] += s[root1];
            s[root1] = root2;
        }
    }

    /**
     * 按树的深度求并
     * @param root1
     * @param root2
     */
    public void union3(int root1, int root2) {
        if (s[root2] < s[root1]) { //root2的深度更大
            s[root1] = root2;
        } else {
            if (s[root1] == s[root2]) {
                s[root1]--;
            }
            s[root2] = root1;
        }
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

    /**
     * 路径压缩的find例程，路径压缩的效果是：从x到根的路径上的每一个节点都使其父节点成为该树的根
     * @param x
     * @return
     */
    public int find2(int x) {
        if (s[x] < 0) {
            return x;
        } else {
            return s[x] = find(s[x]);
        }

    }

    @Override
    public String toString() {
        return Arrays.toString(s);
    }

    public static void main(String[] args) {
        DisjSets ds = new DisjSets(8);

        //测试按大小求并
        ds.union2(4,5);
        ds.union2(6,7);
        ds.union2(4,6);
        ds.union2(3,4);
        System.out.println(ds);

        //测试按深度求并
        ds.clear();
        ds.union3(4,5);
        ds.union3(6,7);
        ds.union3(4,6);
        ds.union3(3,4);
        System.out.println(ds);
    }
}
