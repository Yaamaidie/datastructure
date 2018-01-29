package util.graph;

import java.util.*;

/**
 * 图
 */
class Graph {
    //顶点数组
    int[] points;
    int vertexSize;
    //顶点对应的邻接表
    Map<Integer, Vertex> adjacentList;

    Graph(List<Vertex> vs) {
        vertexSize = vs.size();
        points = new int[vertexSize];
        adjacentList = new HashMap<>();

        int i = 0;
        for (Vertex v : vs) {
            points[i++] = v.point;
            adjacentList.put(v.point, v);
        }
    }

    /**
     * 按顶点名查找顶点
     * @param point
     * @return
     */
    Vertex findVertexByPoint(int point) {
        return adjacentList.get(point);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<Integer, Vertex> entry : adjacentList.entrySet()) {
            s.append(entry.getValue().toString());
        }
        return s.toString();
    }

}

class CycleFoundException extends Exception{}
