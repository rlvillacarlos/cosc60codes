package edu.rlv.cosc60;

import java.util.Iterator;
import java.util.Random;
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
public class ArrayListTest {
    private List<Integer> list;
    private Integer data[];
    private Random rnd;
            
    public ArrayListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        list = new ArrayList<>();
        rnd = new Random(System.currentTimeMillis());
        data = new Integer[10];
        for(int i=0;i<data.length;i++){
            data[i] = rnd.nextInt(100) + 1;
            list.add(i,data[i]);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of get method, of class ArrayList.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        
        for(int i = 0;i<data.length;i++){
            Integer n = data[i];
            assertEquals(n, list.get(i));
        }
    }

    /**
     * Test of set method, of class ArrayList.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        int i = 0;
        Object e = null;
        ArrayList instance = new ArrayList();
        Object expResult = null;
        Object result = instance.set(i, e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrayList.
     */
    @Test
    public void testAdd_int_GenericType() {
        System.out.println("add");
        int i = 0;
        Object e = null;
        ArrayList instance = new ArrayList();
        boolean expResult = false;
        boolean result = instance.add(i, e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrayList.
     */
    @Test
    public void testAdd_GenericType() {
        System.out.println("add");
        Object e = null;
        ArrayList instance = new ArrayList();
        boolean expResult = false;
        boolean result = instance.add(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrayList.
     */
    @Test
    public void testRemove_int() {
        System.out.println("remove");
        int i = 0;
        ArrayList instance = new ArrayList();
        Object expResult = null;
        Object result = instance.remove(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrayList.
     */
    @Test
    public void testRemove_GenericType() {
        System.out.println("remove");
        Object e = null;
        ArrayList instance = new ArrayList();
        boolean expResult = false;
        boolean result = instance.remove(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class ArrayList.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Object e = null;
        ArrayList instance = new ArrayList();
        boolean expResult = false;
        boolean result = instance.contains(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indexOf method, of class ArrayList.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        Object e = null;
        ArrayList instance = new ArrayList();
        int expResult = 0;
        int result = instance.indexOf(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class ArrayList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        ArrayList instance = new ArrayList();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class ArrayList.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        ArrayList instance = new ArrayList();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class ArrayList.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        ArrayList instance = new ArrayList();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ArrayList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ArrayList instance = new ArrayList();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class ArrayList.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        ArrayList instance = new ArrayList();
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
