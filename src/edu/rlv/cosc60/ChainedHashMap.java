package edu.rlv.cosc60;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author russel
 */
public class ChainedHashMap<K,V> implements Map<K, V> {
    private class Entry{
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 37 * hash + Objects.hashCode(this.key);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Entry other = (Entry) obj;
            if (!Objects.equals(this.key, other.key)) {
                return false;
            }
            return true;
        }        
    }
    
    private final int W = 32;
    private final int D = 3;
    private final int a;
    private final double loadFactor;
    
    private List<Entry> table[];
    private int size;
    private int d;
    
    
    public ChainedHashMap(double loadFactor) {
        if(loadFactor <= 0){
            throw new IllegalArgumentException();
        }
        
        Random rnd = new Random(System.currentTimeMillis());
        
        int rndA = rnd.nextInt(Integer.MAX_VALUE);
        
        this.a = rndA + (rndA%2 == 0 ? 1:0);
        
        this.d = D;
        this.table = new List[(int)Math.pow(2, this.d)];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public ChainedHashMap() {
        this(1.0);
    }
    
    @Override
    public V put(K key, V value) {
        int i = hash(key);
        Entry e = new Entry(key,value);
        V result = null;
        
        if(table[i] == null){
            table[i] = new SingleLinkedList<>();
            table[i].add(0, e);
            size++;
        }else{
            int chainIndex = table[i].indexOf(e);
            if(chainIndex == -1){
//                System.out.println("collision");

                table[i].add(0, e);
                size++;
            }else{
                Entry old = table[i].set(chainIndex, e);
                result = old.value;
            }
        }
        
        if(size/table.length > loadFactor){
            resize();
        }
        
        return result;
    }

    @Override
    public boolean remove(K key) {
        int i = hash(key);
        
        if(table[i]== null){
            return false;
        }
        Entry e = new Entry(key,null);
        size--;
        return table[i].remove(e);
    }

    @Override
    public boolean contains(K key) {
        int i = hash(key);
        Entry e = new Entry(key,null);
        return table[i] == null ? false : table[i].contains(e);
    }

    @Override
    public V get(K key) {
        int i = hash(key);
        
        if(table[i] == null){
            return null;
        }
        
        Entry e = new Entry(key,null);
        int chainIndex = table[i].indexOf(e);
        return chainIndex != -1 ? table[i].get(chainIndex).value : null;
    }

    @Override
    public String toString() {
        StringBuilder t = new StringBuilder("{");
        
        for(K k: getKeys()){
            t.append(k).append(": ").append(get(k)).append(", ");
        }
        
        if(t.length() > 1){
            t.delete(t.length()-2, t.length());
        }
        
        return t.append("}").toString();
    }
    
    

    @Override
    public Iterable<K> getKeys() {
        return new Iterable<K>(){
            @Override
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    int ctr = -1;
                    int indx = -1;
                    Iterator<Entry> itr = null;
                    
                    @Override
                    public boolean hasNext() {
                        return ctr < size - 1;
                    }

                    @Override
                    public K next() {
                        if(hasNext()){
                            if(itr == null || !itr.hasNext()){
                                while(table[++indx]== null);           
                                itr = table[indx].iterator();
                            }
                            ctr++;
                            return itr.next().key;
                        }
                        throw new NoSuchElementException();
                    }
                };
            }
        
        };
    }

    @Override
    public Iterable<V> getValues() {
        return new Iterable<V>(){
            @Override
            public Iterator<V> iterator() {
                return new Iterator<V>() {
                    int ctr = -1;
                    int indx = -1;
                    Iterator<Entry> itr = null;
                    
                    @Override
                    public boolean hasNext() {
                        return ctr < size - 1;
                    }

                    @Override
                    public V next() {
                        if(hasNext()){
                            if(itr == null || !itr.hasNext()){
                                while(table[++indx]== null);           
                                itr = table[indx].iterator();
                            }
                            ctr++;
                            return itr.next().value;
                        }
                        throw new NoSuchElementException();
                    }
                };
            }
        
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        d = D;
        table = new List[(int)Math.pow(2, this.d)];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    private int hash(K k){
        return k.hashCode() * this.a >>> (W - d);
    }
    
    private void resize(){
        this.d++;
        List<Entry> t[] = new List[(int)Math.pow(2, this.d)];
        
        for(int i= 0 ;i<table.length;i++){
            if(table[i]!=null){
                for(Entry e: table[i]){
                    int j = hash(e.key);
                    
                    if(t[j]==null){
                        t[j] = new SingleLinkedList<>();
                    }
                    t[j].add(0, new Entry(e.key, e.value));
                }
            }
        }
        this.table = t;
    }
}
