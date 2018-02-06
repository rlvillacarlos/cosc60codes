package edu.rlv.cosc60;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**Bag ADT
 *
 * @author russel
 */
public class BoundedBag<E> implements Iterable<E>{
    //Backing array where the elements of the bag are to be stored
    private E[] bag;
    //Pointer to the next empty position in the backing array
    private int next;
    
    public BoundedBag(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException();
        }
        bag = (E[]) new Object[capacity];
        next = 0;
    }
    
    
    public boolean add(E e){
        failIfNull(e);
        
        if(isFull()){
            return false;
        }
        
        bag[next] = e;
        next++;
        return true;
    }
    
    public boolean remove(E e){
        failIfNull(e);
        
        int posToRemove = -1;
        
        //Find the position of the item to be removed
        for(int i =0;i<next;i++){
            if(bag[i].equals(e)){
                posToRemove = i;
                break;
            }
        }
        
        //If the item is not found then return false
        if(posToRemove == -1){
            return false;
        }
        
        /**
         * If the item is found, move the items backward starting from the 
         * position of the item to be removed
         */
        for(int i = posToRemove; i < next-1; i++){
            bag[i] = bag[i+1];
        }
        
        //Decrement the next pointer
        next--;
        
        //Set the position pointed by next to null
        bag[next] = null;
        return true;
        
    }
    
    public boolean contains(E e){
        failIfNull(e);
        
        for(int i =0;i<next;i++){
            if(bag[i].equals(e)){
                return true;
            }
        }
        return false;
    }
    
    public int count(E e){
        failIfNull(e);
        
        int frequency = 0;
        for(int i =0;i<next;i++){
            if(bag[i].equals(e)){
                frequency++;
            }
        }
        return frequency;
    }
    
    public int size(){
        return next;
    }
    
    public int capacity(){
        return bag.length;
    }
    
    public boolean isEmpty(){
        return 0 == next;
    }    
    
    public boolean isFull(){
        return bag.length == next;
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder("[");
        
        for(int i=0;i<next;i++){
            tmp.append(bag[i]).append(", ");
        }
        
        if(tmp.length()>1){
            tmp.delete(tmp.length()-2, tmp.length());
        }
        
        tmp.append("]");
        
        return tmp.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cur = -1;
            @Override
            public boolean hasNext() {
                return !isEmpty() && cur < next - 1;
            }

            @Override
            public E next() {
                if(hasNext()){
                    cur++;
                    return bag[cur];
                }
                throw new NoSuchElementException();
            }
        };
    }
    
    
    private void failIfNull(E e){
        if(null == e){
            throw new IllegalArgumentException();
        }
    }
}
