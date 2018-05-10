package edu.rlv.cosc60.applications.graphs;

import edu.rlv.cosc60.graphs.Graph;
import edu.rlv.cosc60.graphs.UndirectedGraph;

/**
 *
 * @author russel
 */
public class UndirectedGraphSample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph<String> g1 = new UndirectedGraph<>();
        
        g1.addVertex("A")
          .addVertex("B")
          .addVertex("C")
          .addEdge("A", "B")
          .addEdge("B", "C")
          .addEdge("C", "A");
        
        System.out.println(g1);
        System.out.println(g1.reverse());
    }
    
}
