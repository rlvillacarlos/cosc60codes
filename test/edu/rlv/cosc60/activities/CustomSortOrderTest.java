package edu.rlv.cosc60.activities;

import static edu.rlv.cosc60.matchers.CustomSortOrderMatcher.*;
import edu.rlv.cosc60.util.ArrayUtil;
import static edu.rlv.cosc60.util.ArrayUtil.*;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russel
 */
public class CustomSortOrderTest {
    private final int k = 10;
    private final int n = 15;
    private Integer O1[];
    private Integer ASort[];
    private Integer ADist[];
    private CustomSortOrder sorter;

  
    
    @Before
    public void setUp() {
        O1 = permute(range(0,k-1));
        ASort = randomArray(range(0,k-1),n);
        ADist = permute(O1);
        sorter = new CustomSortOrder(O1);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_ShouldThrowIllegalArgumentExceptionWhenInvalidRange(){
        Integer[] o = range(2, k-1);
        CustomSortOrder failedSorter = new CustomSortOrder(o);
        failedSorter.sort(o);
        
        o = range(0, k-1);
        int i = new Random(System.currentTimeMillis()).nextInt(k);        
        o[i]=k;
        failedSorter = new CustomSortOrder(o);
        failedSorter.sort(o);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_ShouldThrowIllegalArgumentExceptionWhenRepeated(){    
        Integer o[] = permute(expand(range(0,5), 2));
        CustomSortOrder failedSorter = new CustomSortOrder(o);
        failedSorter.sort(o);
    }
    
    /**
     * Test of sort method, of class CustomSortOrder.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSort_ShouldThrowIllegalArgumentExceptionWhenInvalidRange() {
        //Should fail since range is from 0 to k-1 only
        Integer[] A = permute(range(0,k*2));
        sorter.sort(A);
    }

    @Test
    public void testSort_ShouldSortWithRespectToCustomOrder(){
        System.out.println("Should Sort With Respect To Custom Order");
        System.out.printf("   O: %s%n",ArrayUtil.toString(O1));
        System.out.printf("   A: %s%n",ArrayUtil.toString(ASort));
        assertThat(sorter,canCustomSort(O1, ASort));
    }    
       
    /**
     * Test of distance method, of class CustomSortOrder.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDistance_ShouldThrowIllegalArgumentExceptionWhenInvalidRange() {
        Integer[] A = new Integer[O1.length];
        System.arraycopy(O1, 0, A, 0, O1.length);
        int i = new Random(System.currentTimeMillis()).nextInt(O1.length);
        A[i] = O1.length;
        sorter.distance(A);
    }
    
    @Test
    public void testDistance_ShouldComputeCorrectDistance(){
        System.out.println("Should Compute Correct Distance");
        System.out.printf("   O: %s%n",ArrayUtil.toString(O1));
        System.out.printf("   A: %s%n",ArrayUtil.toString(ADist));
        assertThat(sorter,canComputeDistance(O1, ADist));
    }
    
}
