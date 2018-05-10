package edu.rlv.cosc60.applications.graphs;

import edu.rlv.cosc60.graphs.DirectedGraph;

/**
 *
 * @author russel
 */
public class TopologicalSortSample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DirectedGraph<String> g = new DirectedGraph<>();
        
        g.addEdge("A", "B")
         .addEdge("B", "C")
         .addEdge("B", "E")
         .addEdge("C", "F")
         .addEdge("E", "F")
         .addEdge("D", "B");
        
        System.out.println(new TopologicalSorter<>(g));
                
    }
    
}
