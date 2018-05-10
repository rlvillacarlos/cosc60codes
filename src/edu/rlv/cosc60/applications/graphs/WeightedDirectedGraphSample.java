package edu.rlv.cosc60.applications.graphs;

import edu.rlv.cosc60.graphs.WeightedDirectedGraph;

/**
 *
 * @author russel
 */
public class WeightedDirectedGraphSample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WeightedDirectedGraph<String> g1 = new WeightedDirectedGraph<>(0.0);
        
        g1.addVertex("A")
          .addVertex("B")
          .addVertex("C")
          .addEdge("A", "B",1.5)
          .addEdge("B", "C",2.0)
          .addEdge("C", "B")                
          .addEdge("C", "A");
        
        System.out.println(g1);
        System.out.println(g1.order());
        System.out.println(g1.size());
        System.out.println(g1.neighbors("A"));
        System.out.println(g1.neighbors("B"));
        System.out.println(g1.neighbors("C"));
        
        System.out.println(g1.reverse());
    }
    
}
