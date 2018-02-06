/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rlv.cosc60;

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
public class BoundedBagTest {
    
    public BoundedBagTest() {
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

    @Test
    public void testConstructor(){
        testConstructorWhenInvalidCapacity();
    }
    
    private void testConstructorWhenInvalidCapacity(){
        try{
            BoundedBag<String> bag = new BoundedBag<>(-1);
            fail("Invalid Capacity did not throw IllegalArgumentException");
        }catch(IllegalArgumentException ex){
            //Ignore
        }catch(Exception ex){
            fail("Expected exception not thrown");
        }
    }
    
    /**
     * Test of add method, of class BoundedBag.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Object e = null;
        BoundedBag instance = null;
        boolean expResult = false;
        boolean result = instance.add(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class BoundedBag.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Object e = null;
        BoundedBag instance = null;
        boolean expResult = false;
        boolean result = instance.remove(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class BoundedBag.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Object e = null;
        BoundedBag instance = null;
        boolean expResult = false;
        boolean result = instance.contains(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class BoundedBag.
     */
    @Test
    public void testCount() {
        System.out.println("count");
        Object e = null;
        BoundedBag instance = null;
        int expResult = 0;
        int result = instance.count(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class BoundedBag.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        BoundedBag instance = null;
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of capacity method, of class BoundedBag.
     */
    @Test
    public void testCapacity() {
        System.out.println("capacity");
        BoundedBag instance = null;
        int expResult = 0;
        int result = instance.capacity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class BoundedBag.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        BoundedBag instance = null;
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFull method, of class BoundedBag.
     */
    @Test
    public void testIsFull() {
        System.out.println("isFull");
        BoundedBag instance = null;
        boolean expResult = false;
        boolean result = instance.isFull();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
