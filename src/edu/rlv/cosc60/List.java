package edu.rlv.cosc60;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author russel
 */
public class List<E> implements Iterable<E> {

    //Backing array where the elements of the bag are to be stored
    private E[] list;
    //Pointer to the next empty position in the backing array
    private int next;

    public List(int initial_capacity) {
        if (initial_capacity <= 0) {
            throw new IllegalArgumentException();
        }
        list = (E[]) new Object[initial_capacity];
        next = 0;
    }

    public E get(int i){
        failOnInvalidIndex(i);
        
        return list[i];
    }
    
    public E set(int i, E e){
        failOnInvalidIndex(i);
        
        E prev = list[i];
        list[i] = e;
                
        return prev;
    }
    
    public boolean add(int i, E e) {
        if (i != next) {
            failOnInvalidIndex(i);
        }

        if (next == list.length) {
            resize();
        }

        for (int j = next - 1; j >= i; j--) {
            list[j + 1] = list[j];
        }

        list[i] = e;
        next++;

        return true;
    }

    public boolean add(E e) {
        return add(next, e);
    }

    public E remove(int i) {
        failOnInvalidIndex(i);

        E tmp = list[i];

        next--;

        for (int j = i; j < next; j++) {
            list[j] = list[j + 1];
        }
        
        if(next <= list.length/4){
            resize();
        }
        
        list[next] = null;
        
        return tmp;
    }

    public boolean remove(E e) {
        int i = -1;

        for (int j = 0; j < next; j++) {
            if (list[j].equals(e)) {
                i = j;
                break;
            }
        }

        if (i != -1) {
            next--;
            
            for (int j = i; j < next; j++) {
                list[j] = list[j + 1];
            }

            list[next] = null;
            
            return true;
        }
        return false;
    }

    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

    public int indexOf(E e) {

        for (int i = 0; i < next; i++) {
            if (list[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    
    public int size() {
        return next;
    }

    public boolean isEmpty() {
        return 0 == next;
    }
    
    public void clear(){
        next = 0;
        resize();
    }
    
    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder("[");

        for (int i = 0; i < next; i++) {
            tmp.append(list[i]).append(", ");
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
            int cur = -1;

            @Override
            public boolean hasNext() {
                return !isEmpty() && cur < next - 1;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    cur++;
                    return list[cur];
                }
                throw new NoSuchElementException();
            }
        };
    }

    private void failOnInvalidIndex(int i) {
        if (i < 0 || i >= next) {
            throw new IndexOutOfBoundsException(Integer.toString(i));
        }
    }

    private void resize() {
        E tmp[] = (E[]) new Object[Math.max(1, next * 2)];
        System.arraycopy(list, 0, tmp, 0, next);
        list = tmp;
    }
}
