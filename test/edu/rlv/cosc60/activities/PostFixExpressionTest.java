package edu.rlv.cosc60.activities;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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
public class PostFixExpressionTest {
    
    public PostFixExpressionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println(generateRandomArithmeticExpression(3, 1, 20));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toPostFix method, of class PostFixExpression.
     */
    @Test
    public void testToPostFix() {
        System.out.println("toPostFix");
        String fullyParenthesizedInfix = "";
        String expResult = "";
        String result = PostFixExpression.toPostFix(fullyParenthesizedInfix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toFullyParenthesizedInfix method, of class PostFixExpression.
     */
    @Test
    public void testToFullyParenthesizedInfix() {
        System.out.println("toFullyParenthesizedInfix");
        String postFix = "";
        String expResult = "";
        String result = PostFixExpression.toFullyParenthesizedInfix(postFix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of evaluate method, of class PostFixExpression.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        String postFix = "";
        float expResult = 0.0F;
        float result = PostFixExpression.evaluate(postFix);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    private String generateRandomArithmeticExpression(int numOps, int minVal, int maxVal){
        String ops[] = {"+","-","*","/"};
        Random rnd = ThreadLocalRandom.current();
        String[] operators = new String[numOps];
        
        for(int i = 0 ;i<numOps;i++){
            operators[i] = ops[rnd.nextInt(ops.length)];
        }
        return generateRandomArithmeticExpression(operators, 0, operators.length-1, minVal, maxVal);
        
    }
    
    private String generateRandomArithmeticExpression(String[] operators, int lo, int hi,int minVal, int maxVal){
        Random rnd = ThreadLocalRandom.current();
        if(hi<lo){
            return String.format("%d", rnd.nextInt(maxVal-minVal+1) + minVal);
        }
        int split = rnd.nextInt(hi - lo + 1) + lo;
        
        return String.format("(%s%s%s)",
                generateRandomArithmeticExpression(operators, lo, split-1, minVal, maxVal),
                operators[split],
                generateRandomArithmeticExpression(operators, split+1, hi, minVal, maxVal));
    }
    
}
