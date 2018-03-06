package edu.rlv.cosc60;

import edu.rlv.cosc60.util.ArrayUtil;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russel
 */
public class InsertionSortTest {
    /**
     * Test of sort method, of class SelectionSort.
     */
    @Test(timeout = 2000)
    public void testSort() {
        System.out.println("Insertion Sort");
        Comparable[] L = ArrayUtil.range(1, 100);
        Comparable[] toSort = ArrayUtil.permute(L);
        System.out.printf("Before sorting: %s%n",ArrayUtil.toString(toSort));
        InsertionSort.sort(toSort);
        System.out.printf("After sorting: %s%n",ArrayUtil.toString(toSort));

        assertArrayEquals(toSort, L);
    }
    
}
