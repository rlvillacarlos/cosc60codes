package edu.rlv.cosc60.applications.graphs;

import edu.rlv.cosc60.graphs.DirectedGraph;
import edu.rlv.cosc60.graphs.Graph;

/**
 *
 * @author russel
 */
public class EdgeClassificationSample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph<String> g = new DirectedGraph();
        
        g.addEdge("a", "b")
         .addEdge("a", "d")
         .addEdge("b", "e")
         .addEdge("c", "e")
         .addEdge("c", "f")
         .addEdge("d", "b")
         .addEdge("e", "d");
        EdgeClassifier<String> classifier = new EdgeClassifier<>(g);
        
        System.out.println(classifier);
        
    }
    
}
