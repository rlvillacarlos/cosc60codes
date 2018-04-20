package edu.rlv.cosc60;

import edu.rlv.cosc60.util.ArrayUtil;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russel
 */
public class AVLMapTest {
    TreeMap<Integer,Integer> map;
    Integer aSorted[];
    Integer a[];
    
    @Before
    public void setUp() {
        map = new AVLMap<>();
        aSorted = ArrayUtil.range(1, 100000);
        a = ArrayUtil.permute(aSorted);
        
    }
    
    /**
     * Test of put method, of class TreeMap.
     */
    @Test(timeout = 2000L)
    public void testPut() {
        System.out.println("put");
        for(Integer i: a){
            map.put(i, i);
        }
        
        for(Integer i: a){
            assertTrue(map.contains(i));
        }
    }

    /**
     * Test of remove method, of class TreeMap.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");

        for(Integer i: a){
            map.put(i, i);
        }
        
        for(Integer i: a){
            assertTrue(map.remove(i));
            assertFalse(map.contains(i));
            assertFalse(map.remove(i));
        }
        
    }

    /**
     * Test of contains method, of class TreeMap.
     */
    @Test
    public void testContains() {
        System.out.println("contains");

        for(Integer i: a){
            map.put(i, i);
        }
        
        for(Integer i: a){
            assertTrue(map.contains(i));
            map.remove(i);
            assertFalse(map.contains(i));
        }
    }

    /**
     * Test of get method, of class TreeMap.
     */
    @Test
    public void testGet() {
        System.out.println("get");

        for(Integer i: a){
            map.put(i, i);
        }
        
        for(Integer i: a){
            assertEquals(map.get(i),i);
        }
    }

    /**
     * Test of getKeys method, of class TreeMap.
     */
    @Test
    public void testGetKeys() {
        System.out.println("GetKeys");

        for(Integer i: a){
            map.put(i, i);
        }
        
        int j = 0;
        for(Integer i: map.getKeys()){
            assertEquals(aSorted[j],i);
            j++;
        }
    }

    /**
     * Test of getValues method, of class TreeMap.
     */
    @Test
    public void testGetValues() {
        System.out.println("getValues");
        
        for(Integer i: a){
            map.put(i, i);
        }
        
        int j = 0;
        Integer v[] = new Integer[a.length];
        
        for(Integer i: map.getKeys()){
            v[j] = map.get(i);
            j++;
        }
        
        j = 0;
        for(Integer i: map.getValues()){
            assertEquals(v[j],i);
            j++;
        }
        
    }

    /**
     * Test of size method, of class TreeMap.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        
        int j = 0;
        for(Integer i: a){
            map.put(i, i);
            j++;
            assertEquals(j,map.size());
        }
        int size = a.length;
        for(Integer i: a){
            map.remove(i);
            size--;
            assertEquals(size,map.size());
        }
    }

    /**
     * Test of clear method, of class TreeMap.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        for(Integer i: a){
            map.put(i, i);
        }
        map.clear();
        assertTrue(map.size()==0);
        assertEquals(map.toString(),"{}");
    }

    /**
     * Test of isEmpty method, of class TreeMap.
     */
    @Test
    public void testIsEmpty() {
        for(Integer i: a){
            map.put(i, i);
        }
        assertFalse(map.isEmpty());
        map.clear();
        assertTrue(map.isEmpty());
        
    }

    /**
     * Test of toString method, of class TreeMap.
     */
    @Test
    public void testToString_0args() {
        System.out.println("toString");
        StringBuilder tmp = new StringBuilder("{");
        
        for(Integer i: aSorted){
            tmp.append(i).append(": ").append(i).append(", ");
        }
        tmp.delete(tmp.length()-2, tmp.length()).append("}");
        for(Integer i: a){
            map.put(i, i);
        }
        assertEquals(tmp.toString(), map.toString());
    }
      

   
    /**
     * Test of minKey method, of class TreeMap.
     */
    @Test
    public void testMinKey() {
        System.out.println("minKey");
        for(Integer i: a){
            map.put(i, i);
        }
        
        for(Integer i: aSorted){
            assertEquals(i,map.minKey());
            map.remove(i);
        }
    }

    /**
     * Test of maxKey method, of class TreeMap.
     */
    @Test
    public void testMaxKey() {
        System.out.println("maxKey");
        for(Integer i: a){
            map.put(i, i);
        }
        
        for(int i = aSorted.length-1;i>=0;i--){
            assertEquals(aSorted[i],map.maxKey());
            map.remove(aSorted[i]);
        }
    }
    
}
