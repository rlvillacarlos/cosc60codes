package edu.rlv.cosc60.applications.graphs;

import edu.rlv.cosc60.graphs.UndirectedGraph;
import java.util.Set;

/**
 *
 * @author russel
 */
public class ConnectedComponentSample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UndirectedGraph<String> g = new UndirectedGraph<>();
        
        g.addEdge("A", "B")
         .addEdge("B", "C")
         .addEdge("D", "E")
         .addVertex("F");
        
        ConnectedComponentsFinder<String> ccfinder = new ConnectedComponentsFinder<>(g);
        
        int i = 0;
        for(Set<String> c: ccfinder.getComponents()){
            System.out.printf("Component %d: %s%n",i,c);            
            i++;
        }
        
        for(String s: g.vertices()){
            System.out.printf("Component containing %s: %s%n",s,ccfinder.getComponent(s));
        }
        
    }
    
}
