package edu.rlv.cosc60.activities;

import edu.rlv.cosc60.checker.PreFixChecker;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russel
 */
public class PreFixExpressionTest {
    PreFixExpression preFixExp;
    
    
    @Before
    public void setUp() {
        preFixExp = new PreFixExpression();
    }
    
     /**
     * Test of toPreFix method, of class PreFixExpression.
     */
    @Test
    public void testToPreFix() {
        System.out.println("toPreFixFix");
        String exp = generateRandomArithmeticExpression(4, 1, 20);
        boolean res = PreFixChecker.checkToPreFix(preFixExp, exp);
        assertTrue(PreFixChecker.getLastErrorMessage(),res);
    }

    /**
     * Test of toFullyParenthesizedInfix method, of class PreFixExpression.
     */
    @Test
    public void testToFullyParenthesizedInfix() {
        System.out.println("toFullyParenthesizedInfix");
        String exp = generateRandomArithmeticExpression(4, 1, 20);
        boolean res = PreFixChecker.checktoFullyParenthesizedInfix(preFixExp, exp);
        assertTrue(PreFixChecker.getLastErrorMessage(),res);
    }

    /**
     * Test of evaluate method, of class PreFixExpression.
     */
    @Test
    public void testEvaluate() {
        System.out.println("Evaluate");
        String exp = generateRandomArithmeticExpression(4, 1, 20);
        boolean res = PreFixChecker.checkEvaluate(preFixExp, exp);        
        assertTrue(PreFixChecker.getLastErrorMessage(),res);
    }
    
    private String generateRandomArithmeticExpression(int numOps, int minVal, int maxVal){
        String ops[] = {"+","-","*"};
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
