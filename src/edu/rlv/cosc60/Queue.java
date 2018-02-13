package edu.rlv.cosc60;

/**
 *
 * @author russel
 */
public interface Queue<E> extends Iterable<E> {

    public void enqueue(E e);

    public E dequeue();

    public E peek();

    public int size();

    public void clear();

    public boolean isEmpty();
}
