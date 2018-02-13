package edu.rlv.cosc60;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author russel
 */
public class SLLStack<E> implements Stack<E> {
    private class Node{
        E value;
        Node next;
    }
    
    private Node head;
    private int count;

    public SLLStack() {
        //Create a head node
        head = new Node();
        count = 0;
    }
    
    @Override
    public void push(E e) {
        Node newNode = new Node();
        newNode.value = e;
        
        newNode.next = head.next;
        head.next = newNode;
        count++;
    }

    @Override
    public E pop() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        
        Node firstNode = head.next;
        head.next = firstNode.next;
        firstNode.next = null;
        count--;
        return firstNode.value;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
//        Node firstNode = head.next;
        
        return head.next.value; 
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        while(!isEmpty()){
            pop();
        }
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder("[");
        Node cur = head.next;
        
        while(cur!=null){
            tmp.append(cur.value).append(", ");
            cur = cur.next;
        }
        
        if(tmp.length()>1){
            tmp.delete(tmp.length()-2, tmp.length());
        }
        
        return tmp.append("]").toString();
    }

    
    
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node cur = head;
            
            @Override
            public boolean hasNext() {
                return cur.next != null;
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
    
}
