package edu.rlv.cosc60.graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author russel
 */
public class WeightedDirectedGraph<T> extends DirectedGraph<T> implements WeightedGraph<T>{
    private double defaultWeight;
    private DirectedGraph<T> g;
    private Map<Pair<T>,Double> weights;

    public WeightedDirectedGraph(double defaultWeight) {
        this(new DirectedGraph<>(), new HashMap<>(), defaultWeight);
    }
        
    public WeightedDirectedGraph(DirectedGraph<T> g, double defaultWeight) {
        this(g,new HashMap<>(),defaultWeight);
    }
    
    public WeightedDirectedGraph(DirectedGraph<T> g, Map<Pair<T>,Double> weights, double defaultWeight) {
        this.g = g;
        this.defaultWeight = defaultWeight;
        this.weights = new HashMap<>();
        for(T u:g.vertices()){
            for (T v:g.neighbors(u)){
                Pair p = new Pair(u, v);
                this.weights.put(p, weights.getOrDefault(p, defaultWeight));
            }
        }
    }

    @Override
    public WeightedGraph<T> addVertex(T u) {
        g.addVertex(u);
        return this;
    }

    @Override
    public WeightedGraph<T> removeVertex(T u) {
        g.removeVertex(u);
        return this;

    }

    @Override
    public boolean hasVertex(T u) {
        return g.hasVertex(u);
    }

    @Override
    public Set<T> vertices() {
        return g.vertices();
    }

    @Override
    public WeightedGraph<T> addEdge(T u, T v) {
        return addEdge(u, v, defaultWeight);
    }
    
    @Override
    public WeightedGraph<T> addEdge(T u, T v,Double w) {
        g.addEdge(u, v);
        weights.put(new Pair(u,v), (w == null?defaultWeight:w));
        
        return this;
    }
    
    @Override
    public WeightedGraph<T> setEdgeWeight(T u, T v,Double w) {
        if(hasEdge(u, v)){
            weights.put(new Pair(u,v), w);
        }
        
        return this;
    }

    @Override
    public Double getEdgeWeight(T u, T v) {
        if(hasEdge(u, v)){
            return weights.get(new Pair(u,v));
        }
        
        return null;
    }
    
    

    @Override
    public WeightedGraph<T> removeEdge(T u, T v) {
        g.removeEdge(u, v);
        weights.remove(new Pair(u,v));
        return this;
    }

    @Override
    public boolean hasEdge(T u, T v) {
        return g.hasEdge(u, v);
    }

    @Override
    public WeightedGraph<T> reverse() {
        WeightedDirectedGraph<T> rev = new WeightedDirectedGraph<>(defaultWeight);
        
        for(T u:vertices()){
            for(T v:neighbors(u)){
                rev.addEdge(v, u,weights.get(new Pair<>(u,v)));
            }
        }
        
        return rev;
    }

    @Override
    public int order() {
        return g.order();
    }

    @Override
    public int size() {
        return g.size();
    }

    @Override
    public List<T> neighbors(T u) {
        return g.neighbors(u);
    }
    
    @Override
    public String toString() {
        StringBuilder s= new StringBuilder("[");
        for(T u:vertices()){
            s.append(u).append(": {");
            for(T v:neighbors(u)){
                s.append("(")
                 .append(v)
                 .append(", ")
                 .append(weights.get(new Pair<>(u,v)))
                 .append(") ,");
            }    
            if(s.charAt(s.length()-1)!='{'){
                s.delete(s.length()-2, s.length());               
            }
            s.append("}, "); 
        }
        if(s.length()>1){
            s.delete(s.length()-2, s.length());
        }
        
        return s.append("]").toString(); 
    }
    
}
