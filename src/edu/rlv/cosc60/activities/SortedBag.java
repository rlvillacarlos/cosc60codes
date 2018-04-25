package edu.rlv.cosc60.activities;

import edu.rlv.cosc60.AVLMap;
import edu.rlv.cosc60.Map;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author russel
 */
public class SortedBag<E extends Comparable<E>> implements Iterable<E> {
    private Map<E,Integer> m = new AVLMap<>();
    int size;
    
    public boolean add(E e) {
        if(m.contains(e)){
            m.put(e,m.get(e) + 1);
        }else{
            m.put(e,1);
        }
        size++;
        return true;
    }

    public boolean remove(E e) {
        if (m.contains(e)) {
            m.put(e, m.get(e) - 1);
            if(m.get(e) == 0){
                m.remove(e);
            }
            size--;
            return true;
        } 
        
        return false;
    }

    public int size() {
        return size;
    }
    
    public void clear() {
        m.clear();
        size = 0;
    }
    
    public boolean contains(E e) {
        return m.contains(e);
    }

    public boolean isEmpty(){
        return size()==0;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int keyFrequency = 0;
            E curKey;
            Iterator<E> itr = m.getKeys().iterator();
            
            @Override
            public boolean hasNext() {
                return itr.hasNext();
            }

            @Override
            public E next() {
                if(hasNext()){
                    if(keyFrequency == 0){
                        curKey = itr.next();
                        keyFrequency = m.get(curKey);    
                    }
                    keyFrequency--;
                    return curKey;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
