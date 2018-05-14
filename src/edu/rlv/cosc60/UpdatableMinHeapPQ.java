package edu.rlv.cosc60;

import java.util.Iterator;

/**
 *
 * @author russel
 */
public class UpdatableMinHeapPQ<E extends Comparable<E>> implements MinPQ<E> {
    private final List<E> heap;
    private final Map<E,Integer> indices;
    
    public UpdatableMinHeapPQ() {
        heap = new ArrayList<>();
        indices = new ChainedHashMap<>();
        heap.add(null);
    }

    public UpdatableMinHeapPQ(E[] values) {
        this();
        for(E v:values){
            if(!indices.contains(v)){
                indices.put(v, heap.size());
                heap.add(v);
            }
        }
        
        for(int i = size()/2;i>=1;i--){
            demote(i);
        }
        
    }
    
    @Override
    public boolean add(E value) {    
        
        if(indices.contains(value)){
            return false;
        }
        
        indices.put(value, heap.size());
        heap.add(value);

        if(size()>1){
            promote(size());
        }
        
        return true;
    }

    public boolean update(E value, E newValue) {
        if(indices.contains(value) && !indices.contains(newValue)){
            int index = indices.get(value);
            
            heap.set(index, newValue);
            
            if(newValue.compareTo(value) < 0){
                promote(index);
            }else{
                demote(index);
            }
            
            return true;
        }
        
        
        return false;
    }
    
    @Override
    public E getMin() {
        return heap.get(1);
    }
    
    
    @Override
    public E removeMin() {        
        swap(1, size());
        E toRemove = heap.remove(size());
        indices.remove(toRemove);
        if(size()>1){
            demote(1);
        }
        return toRemove;
    }
  
    @Override
    public Iterator<E> iterator() {
        Iterator<E> itr = heap.iterator();
        itr.next();
        return itr;
    }

    @Override
    public int size() {
        return heap.size() - 1;
    }

    @Override
    public void clear() {
        heap.clear();
        indices.clear();
        heap.add(null);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder("[");
        for(E e:this){
            tmp.append(e).append(", ");
        }
        if(tmp.length()>1){
            tmp.delete(tmp.length()-2, tmp.length());
        }
        return tmp.append("]").toString();
    }    
    
    private void promote(int i){
        E cur = heap.get(i);
        
        while(i != 1){
            int p = i/2;
            E parent = heap.get(p);
            
            if(cur.compareTo(parent) < 0){
                swap(i,p);
                i = p;
            }else{
                break;
            }
        }
    }
    
    private void demote(int i){
        E cur = heap.get(i);

        while(i <= size()/2){
            int m = i*2;
            int r = m + 1;
            
            E min = heap.get(m);
            
            if(r <= size()){
                E right = heap.get(r);

                if(right.compareTo(min)<0){
                    min = right;
                    m = r;
                }
            }
            
            if(min.compareTo(cur) < 0){
                swap(i,m);
                i = m;
            }else{
                break;
            }
        }
    }
    
    private void swap(int i,int j){
        E e_i = heap.get(i);
        E e_j = heap.get(j);
        
        heap.set(i, e_j);
        heap.set(j, e_i);
        
        indices.put(e_i, j);
        indices.put(e_j, i);
    }
    
    public static void main(String[] args) {
        Integer d[] = {1,5,2,6,3,7,4,8,1};
        UpdatableMinHeapPQ<Integer> pq = new UpdatableMinHeapPQ<>(d);
        System.out.println(pq);
        pq.update(8, 0);
        System.out.println(pq);
        
    }
}
