package edu.rlv.cosc60;

import java.util.Iterator;

/**
 *
 * @author russel
 */
public class MinHeapPQ<E extends Comparable<E>> implements MinPQ<E> {
    private final List<E> heap;
    
    public MinHeapPQ() {
        heap = new ArrayList<>();
        heap.add(null);
    }

    public MinHeapPQ(E[] values) {
        this();
        for(E v:values){
            heap.add(v);
        }
        
        for(int i = size()/2;i>=1;i--){
            demote(i);
        }
        
    }
    
    @Override
    public boolean add(E value) {        
        heap.add(value);
        if(size()>1){
            promote(size());
        }
        
        return true;
    }

    @Override
    public E getMin() {
        return heap.get(1);
    }
    
    
    @Override
    public E removeMin() {        
        swap(1, size());
        E toRemove = heap.remove(size());
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
        E tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }
    
    public static void main(String[] args) {
        Integer vals[] = {5,4,3,2,1};
        MinHeapPQ<Integer> heap = new MinHeapPQ<>(vals);
        System.out.println(heap);
    }
}
