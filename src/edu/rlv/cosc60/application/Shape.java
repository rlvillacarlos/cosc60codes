package edu.rlv.cosc60.application;

/**
 *
 * @author russel
 */
public abstract class Shape implements Comparable<Shape>{
    
    public abstract double area();
    
    @Override
    public int compareTo(Shape o) {
        double thisArea = this.area(), oArea = o.area();
        
        if(thisArea == oArea){
            return 0;
        }else if(thisArea > oArea){
            return 1;
        }else{
            return -1;
        }
    }
    
}
