package util.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图
 */
public class Graph {
    //顶点数组
    private int[] points;
    private int vertexSize;
    //顶点对应的邻接表
    private Map<Integer, Vertex> adjacentList;

    public Graph(List<Vertex> vs) {
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
     * 拓扑排序
     * 时间复杂度为O(|V|^2)，|V|为顶点集合的大小
     * @throws CycleFoundException
     */
    public void topsort() throws CycleFoundException {
        for (int counter = 0; counter < vertexSize; counter++) {
            Vertex v = findVertexOfIndegreeZero();
            if (v == null) {
                throw new CycleFoundException();
            }
            v.topNum = counter;
            for (int point : v.adList) {
                findVertexByPoint(point).indegree--;
            }
        }
    }

    /**
     * 按顶点名查找顶点
     * @param point
     * @return
     */
    public Vertex findVertexByPoint(int point) {
        return adjacentList.get(point);
    }


    /**
     * 扫描顶点数组,寻找一个尚未被分配拓扑编号的入度为0的顶点
     * @return
     */
    public Vertex findVertexOfIndegreeZero() {
        boolean found = false;
        Vertex v = null;
        for (int i : points) {
            v = findVertexByPoint(i);
            if (v.topNum == -1 && v.indegree == 0) {
                found = true;
                break;
            }
        }
        if (found) {
            return v;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<Integer, Vertex> entry : adjacentList.entrySet()) {
            s.append(entry.getValue().toString());
        }
        return s.toString();
    }

    /**
     * 构造一个简单的图作为测试用例
     * @return
     */
    public static Graph createTestGraph() {

        List<Vertex> vertices = new ArrayList<>();
        List<Integer> adjacencyList = new ArrayList<>(3);

        int point;
        Vertex v;

        point = 1;
        adjacencyList.add(2);
        adjacencyList.add(4);
        adjacencyList.add(3);
        v = new Vertex(point, 0, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 2;
        adjacencyList.add(4);
        adjacencyList.add(5);
        v = new Vertex(point, 1, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 3;
        adjacencyList.add(6);
        v = new Vertex(point, 2, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 4;
        adjacencyList.add(6);
        adjacencyList.add(7);
        adjacencyList.add(3);
        v = new Vertex(point,3, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 5;
        adjacencyList.add(4);
        adjacencyList.add(7);
        v = new Vertex(point, 1, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 6;
        v = new Vertex(point, 3, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 7;
        adjacencyList.add(6);
        v = new Vertex(point, 2, -1, adjacencyList);
        vertices.add(v);

        Graph g = new Graph(vertices);

        return g;
    }

    public static void main(String[] args) {
        Graph g = createTestGraph();
        System.out.println(g);
    }

}

/**
 * 顶点类，包括顶点、邻接表、入度
 */
class Vertex {
    //顶点
    int point;
    //入度
    int indegree;
    //拓扑编号
    int topNum;
    //邻接表
    List<Integer> adList;

    Vertex(int p, List<Integer> al) {
        this(p, 0, -1, al);
    }

    Vertex(int p, int i, int tn, List<Integer> al) {
        point = p;
        indegree = i;
        topNum = tn;
        adList = new ArrayList<>(al.size());
        for (Integer a : al) {
            adList.add(a);
        }

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(point).append(": topNum: ").append(topNum).append("; ");
        s.append("adList:");
        for (Integer p : adList) {
            s.append(" " + p);
        }
        s.append(";\n");

        return s.toString();
    }
}

class CycleFoundException extends Exception{}
