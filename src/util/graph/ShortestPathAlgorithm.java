package util.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 最短路径算法，四种形态的算法
 * @author jun.li
 */
class ShortestPathAlgorithm extends Graph{
    ShortestPathAlgorithm(List<Vertex> vs) {
        super(vs);
    }

    /**
     * 无权图的简单的最短路径算法，时间复杂度为O(|V|^2)
     * @param s
     */
    void unweighted(UnweightedVertex1 s) {
        //初始化的工作在创建UnweightedVertex的时候已经完成了
        /*
        for (Vertex v : adjacentList.values()) {
            UnweightedVertex1 v1 = (UnweightedVertex1) v;
            v1.dist = null;
            v1.known = false;
        }
        */
        s.dist = 0; //最开始的时候除了s以外的所有顶点都是不可达到的，而s的路径长为0

        for (int currDist = 0; currDist < vertexSize; currDist++) {
            for (Vertex v : adjacentList.values()) {
                UnweightedVertex1 v1 = (UnweightedVertex1) v;
                if (!v1.known && v1.dist != null && v1.dist == currDist) {
                    v1.known = true;
                    System.out.print("point: " + v1.point + " dist: " + v1.dist);
                    System.out.println(" path: " + v1);
                    for (Integer p : v1.adList) {
                        UnweightedVertex1 w = (UnweightedVertex1)findVertexByPoint(p);
                        if (w.dist == null) {
                            w.dist = currDist + 1;
                            w.path = v1;
                        }
                    }
                }
            }
        }

    }

    /**
     * 无权图的改进后的最短路径算法，时间复杂度为O(|V| + |E|)
     * @param s
     */
    void unweighted2(UnweightedVertex2 s) {
        Queue<UnweightedVertex2> q = new ArrayDeque<>(vertexSize);
        //初始化的工作在创建UnweightedVertex2的时候已经完成了
        /*
        for (Vertex v : adjacentList.values()) {
            UnweightedVertex1 v1 = (UnweightedVertex1) v;
            v1.dist = null;
        }
        */
        s.dist = 0; //最开始的时候除了s以外的所有顶点都是不可达到的，而s的路径长为0
        q.add(s);

        while (!q.isEmpty()) {
            UnweightedVertex2 v = q.remove();
            System.out.print("point: " + v.point + " dist: " + v.dist);
            System.out.println(" path: " + v);
            for (Integer i : v.adList) {
                UnweightedVertex2 w = (UnweightedVertex2) findVertexByPoint(i);
                if (w.dist == null) {
                    w.dist = v.dist + 1;
                    w.path = v;
                    q.add(w);
                }
            }
        }

    }

    /**
     * 构造一个简单的图
     * @return
     */
    static ShortestPathAlgorithm createGraph(String vertexFlag) {

        List<Vertex> vertices = new ArrayList<>();
        List<Integer> adjacencyList = new ArrayList<>(3);

        int point;
        Vertex v;

        point = 1;
        adjacencyList.add(2);
        adjacencyList.add(4);
        v = createVertexByVertexFlag(vertexFlag, point, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 2;
        adjacencyList.add(4);
        adjacencyList.add(5);
        v = createVertexByVertexFlag(vertexFlag, point, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 3;
        adjacencyList.add(1);
        adjacencyList.add(6);
        v = createVertexByVertexFlag(vertexFlag, point, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 4;
        adjacencyList.add(6);
        adjacencyList.add(7);
        adjacencyList.add(3);
        adjacencyList.add(5);
        v = createVertexByVertexFlag(vertexFlag, point, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 5;
        adjacencyList.add(7);
        v = createVertexByVertexFlag(vertexFlag, point, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 6;
        v = createVertexByVertexFlag(vertexFlag, point, adjacencyList);
        vertices.add(v);

        adjacencyList.clear();
        point = 7;
        adjacencyList.add(6);
        v = createVertexByVertexFlag(vertexFlag, point, adjacencyList);
        vertices.add(v);

        ShortestPathAlgorithm g = new ShortestPathAlgorithm(vertices);

        return g;
    }

    /**
     * 根据vertexFlag返回对应的vertex实例
     * @param vertexFlag unweight1, unweight2
     * @param point
     * @param adjacencyList
     * @return
     */
    private static Vertex createVertexByVertexFlag(String vertexFlag, int point, List<Integer> adjacencyList) {
        if ("unweight1".equals(vertexFlag)) {
            return new UnweightedVertex1(point, adjacencyList);
        } else if ("unweight2".equals(vertexFlag)) {
            return new UnweightedVertex2(point, adjacencyList);
        } else {
            return null;
        }
    }

    /**
     * 无权最短路径的简单算法的Vertex
     */
    private static class UnweightedVertex1 extends Vertex{
        boolean known = false; //初始时所有顶点都不是已知的
        Integer dist = null; //初始时所有顶点的距离都是无穷大的，用null来表示
        UnweightedVertex1 path = null;

        UnweightedVertex1(int p, List<Integer> al) {
            super(p, al);
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append(point);

            if (path != null) {
                result.append("->").append(path);
            }

            return result.toString();
        }
    }

    /**
     * 无权最短路径的简单算法的Vertex
     */
    private static class UnweightedVertex2 extends Vertex{
        //不使用known域是因为改进后的算法中，一个顶点一旦被处理就不再进入队列，因此不需要被处理的事实就意味着被做了标记

        Integer dist = null; //初始时所有顶点的距离都是无穷大的，用null来表示
        UnweightedVertex2 path = null;

        UnweightedVertex2(int p, List<Integer> al) {
            super(p, al);
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append(point);

            if (path != null) {
                result.append("->").append(path);
            }

            return result.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("Next is simple unweighted: ");
        ShortestPathAlgorithm g = createGraph("unweight1");
        UnweightedVertex1 s = (UnweightedVertex1) g.findVertexByPoint(3);
        g.unweighted(s);

        System.out.println("Next is improved unweighted: ");
        ShortestPathAlgorithm g2 = createGraph("unweight2");
        UnweightedVertex2 s2 = (UnweightedVertex2) g2.findVertexByPoint(3);
        g2.unweighted2(s2);

    }
}
