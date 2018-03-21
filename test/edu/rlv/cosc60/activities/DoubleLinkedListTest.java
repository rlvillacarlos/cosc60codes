package edu.rlv.cosc60.activities;

import edu.rlv.cosc60.List;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static edu.rlv.cosc60.util.ArrayUtil.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.Rule;
import org.junit.rules.Timeout;
import static edu.rlv.cosc60.matchers.DoubleLinkedListMatchers.*;
import org.junit.BeforeClass;

/**
 *
 * @author russel
 */
    
public class DoubleLinkedListTest {
    private DoubleLinkedList<Integer> list;
    private static Integer[] data;
    private static final int LO = 1;
    private static final int HI = 1000000;

    @Rule
    public final Timeout t = new Timeout(3, TimeUnit.SECONDS);
    
    @BeforeClass
    public static void setUpClass(){
        data = permute(range(LO, HI));
    }
    
    @Before
    public void setUp() {
        list = new DoubleLinkedList<>();        
        for(int i = data.length-1;i>=0;i--){
            list.add(0,data[i]);
        }
    }
    
    /**
     * Test of structure of class DoubleLinkedList.
     */
    
    @Test
    public void testStructure_ShouldHaveAHeadNode(){
        System.out.println("testStructure_ShouldHaveAHeadNode");
        list = new DoubleLinkedList<>();
        assertThat(list, hasHeadNode());
    }
    
    @Test
    public void testStructure_ShouldBeCircular(){
        System.out.println("testStructure_ShouldBeCircular");
        assertThat(list, isCircular());
    }
    
    /**
     * Test of get method, of class DoubleLinkedList.
     */    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet_ShouldThrowIndexOutOfBounds() {
        System.out.println("testGet_ShouldThrowIndexOutOfBounds");
        list.get(data.length);
        list.get(-1);
    }

    @Test()
    public void testGet_ShouldReturnCorrectElement() {
        System.out.println("testGet_ShouldReturnCorrectElement");        
        int index = selectRandomIndex();
        assertEquals(data[index],list.get(index)) ;
    }
    
    /**
     * Test of set method, of class DoubleLinkedList.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet_ShouldThrowIndexOutOfBounds() {
        System.out.println("testSet_ShouldThrowIndexOutOfBounds");
        list.set(data.length,-1);
        list.set(-1,-1);
    }
    
    @Test
    public void testSet_ShouldReturnOldValue() {
        System.out.println("testSet_ShouldReturnOldValue");
        int index = selectRandomIndex();
        Integer oldVal = list.set(index,-data[index]);
        assertEquals(data[index],oldVal);
    }
    
    @Test
    public void testSet_ShouldUpdateValue() {
        System.out.println("testSet_ShouldUpdateValue");
        int index = selectRandomIndex();
        list.set(index,-data[index]);
        assertEquals(-data[index],(long)list.get(index));
    }

    /**
     * Test of add method, of class DoubleLinkedList.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd_int_GenericType_ShouldThrowIndexOutOfBounds() {
        System.out.println("testAdd_int_GenericType_ShouldThrowIndexOutOfBounds");
        list.add(data.length+1, -1);
        list.add(-1, -1);
    }
    
    @Test
    public void testAdd_int_GenericType_ShouldInsertTheElement() {
        System.out.println("testAdd_int_GenericType_ShouldInsertTheElement");
        Random  rnd = new Random();
        int index = rnd.nextInt(data.length+1);
        list.add(index,-data[index]);
        assertEquals(-data[index], (long)list.get(index));
    }

    @Test
    public void testAdd_int_GenericType_ShouldIncreaseTheSize() {
        System.out.println("testAdd_int_GenericType_ShouldIncreaseTheSize");
        int index = selectRandomIndex();
        list.add(index,-data[index]);
        assertTrue("Size did not increase after adding an elemet",  data.length < list.size());
    }
    
    /**
     * Test of remove method, of class DoubleLinkedList.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove_int_ShouldThrowIndexOutOfBounds() {
        System.out.println("testRemove_int_ShouldThrowIndexOutOfBounds");
        list.remove(-1);
        list.remove(data.length);
    }

    @Test
    public void testRemove_int_ShouldReturnRemovedElement() {
        System.out.println("testRemove_int_ShouldReturnRemovedElement");
        int index = selectRandomIndex();
        Integer e = list.remove(index);
        assertEquals(data[index], e);
    }
    
    @Test
    public void testRemove_int_ShouldRemoveElement() {
        System.out.println("testRemove_int_ShouldRemoveElement");
        int index = selectRandomIndex();
        list.remove(index);
        assertFalse("Element was not removed",list.contains(data[index]));
    }
    
    /**
     * Test of indexOf method, of class DoubleLinkedList.
     */
    @Test
    public void testIndexOf_ShouldReturnNegativeOne() {
        System.out.println("testIndexOf_ShouldReturnNegativeOne");
        int index = selectRandomIndex();
        assertEquals(-1, list.indexOf(-data[index]));
    }
    
    @Test
    public void testIndexOf_ShouldReturnCorrectIndex() {
        System.out.println("testIndexOf_ShouldReturnCorrectIndex");
        int index = selectRandomIndex();
        assertEquals(index, list.indexOf(data[index]));
    }

    
    /**
     * Test of sublist method, of class DoubleLinkedList.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSublist_ShouldThrowIndexOutOfBounds() {
        System.out.println("testSublist_ShouldThrowIndexOutOfBOunds");
        list.sublist(-1, data.length-1);
        list.sublist(0, data.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSublist_ShouldThrowIllegalArgument() {
        System.out.println("testSublist_ShouldThrowIllegalArgument");
        list.sublist(data.length-1,0);
    }
    
    @Test
    public void testSublist_ShouldReturnACorrectSublist() {
        System.out.println("testSublist_ShouldReturnACorrectSublist");
        Random rnd = new Random(System.currentTimeMillis());
        
        int start = rnd.nextInt(data.length);
        int end = start + rnd.nextInt(data.length-start);
        
        List<Integer> l = list.sublist(start, end);
        assertEquals("Sublist has invalid size",end-start+1,l.size());
        
        int i = 0;
        
        for(Integer e:l){
            String msg = String.format("Element %d not expected at index %d", e,i);
            assertEquals(msg,data[start+i],e);
            i++;
        }
        
    }
    
    /**
     * Test of iterator method, of class DoubleLinkedList.
     */
    @Test
    public void testIterator() {
        System.out.println("testIterator");
        int i = 0;
        for(Integer e:list){
            assertEquals("Elements were iterated incorrectly",data[i],e);
            i++;
        }
        if(i<data.length){
            fail("The entire list was not iterated");
        }
    }

    /**
     * Test of reverseIterator method, of class DoubleLinkedList.
     */
    @Test
    public void testReverseIterator() {
        System.out.println("testReverseIterator");
        int i = data.length-1;
        Iterator<Integer> itr = list.reverseIterator();
        while(itr.hasNext()){
            Integer e = itr.next();
            assertEquals("Elements were iterated incorrectly",data[i],e);
            i--;
        }
        if(i > 0){
            fail("The entire list was not iterated");
        }
    }
    
    private int selectRandomIndex(){
        Random  rnd = new Random(System.currentTimeMillis());
        return rnd.nextInt(data.length);
    }
    
    
}
