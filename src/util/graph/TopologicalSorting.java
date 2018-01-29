package util.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 拓扑排序
 * @author jun.li
 */
public class TopologicalSorting extends Graph{

    public TopologicalSorting(List<Vertex> vs) {
        super(vs);
    }

    /**
     * 简单拓扑排序
     * 时间复杂度为O(|V|^2)，|V|为顶点集合的大小
     * @throws CycleFoundException
     */
    public void topsort() throws CycleFoundException {
        System.out.print("topNum: ");
        for (int counter = 0; counter < vertexSize; counter++) {
            TopoVertex v = findVertexOfIndegreeZero();
            if (v == null) {
                throw new CycleFoundException();
            }
            v.topNum = counter;
            for (int point : v.adList) {
                ((TopoVertex)findVertexByPoint(point)).indegree--;
            }

            System.out.print(" " + v.point);
        }
        System.out.println();
    }

    /**
     * 改进的拓扑排序
     * 时间复杂度为O(|V| + |E|)，|V|为顶点集合的大小，|E|为边集合的大小
     * @throws CycleFoundException
     */
    public void topsort2() throws CycleFoundException {
        Queue<Vertex> q = new LinkedList<>();
        int counter = 0;

        //初始时将所有（未分配topNum）的入度为0的顶点压入栈中
        for (Vertex v : adjacentList.values()) {
            if (((TopoVertex)v).indegree == 0) {
                q.offer(v);
            }
        }

        System.out.print("topNum: ");
        while (!q.isEmpty()) {
            //弹出并删除一个入度为0的顶点
            Vertex v = q.poll();
            ((TopoVertex)v).topNum = counter++;

            //注意到内层循环对每条边最多执行一次，且每条边不会重复执行，所以内层循环的总时间复杂度为O(|E|)
            for (Integer p : v.adList) {
                Vertex w = findVertexByPoint(p);
                if (--((TopoVertex)w).indegree == 0) {
                    q.offer(w);
                }
            }

            System.out.print(" " + v.point);
        }
        System.out.println();

        if (counter != vertexSize) {
            throw new CycleFoundException();
        }

    }

    /**
     * 扫描顶点数组,寻找一个尚未被分配拓扑编号的入度为0的顶点
     * @return
     */
    public  TopoVertex findVertexOfIndegreeZero() {
        boolean found = false;
        TopoVertex v = null;
        for (int i : points) {
            v = (TopoVertex)findVertexByPoint(i);
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
    /**
     * 构造一个简单的图作为测试用例
     * @return
     */
    static TopologicalSorting createTestGraph() {

        List<Vertex> vertices = new ArrayList<>();
        List<Integer> adjacencyList = new ArrayList<>(3);

        int point;
        Vertex v;

        point = 1;
        adjacencyList.add(2);
        adjacencyList.add(4);
        adjacencyList.add(3);
        v = new TopoVertex(point, 0, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 2;
        adjacencyList.add(4);
        adjacencyList.add(5);
        v = new TopoVertex(point, 1, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 3;
        adjacencyList.add(6);
        v = new TopoVertex(point, 2, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 4;
        adjacencyList.add(6);
        adjacencyList.add(7);
        adjacencyList.add(3);
        v = new TopoVertex(point,3, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 5;
        adjacencyList.add(4);
        adjacencyList.add(7);
        v = new TopoVertex(point, 1, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 6;
        v = new TopoVertex(point, 3, -1, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 7;
        adjacencyList.add(6);
        v = new TopoVertex(point, 2, -1, adjacencyList);
        vertices.add(v);

        TopologicalSorting g = new TopologicalSorting(vertices);

        return g;
    }

    public static void main(String[] args) throws CycleFoundException {
        TopologicalSorting g = createTestGraph();
        TopologicalSorting g2 = createTestGraph();
        g.topsort();
        g2.topsort2();

        System.out.println();

        System.out.println(g);
        System.out.println(g2);
    }

    private static class TopoVertex extends Vertex{
        //入度
        int indegree;
        //拓扑编号
        int topNum;

        TopoVertex(int p, List<Integer> al) {
            this(p, 0, -1, al);
        }

        TopoVertex(int p, int i, int tn, List<Integer> al) {
            super(p, al);
            indegree = i;
            topNum = tn;
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
}


