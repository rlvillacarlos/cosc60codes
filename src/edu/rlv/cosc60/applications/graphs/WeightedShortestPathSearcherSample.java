package edu.rlv.cosc60.applications.graphs;

import edu.rlv.cosc60.graphs.WeightedDirectedGraph;

/**
 *
 * @author russel
 */
public class WeightedShortestPathSearcherSample {
    public static void main(String[] args) {
        WeightedDirectedGraph<String> g = new WeightedDirectedGraph(0);
        g.addEdge("A", "B", 10.0);
        g.addEdge("A", "C", 5.0);
        g.addEdge("B", "C", 2.0);
        g.addEdge("C", "B", 3.0);
        g.addEdge("B", "D", 1.0);
        g.addEdge("C", "D", 9.0);
        g.addEdge("C", "E", 2.0);
        g.addEdge("D", "E", 4.0);
        g.addEdge("E", "D", 6.0);
        g.addEdge("E", "A", 7.0);
        g.addVertex("F");
        
        WeightedShortestPathSearcher searcher = new WeightedShortestPathSearcher(g, "A");
        System.out.println(g);
        System.out.printf("%s->%s: %s, %.2f %n","A","A",searcher.getPath("A"),searcher.getWeight("A"));
        System.out.printf("%s->%s: %s, %.2f %n","A","B",searcher.getPath("B"),searcher.getWeight("B"));
        System.out.printf("%s->%s: %s, %.2f %n","A","C",searcher.getPath("C"),searcher.getWeight("C"));
        System.out.printf("%s->%s: %s, %.2f %n","A","D",searcher.getPath("D"),searcher.getWeight("D"));
        System.out.printf("%s->%s: %s, %.2f %n","A","E",searcher.getPath("E"),searcher.getWeight("E"));
        System.out.printf("%s->%s: %s, %.2f %n","A","F",searcher.getPath("F"),searcher.getWeight("F"));
    }
}
