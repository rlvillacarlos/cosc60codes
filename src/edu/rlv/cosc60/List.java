package edu.rlv.cosc60;

/**
 *
 * @author russel
 */
public interface List<E> extends Iterable<E> {
    public E get(int i);
    
    public E set(int i, E e);
    
    public boolean add(int i, E e);

    public boolean add(E e);

    public E remove(int i);

    public boolean remove(E e);

    public boolean contains(E e);

    public int indexOf(E e);

    public int size();

    public boolean isEmpty();
    
    public void clear();
    
    public default List<E> sublist(int start, int end){
        throw new UnsupportedOperationException("Operation not supported");
    }
}
