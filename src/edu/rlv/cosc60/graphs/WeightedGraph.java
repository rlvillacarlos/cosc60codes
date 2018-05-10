package edu.rlv.cosc60.graphs;
/**
 *
 * @author russel
 * @param <T> the type of vertex
 */
public interface WeightedGraph<T> extends Graph<T>{
    
    @Override
    public WeightedGraph<T> addVertex(T u);
    
    @Override
    public WeightedGraph<T> removeVertex(T u);
        
    @Override
    public WeightedGraph<T> addEdge(T u, T v);
    
    @Override
    public WeightedGraph<T> removeEdge(T u, T v);

    @Override
    public WeightedGraph<T> reverse();
    
    public WeightedGraph<T> addEdge(T u, T v, Double w);
    
    public WeightedGraph<T> setEdgeWeight(T u, T v, Double w);
    
    public Double getEdgeWeight(T u, T v);
   
}
