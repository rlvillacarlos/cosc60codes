package edu.rlv.cosc60;

/**
 *
 * @author russel
 */
public class Counter{
    private int count;
    private final int MAX_COUNT;

    public Counter() {
        this(Integer.MAX_VALUE);
    }

    public Counter(int MAX_COUNT) {
        this.count = 0;
        this.MAX_COUNT = MAX_COUNT;
    }
    
    public int increment(){
        if(!isMaxed()){
            this.count++;
        }
        return this.count;
    }
    
    
    public int decrement(){
        if(this.count > 0){
            this.count--;
        }
        return this.count;
    }
    
    public boolean isMaxed(){
        return this.count == this.MAX_COUNT;
    }
    
    public int getValue(){
        return this.count;
    }

    @Override
    public String toString() {
        return String.format("(MAX_COUNT = %d, Count = %d)", MAX_COUNT,count);
    }
    
}
