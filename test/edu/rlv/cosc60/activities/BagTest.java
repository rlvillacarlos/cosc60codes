package edu.rlv.cosc60.activities;

import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author russel
 */
public class BagTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    private Bag<Integer> bag;
    
    @Before
    public void setUp() {
        bag = new Bag<>(1);
    }
    
    @After
    public void tearDown() {
        System.out.println("");
    }

    
     /**
     * Tests for addAll
     */
    
    @Test
    public void testAddAll_ShouldReturnTrueWhenItemsAreAdded(){
        System.out.println("AddAll: Should Return True When Items Are Added");
        
        Integer toAdd[] = generateData(10);
        String dataset = arrayToString(toAdd);
        System.out.printf("Dataset: %s%n",dataset);
        
        String msg = String.format("Expected to return true after adding all of  %s", dataset);
        assertTrue(msg,bag.addAll(toAdd));
    }
    
    @Test
    public void testAddAll_ShouldReturnFalseWhenNoItemsWereAdded(){
        System.out.println("AddAll: Should Return False When No Items Were Added");
        
        Integer toAdd[] = {};
        String dataset = arrayToString(toAdd);
        System.out.printf("Dataset: %s%n",dataset);
        
        String msg = String.format("Expected to return false when no items were added");
        assertFalse(msg,bag.addAll(toAdd));
    }
    
    @Test
    public void testAddAll_ShouldAddAllItems(){
        System.out.println("AddAll: Should Add All Items");
        
        Integer toAdd[] = generateData(10);
        String dataset = arrayToString(toAdd);
        System.out.printf("Dataset: %s%n",dataset);
        
        bag.addAll(toAdd);
        for(Integer e:toAdd){
            String msg = String.format("Expected to include all of %s but %d was not found."
                                    , dataset, e);
            assertTrue(msg,bag.contains(e));
        }
    }
    
    @Test
    public void testAddAll_ShouldAddAllItemsWithRepetition(){
        System.out.println("AddAll: Should Add All Items With Repetition");
        
        Integer toAdd[] = generateData(10);
        toAdd = stretch(toAdd,new Integer[toAdd.length*2],2);
        String dataset = arrayToString(toAdd);
        System.out.printf("Dataset: %s%n",dataset);
       
        bag.addAll(toAdd);
        for(Integer e:toAdd){
            String msg = String.format("Expected to allow repetition but %d was not added repeatedly.", e);
            assertTrue(msg,bag.count(e) == 2);
        }
    }
    
    @Test
    public void testAddAll_ShouldIgnoreNull(){
        System.out.println("AddAll: Should Ignore Null");
        
        Integer toAdd[] = generateData(10);
        toAdd[0] = null;
        String dataset = arrayToString(toAdd);
        System.out.printf("Dataset: %s%n",dataset);
        
        bag.addAll(toAdd);
        assertTrue("Expected to ignore null",bag.size()== 9);
    }
    
    @Test
    public void testAddAll_ShouldThrowIllegalArgumentException(){
        System.out.println("AddAll: Should Throw IllegalArgumentException");
        
        Integer toAdd[] = null;
        String dataset = "null";
        System.out.printf("Dataset: %s%n",dataset);
        
        thrown.expect(IllegalArgumentException.class);
        bag.addAll(toAdd);
        
    }
    
    /**
     * Tests for removeAll
     */
    
    @Test
    public void testRemoveAll_ShouldReturnTrueWhenItemsAreRemoved(){
        System.out.println("RemoveAll: Should Return True When Items Are Removed");
        
        Integer data[] = generateData(10);
        String dataset = arrayToString(data);
        System.out.printf("Dataset: %s%n",dataset);
        
        String msg = String.format("Expected to return true after removing all of  %s", dataset);
        bag.addAll(data);
        assertTrue(msg,bag.removeAll(data));
    }
    
    @Test
    public void testRemoveAll_ShouldReturnFalseWhenNoItemsWereRemoved(){
        System.out.println("RemoveAll: Should Return False When No Items Were Removed");
        
        Integer toAdd[] = generateData(10);
        Integer toRemove[] = new Integer[toAdd.length];
        for(int i =0; i < toAdd.length; i++){
            toRemove[i] = -toAdd[i];
        }
        
        String dataset = arrayToString(toAdd);
        System.out.printf("Dataset: %s%n",dataset);
        System.out.printf("Dataset to remove: %s%n",arrayToString(toRemove));
                
        String msg = String.format("Expected to return false when no items were removed");
        bag.addAll(toAdd);
        assertFalse(msg,bag.removeAll(toRemove));
    }
    
    @Test
    public void testRemoveAll_ShouldOnlyRemoveGivenItems(){
        System.out.println("RemoveAll: Should Only Remove Given Items");
        
        Integer toRemove[] = generateData(10);
        Integer toRetain[] = new Integer[toRemove.length];
        for(int i =0; i < toRemove.length; i++){
            toRetain[i] = -toRemove[i];
        }
        Integer data[] = new Integer[toRemove.length + toRetain.length];
        System.arraycopy(toRemove, 0, data, 0, toRemove.length);
        System.arraycopy(toRetain, 0, data, toRemove.length, toRetain.length);
        
        String dataset = arrayToString(data);
        System.out.printf("Dataset: %s%n",dataset);
        System.out.printf("Dataset to remove: %s%n",arrayToString(toRemove));
                
        bag.addAll(data);
        bag.removeAll(toRemove);
        for(Integer e:toRetain){
            String msg = String.format("Expected not to remove items from %s but %d was not found."
                                    , arrayToString(toRetain), e);
            assertTrue(msg,bag.contains(e));
        }
    }
    
    @Test
    public void testRemoveAll_ShouldRemoveAllOccurences(){
        System.out.println("RemoveAll: Should Remove All Occurences");
        
        Integer toRemove[] = generateData(10);
        Integer toAdd[] = stretch(toRemove,new Integer[toRemove.length*2], 2);
        
        String dataset = arrayToString(toAdd);
        String datasetToRemove = arrayToString(toRemove);
        System.out.printf("Dataset: %s%n",dataset);
        System.out.printf("Dataset to remove: %s%n",datasetToRemove);
        
        for(Integer e:toAdd){
            bag.add(e);
        }
        bag.removeAll(toRemove);
        for(Integer e:toRemove){
            String msg = String.format("Expected to remove all occurences of items in %s but %d was found."
                                    , datasetToRemove, e);
            assertFalse(msg,bag.contains(e));
        }
    }
    
    @Test
    public void testRemoveAll_ShouldIgnoreNull(){
        System.out.println("RemoveAll: Should Ignore Null");
        
        Integer toAdd[] = generateData(10);
        Integer toRemove[] = new Integer[toAdd.length];
        System.arraycopy(toAdd, 0, toRemove, 0, toAdd.length);
        toRemove[0] = null;
        
        String dataset = arrayToString(toAdd);
        System.out.printf("Dataset: %s%n",dataset);
        System.out.printf("Dataset to remove: %s%n",arrayToString(toRemove));
        
        bag.addAll(toAdd);
        bag.removeAll(toRemove);
        assertFalse("Expected to ignore null",bag.isEmpty());
    }
    
    @Test
    public void testRemoveAll_ShouldThrowIllegalArgumentException(){
        System.out.println("RemoveAll: Should Throw IllegalArgumentException");
        
        Integer data[] = null;
        String dataset = "null";
        System.out.printf("Dataset: %s%n",dataset);
        
        thrown.expect(IllegalArgumentException.class);
        bag.removeAll(data);
    }
    
    /**
     * Tests for containsAll
     */
    
    @Test
    public void testContainsAll_ShouldReturnTrueWhenAllItemsAreFound(){
        System.out.println("ContainsAll: Should Return True When All Items Are Found");
        
        Integer toAdd[] = generateData(10);
        
        String dataset = arrayToString(toAdd);
        System.out.printf("Dataset: %s%n",dataset);
        String msg = String.format("Expected to return true when all items in %s are found", dataset);
        
        bag.addAll(toAdd);
        assertTrue(msg,bag.containsAll(toAdd));
    }
    
    @Test
    public void testContainsAll_ShouldReturnFalseWhenNotAllItemsAreFound(){
        System.out.println("ContainsAll: Should Return False When Not All Items Are Found");
        
        Integer toAdd[] = generateData(10);
        Integer toCheck[] = new Integer[toAdd.length];
        System.arraycopy(toAdd, 0, toCheck, 0, toAdd.length);
        toCheck[0] = -1;
        
        String dataset = arrayToString(toAdd);
        String datasetToCheck = arrayToString(toCheck);
        System.out.printf("Dataset: %s%n",dataset);
        System.out.printf("Dataset to check: %s%n",datasetToCheck);
        
        String msg = String.format("Expected to return false when not all items in %s are found", 
                                    datasetToCheck);
        bag.addAll(toAdd);
        assertFalse(msg,bag.containsAll(toCheck));
    }
    
    @Test
    public void testContainsAll_ShouldIgnoreNull(){
        System.out.println("ContainsAll: Should Return True When All Items Are Found");
        
        Integer toAdd[] = generateData(10);
        Integer toCheck[] = new Integer[toAdd.length];
        System.arraycopy(toAdd, 0, toCheck, 0, toAdd.length);
        toCheck[0] = null; 
        
        String dataset = arrayToString(toAdd);
        String datasetToCheck = arrayToString(toCheck);
        System.out.printf("Dataset: %s%n",dataset);
        System.out.printf("Dataset to check: %s%n",datasetToCheck);
        
        String msg = String.format("Expected to ignore null");
        bag.addAll(toAdd);
        assertTrue(msg,bag.containsAll(toCheck));
    }
    
    @Test
    public void testContainsAll_ShouldThrowIllegalArgumentException(){
        System.out.println("ContainsAll: Should Throw IllegalArgumentException");
        Integer data[] = null;
        String dataset = "null";
        
        thrown.expect(IllegalArgumentException.class);
        System.out.printf("Dataset: %s%n",dataset);
        
        bag.containsAll(data);
    }
    
    @Test
    public void testClear_ShouldRemoveAllItems() {
        System.out.println("Clear: Should Remove All Items");
        
        Integer toAdd[] = generateData(10);
        String dataset = arrayToString(toAdd);
        System.out.printf("Dataset: %s%n",dataset);
       
        bag.addAll(toAdd);
        bag.clear();
        for(Integer e:toAdd){
            String msg = String.format("Expected to remove all of %s but %d was  found."
                                    , dataset, e);
            assertFalse(msg,bag.contains(e));
        }
        String msg = "Expected to empty the bag.";
        assertTrue(msg,bag.isEmpty());
    }
    
    /**
     * Helper methods
     */
    
    private Integer[] generateData(int count){
        Random rnd = new Random(System.currentTimeMillis());
        int initValue = rnd.nextInt(100);
        Integer[] data = new Integer[count];
        
        for(int i=0;i<count;i++){
            data[i] = i + initValue;
        }
        
        for(int i = 0,j=count-1;i<count;i++,j--){
            int indx = rnd.nextInt(count-i);
            int tmp = data[j];
            data[j] = data[indx];
            data[indx] = tmp;
        }
        
        return data;
    }
    
    private static <T> String arrayToString(T[] arr){
        StringBuilder build = new StringBuilder("[");
        
        for(T a: arr){
            build.append(String.format(" %s ",a==null?"null":a.toString()));
        }
        
        return build.append("]").toString();
    }
    
    private static <T> T[] stretch(T[] src,T[] target,int factor){
        if(factor >0 && target.length <= src.length*factor){
            for(int i = 0;i<factor;i++){
                System.arraycopy(src, 0, target, src.length*i, src.length);
            }

        }
        return target;
    }
    
}
