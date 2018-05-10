package edu.rlv.cosc60.applications.graphs;

/**
 *
 * @author russel
 */
public enum EdgeType {
    TreeEdge, ForwardEdge, BackEdge,CrossEdge;
    
    @Override    
    public String toString() {
        switch(this){
            case TreeEdge: return "Tree Edge";
            case ForwardEdge: return "Forward Edge";
            case BackEdge: return "Back Edge";
            default: return "Cross Edge";
        }
    }
}
