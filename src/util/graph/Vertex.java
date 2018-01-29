package util.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 顶点基类
 */
class Vertex {
    //顶点
    int point;
    //邻接表
    List<Integer> adList;

    Vertex(int p, List<Integer> al) {
        point = p;
        adList = new ArrayList<>(al.size());
        for (Integer a : al) {
            adList.add(a);
        }
    }
}
