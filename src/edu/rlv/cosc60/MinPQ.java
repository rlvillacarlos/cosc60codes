package edu.rlv.cosc60;

/**
 *
 * @author russel
 */
public interface MinPQ<E> extends Iterable<E> {

    public boolean add(E value);
    
    public E getMin();
    
    public E removeMin();

    public int size();
    
    public void clear();
    
    public boolean isEmpty();
}
