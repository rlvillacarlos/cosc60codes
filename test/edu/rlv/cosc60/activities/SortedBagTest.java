package edu.rlv.cosc60.activities;

import edu.rlv.cosc60.AVLMap;
import edu.rlv.cosc60.Map;
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
public class SortedBagTest {
    static Integer lSorted[];
    static Integer lPermuted[];
    static Integer lRandom[];
    static Integer lRandomSorted[];
    static SortedBag<Integer> b1 = new SortedBag<>();
    static SortedBag<Integer> b2 = new SortedBag<>();

    @BeforeClass
    public static void setUpClass() {
        lSorted = ArrayUtil.range(1, 200000);
        lPermuted = ArrayUtil.permute(lSorted);
        lRandom = ArrayUtil.randomArray(lSorted,lSorted.length);
        lRandomSorted = new Integer[lRandom.length];
        System.arraycopy(lRandom, 0, lRandomSorted, 0, lRandom.length);
        Arrays.sort(lRandomSorted);
    }
    
    @Before
    public void setUp(){
        b1 = new SortedBag<>();
        for(Integer i:lRandom){
            b1.add(i);
        }  
        
        b2 = new SortedBag<>();
        for(Integer i:lPermuted){
            b2.add(i);
        }  
    }
    
   
    /**
     * Test of add method, of class SortedBag.
     */
    @Test(timeout = 2000L)
    public void testAdd() {
        System.out.println("add");
        
        b1 = new SortedBag<>();
        b2 = new SortedBag<>();

        for(Integer i:lRandom){
            b1.add(i);
        }
        
        for(Integer i:lRandomSorted){
            assertTrue(b1.contains(i));
        }
        
        
        for(Integer i:lPermuted){
            b2.add(i);
        }
        
        for(Integer i:lSorted){
            assertTrue(b2.contains(i));
        }
        
    }

   
    /**
     * Test of remove method, of class SortedBag.
     */
    
    @Test(timeout = 2000L)
    public void testRemove_element() {
        System.out.println("remove(e)");
        for(int i = 0; i<lSorted.length;i++){
            assertTrue(b2.remove(lSorted[i]));
            assertFalse(b2.remove(lSorted[i]));
        }
    }

    /**
     * Test of size method, of class SortedBag.
     */
    @Test(timeout = 2000L)
    public void testSize() {
        System.out.println("size");
        b1 = new SortedBag<>();
        b2 = new SortedBag<>();

        int s = 0;
        for(Integer i:lRandom){
            b1.add(i);
            s++;
            assertEquals(s,b1.size());
        }
        
        s = 0;
        for(Integer i:lPermuted){
            b2.add(i);
            s++;
            assertEquals(s,b2.size());
        }
        
    }

    /**
     * Test of clear method, of class SortedBag.
     */
    @Test(timeout = 2000L)
    public void testClear() {
        System.out.println("clear");
        b2.clear();
        for(Integer i:lSorted){
            assertFalse(b2.contains(i));
        }
        assertTrue(b2.size()==0);
    }

    /**
     * Test of iterator method, of class SortedBag.
     */
    @Test(timeout = 2000L)
    public void testIterator() {
        System.out.println("iterator");
        int j = 0;
        for(Integer i: b1){
            assertEquals(lRandomSorted[j],i);
            j++;
        }
        
        j = 0;
        for(Integer i: b2){
            assertEquals(lSorted[j],i);
            j++;
        }
    }
}
