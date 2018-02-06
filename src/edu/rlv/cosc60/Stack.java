package edu.rlv.cosc60;

import java.util.Iterator;

/**
 *
 * @author russel
 */
public class Stack<E> implements Iterable<E> {
    List<E> stack;

    public Stack(int initial_capacity) {
        stack = new List<>(initial_capacity);
    }
    
    public int size(){
        return stack.size();
    }
    
    public void clear(){
        stack.clear();
    }
    
    public boolean isEmpty(){
        return stack.isEmpty();
    }
    
    public void push(E e){
        stack.add(e);
    }
    
    public E pop(){
        return stack.remove(stack.size()-1);
    }
    
    public E peek(){
        return stack.get(stack.size()-1);
    }
    
    @Override
    public Iterator<E> iterator() {
        return stack.iterator();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
    
    
    
}
