/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rlv.cosc60;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russel
 */
public class SLLStackTest {
    
    public SLLStackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of push method, of class SLLStack.
     */
    @Test
    public void testPush() {
        System.out.println("push");
        Stack<Integer> stack = new SLLStack<>();
        
        for(int i = 0;i<10;i++){
            stack.push(i);
            assertEquals((Integer)i,stack.peek());
        }
        
    }

    /**
     * Test of pop method, of class SLLStack.
     */
    @Test
    public void testPop_LastInFirstOut() {
        System.out.println("pop");
        int arr[] = {1,2,3,4,5,6,7,8};
        Stack<Integer> stack = new SLLStack<>();
        
        for(int e:arr){
            stack.push(e);
        }
        
        for(int i = arr.length-1; i >= 0; i--){
            assertEquals((Integer)arr[i], stack.pop());
        }
    }
    
    
    /**
     * Test of peek method, of class SLLStack.
     */
    @Test
    public void testPeek() {
        System.out.println("peek");
        SLLStack instance = new SLLStack();
        Object expResult = null;
        Object result = instance.peek();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class SLLStack.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        SLLStack instance = new SLLStack();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class SLLStack.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        SLLStack instance = new SLLStack();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class SLLStack.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        SLLStack instance = new SLLStack();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class SLLStack.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        SLLStack instance = new SLLStack();
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
