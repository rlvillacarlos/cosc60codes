package edu.rlv.cosc60;

import edu.rlv.cosc60.util.ArrayUtil;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russel
 */
public class ChainedHashMapTest {
    Map<Integer,Integer> map;
    Integer aSorted[];
    boolean isHit[];
    Integer a[];
    
    @Before
    public void setUp() {
        map = new ChainedHashMap<>();
        aSorted = ArrayUtil.range(1, 100000);
        isHit = new boolean[aSorted.length];
        a = ArrayUtil.permute(aSorted);
        
    }
    
    /**
     * Test of put method, of class ChainedHashMap.
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
     * Test of remove method, of class ChainedHashMap.
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
     * Test of contains method, of class ChainedHashMap.
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
     * Test of get method, of class ChainedHashMap.
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
     * Test of getKeys method, of class ChainedHashMap.
     */
    @Test
    public void testGetKeys() {
        System.out.println("getKeys");

        for(Integer i: a){
            map.put(i, i);
        }
        
        int j = 0;
        for(Integer i: map.getKeys()){
            isHit[i-1] = true;
            j++;
        }
        for(int i = 0;i<isHit.length;i++){
            assertTrue(String.format("Key %d is missing", i+1),isHit[i]);
        }
        
    }

    /**
     * Test of getValues method, of class ChainedHashMap.
     */
    @Test
    public void testGetValues() {
        System.out.println("getValues");
        
        for(Integer i: a){
            map.put(i, i);
        }
        
        int j = 0;
        for(Integer i: map.getValues()){
            isHit[i-1] = true;
        }
        for(int i = 0;i<isHit.length;i++){
            assertTrue(String.format("Value %d is missing", i+1),isHit[i]);
        }
    }

    /**
     * Test of size method, of class ChainedHashMap.
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
     * Test of clear method, of class ChainedHashMap.
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
     * Test of isEmpty method, of class ChainedHashMap.
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

//    /**
//     * Test of toString method, of class ChainedHashMap.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        
//        for(Integer i: a){
//            map.put(i, i);
//        }
//        
//        String mString = map.toString().replace(","," ");
//        
//        for(Integer i: aSorted){
//            String s = String.format("%d: %d", i,i);
//            mString = mString.replace(s, "");
//        }
//        mString = mString.trim();
//        assertTrue(mString.isEmpty());
//    }
//    
}
