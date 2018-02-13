package edu.rlv.cosc60;

/**
 *
 * @author russel
 */
public interface Stack<E> extends Iterable<E> {

    public void push(E e);

    public E pop();

    public E peek();

    public int size();

    public void clear();

    public boolean isEmpty();
}
