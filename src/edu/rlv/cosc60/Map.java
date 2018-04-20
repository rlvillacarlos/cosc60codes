package edu.rlv.cosc60;

/**
 *
 * @author russel
 */
public interface Map<K,V> {
    public V put(K key,V value);
    
    public boolean remove(K key);
    
    public boolean contains(K key);
    
    public V get(K key);
    
    public Iterable<K> getKeys();
    
    public Iterable<V> getValues();
    
    public int size();
    
    public void clear();
    
    public boolean isEmpty();    
    
}
