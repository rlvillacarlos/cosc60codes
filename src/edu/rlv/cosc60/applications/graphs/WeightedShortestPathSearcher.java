package edu.rlv.cosc60.applications.graphs;

import edu.rlv.cosc60.UpdatableMinHeapPQ;
import edu.rlv.cosc60.graphs.Path;
import edu.rlv.cosc60.graphs.WeightedDirectedGraph;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author russel
 */
public class WeightedShortestPathSearcher<T> {
    private WeightedDirectedGraph<T> g;
    private final T source;
    private final Map<T,T> parent;
    private final Map<T,PQEntry> entries;
    
    private class PQEntry implements Comparable<PQEntry>{
        T vertex;
        Double weight;

        public PQEntry(T vertex) {
            this(vertex,Double.POSITIVE_INFINITY);
        }

        public PQEntry(T vertex, Double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
        
        
        @Override
        public int compareTo(PQEntry o) {
            return weight.compareTo(o.weight);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 17 * hash + Objects.hashCode(this.vertex);
            hash = 17 * hash + Objects.hashCode(this.weight);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final PQEntry other = (PQEntry) obj;
            if (!Objects.equals(this.vertex, other.vertex)) {
                return false;
            }
            if (!Objects.equals(this.weight, other.weight)) {
                return false;
            }
            return true;
        }

    }
    
    
    public WeightedShortestPathSearcher(WeightedDirectedGraph<T> g, T source){
        this.g = g;
        this.source = source;
        this.parent = new HashMap<>();
        this.entries = new HashMap<>();
        dijkstra();
    }
    
    public Double getWeight(T target){
        PQEntry e = entries.get(target);
        return e != null ? e.weight : Double.POSITIVE_INFINITY;
    }
    
    public Path<T> getPath(T target){
        PQEntry e = entries.get(target);
        if(e == null || e.weight.isInfinite()){
            return new Path<>(g);
        }
        
        Path<T> p = new Path(g);
        
        if(source.equals(target)){
            return p.add(source);
        }

        Stack<T> s = new Stack<>();
        
        T cur =  target;
        
        while(cur !=null){
            s.push(cur);                    
            cur = parent.get(cur);
        }       
        
        while(!s.empty()){
            p.add(s.pop());
        }
        
        return p;
    }
    
    private void dijkstra(){
        
        int i  = 0;
        
        for(T e:g.vertices()){
            entries.put(e, new PQEntry(e));
        }
        entries.get(source).weight =  0.0;
        
        UpdatableMinHeapPQ<PQEntry> q = new UpdatableMinHeapPQ<>();
        parent.put(source, null);
        
        for(PQEntry e:entries.values()){
            q.add(e);
        }
        
        while(!q.isEmpty()){
            PQEntry minEntry = q.removeMin();
            T u = minEntry.vertex;
           
            for(T v:g.neighbors(u)){
                PQEntry curEntry = entries.get(v);
                Double newWeight = g.getEdgeWeight(u, v) + minEntry.weight;
                PQEntry newEntry = new PQEntry(v,newWeight);

                if(curEntry.compareTo(newEntry) > 0){
                    q.update(curEntry,newEntry);
                    entries.put(v, newEntry);
                    parent.put(v, u);
                }
            }
            
        }        
    }
    
}
