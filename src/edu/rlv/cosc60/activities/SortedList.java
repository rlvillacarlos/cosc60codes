package edu.rlv.cosc60.activities;

import edu.rlv.cosc60.List;
import java.util.Iterator;

/**
 *
 * @author russel
 */
public class SortedList<E extends Comparable<E>> implements List<E> {

    @Override
    public E get(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public E remove(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(E e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }


    @Override
    public int indexOf(E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    /**************************************************************************
     * The following methods are already implemented, do not modify 
     *************************************************************************/
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public boolean contains(E e) {
        return indexOf(e) != -1;
    }
    
    /**************************************************************************
     * The following methods are not supported by the SortedList, do not modify
     **************************************************************************/
    @Override
    public E set(int i, E e) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean add(int i, E e) {
        throw new UnsupportedOperationException("Not supported.");
    }
    
}
