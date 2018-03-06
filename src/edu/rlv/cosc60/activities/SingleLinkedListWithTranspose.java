package edu.rlv.cosc60.activities;

import edu.rlv.cosc60.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author russel
 */
public class SingleLinkedListWithTranspose<E> implements List<E> {
     
    private class Node{
        E value;
        Node next;
    }
    
    private Node head;
    private int count;

    public SingleLinkedListWithTranspose() {
        head  = new Node(); //Create the head node
        count = 0;
    }
    
    @Override
    public E get(int i) {
        failOnInvalidIndex(i);
        return getNodeAt(i).value;
    }

    @Override
    public E set(int i, E e) {
        failOnInvalidIndex(i);
        Node cur = getNodeAt(i);
        E oldValue = cur.value;
        cur.value = e;
        return oldValue;
    }

    @Override
    public boolean add(int i, E e) {
        if(i!=count){
            failOnInvalidIndex(i);
        }
        
        Node nodeBefore = getNodeAt(i-1);
        
        Node nodeNew = new Node();
        nodeNew.value = e;
        nodeNew.next = nodeBefore.next;
        
        nodeBefore.next = nodeNew;
        
        count++;
        
        return true;
    }

    @Override
    public boolean add(E e) {
        return add(count,e);
    }

    @Override
    public E remove(int i) {
        failOnInvalidIndex(i);
        
        Node nodeBefore = getNodeAt(i-1);
        Node nodeToRemove = nodeBefore.next;
        
        nodeBefore.next = nodeToRemove.next;
        nodeToRemove.next = null;
        
        count--;
        
        return nodeToRemove.value;
    }

    @Override
    public boolean remove(E e) {
        Node nodeBefore = this.head;
        
        while(nodeBefore.next != null){
            Node nodeToRemove = nodeBefore.next;
            
            if(nodeToRemove.value.equals(e)){
                nodeBefore.next = nodeToRemove.next;
                nodeToRemove.next = null;
                
                count--;
                
                return true;
            }
            nodeBefore = nodeBefore.next;
        }
        return false;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

    @Override
    public int indexOf(E e) {
        Node cur = this.head.next;
        int  ctr = 0;
        while(cur != null){
            if(cur.value.equals(e)){
                return ctr;
            }
            cur = cur.next;
            ctr++;
        }
        return -1;
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
        this.head = null;
        this.count = 0;
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder("[");

        for (E e:this) {
            tmp.append(e).append(", ");
        }

        if (tmp.length() > 1) {
            tmp.delete(tmp.length() - 2, tmp.length());
        }

        tmp.append("]");

        return tmp.toString();
    
    }
    
    
    
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node cur = head;
            
            @Override
            public boolean hasNext() {
                return cur.next!=null;
            }

            @Override
            public E next() {
                if(hasNext()){
                    cur = cur.next;
                    return cur.value;
                }
                throw new NoSuchElementException();
            }
        };
    }
    
    private Node getNodeAt(int i){
        Node cur = this.head;
        int  ctr = -1;
        
        while(ctr < i && cur.next !=null){
            cur = cur.next;
            ctr++;
        }
        return cur;
    }
    
    private void failOnInvalidIndex(int i) {
        if (i < 0 || i >= count) {
            throw new IndexOutOfBoundsException(Integer.toString(i));
        }
    }
}
