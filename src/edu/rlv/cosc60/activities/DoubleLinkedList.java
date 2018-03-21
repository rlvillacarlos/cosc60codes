package edu.rlv.cosc60.activities;

import edu.rlv.cosc60.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author russel
 */
public class DoubleLinkedList<E> implements List<E> {
    private class Node{
        E value;
        Node next;
        Node prev;
    }
    
    private int count;
    private Node head;

    @Override
    public E get(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(int i, E e) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public E remove(int i) {        
        throw new UnsupportedOperationException();

    }
    
    @Override
    public int indexOf(E e) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public List<E> sublist(int start, int end) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<E> reverseIterator() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean add(E e) {
        return add(size(),e);
    }
    
    @Override
    public boolean remove(E e) {
        int indx = indexOf(e);
        if(indx == -1){
            return false;
        }
        remove(indx);
        return true;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e)!=-1;
    }
    
    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void clear() {
        if(count>0){
            head.prev.next = null;
            head.next.prev = null;
            head.next = head;
            head.prev = head;
            count = 0;
        }
    } 
}
