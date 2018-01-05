package util.graph;

/**
 * 拓扑排序
 * @author jun.li
 */
public class TopologicalSorting {
    public static void main(String[] args) throws CycleFoundException {
        Graph g = Graph.createTestGraph();
        Graph g2 = Graph.createTestGraph();
        g.topsort();
        g2.topsort2();

        System.out.println();

        System.out.println(g);
        System.out.println(g2);
    }
}


