package edu.rlv.cosc60.activities;

import edu.rlv.cosc60.util.ArrayUtil;
import java.util.Arrays;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russel
 */
public class SortedListTest {
    static Integer lRandom[];
    static Integer lRandomSorted[];
    static SortedList<Integer> l1 = new SortedList<>();

    @BeforeClass
    public static void setUpClass() {
        lRandom = ArrayUtil.randomArray(ArrayUtil.range(1, 300000),200000,false);
        lRandomSorted = new Integer[lRandom.length];
        System.arraycopy(lRandom, 0, lRandomSorted, 0, lRandom.length);
        Arrays.sort(lRandomSorted);
    }
    
    @Before
    public void setUp(){
        l1 = new SortedList<>();
        for(Integer i:lRandom){
            l1.add(i);
        }
    }
    
   
    /**
     * Test of add method, of class SortedList.
     */
    @Test(timeout = 2000L)
    public void testAdd() {
        System.out.println("add");
        
        l1 = new SortedList<>();

        for(Integer i:lRandom){
            l1.add(i);
        }
        
        for(Integer i:lRandomSorted){
            assertTrue(l1.contains(i));
        }
    }

    /**
     * Test of get method, of class SortedList.
     */
    @Test(timeout = 2000L)
    public void testGet() {
        System.out.println("get");
        
        int i = 0;
        for(Integer n:lRandomSorted){
            assertEquals(n,l1.get(i));
            i++;
        }
        
    }
    
   
    /**
     * Test of remove method, of class SortedList.
     */
    @Test(timeout = 2000L)
    public void testRemove_int() {
        System.out.println("remove(int i)");
        for(int i = 0; i<lRandomSorted.length;i++){
            assertEquals(l1.remove(0),lRandomSorted[i]);
        }
    }
    
    @Test(timeout = 2000L)
    public void testRemove() {
        System.out.println("remove");
        for(int i = 0; i<lRandomSorted.length;i++){
            assertTrue(l1.remove(lRandomSorted[i]));
            assertFalse(l1.remove(lRandomSorted[i]));
        }
    }

    /**
     * Test of size method, of class SortedList.
     */
    @Test(timeout = 2000L)
    public void testSize() {
        System.out.println("size");
        l1 = new SortedList<>();

        int s = 0;
        for(Integer i:lRandom){
            l1.add(i);
            s++;
            assertEquals(s,l1.size());
        }
    }

    /**
     * Test of clear method, of class SortedList.
     */
    @Test(timeout = 2000L)
    public void testClear() {
        System.out.println("clear");
        l1.clear();
        for(Integer i:lRandomSorted){
            assertFalse(l1.contains(i));
        }
        assertTrue(l1.size()==0);
    }

    /**
     * Test of iterator method, of class SortedList.
     */
    @Test(timeout = 2000L)
    public void testIterator() {
        System.out.println("iterator");
        int j = 0;
        for(Integer i: l1){
            assertEquals(lRandomSorted[j],i);
            j++;
        }
    }
}
