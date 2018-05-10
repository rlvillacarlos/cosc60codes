package edu.rlv.cosc60.graphs;

import java.util.List;
import java.util.Set;

/**
 *
 * @author russel
 * @param <T> the type of vertex
 */
public interface Graph<T> {
    
    public Graph<T> addVertex(T u);
    
    public Graph<T> removeVertex(T u);
        
    public Graph<T> addEdge(T u, T v);
    
    public Graph<T> removeEdge(T u, T v);

    public Graph<T> reverse();
    
    public Set<T> vertices();
    
    public boolean hasEdge(T u, T v);
    
    public boolean hasVertex(T u);
    
    /***
     * Returns the number of vertices in the graph.
     * 
     * @return the number of vertices in the graph
     */
    public int order();

    /***
     * Returns the number of edges in the graph.
     * 
     * @return the number of edges in the graph
     */
    public int size();
    
    /***
     * Returns the list of all vertices with an edge from vertex u.
     * 
     * @param u the source vertex
     * 
     * @return the list of all vertices with an edge from vertex u
     */
    public List<T> neighbors(T u); 
    
}
