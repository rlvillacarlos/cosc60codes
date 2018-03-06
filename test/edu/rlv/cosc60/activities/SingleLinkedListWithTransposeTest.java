package edu.rlv.cosc60.activities;

import edu.rlv.cosc60.List;
import edu.rlv.cosc60.checker.SingleLinkedListWithTransposeChecker;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russel
 */
public class SingleLinkedListWithTransposeTest {
    
    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private Character[] samples;
    private List<Character> list;
    private SingleLinkedListWithTransposeChecker<Character> checker;
    
    @Before
    public void setUp() {
        samples = generateSampe(5);
        list = new SingleLinkedListWithTranspose();
        checker = SingleLinkedListWithTransposeChecker.newInstance();
    }
      
    /**
     * Test of add method, of class SingleLinkedListWithTranspose.
     */
    @Test
    public void testAdd_int_GenericType() {
        System.out.println("add(i,e)");
        
        for(int i=0;i<samples.length;i++){
            char c = samples[i];
            System.out.printf("   add(%d,%c) ",i,c);
            list.add(i,c);
            checker.add(i,c);
        }
        System.out.println("");
        System.out.printf("   Expected List: %s%n",checker.contentsAsString());
        System.out.printf("   Actual List: %s%n",list);
        String msgErr = String.format("expected list: %s but was: %s", 
                                        checker.contentsAsString(),
                                        list.toString());
        assertTrue(msgErr, checker.hasSameOrder(list));
        
    }

    /**
     * Test of add method, of class SingleLinkedListWithTranspose.
     */
    @Test
    public void testAdd_GenericType() {
        System.out.println("add(e)");
        
        for (Character c : samples) {
            System.out.printf("   add(%c) ",c);
            list.add(c);
            checker.add(c);
        }
        System.out.println("");
        System.out.printf("   Expected List: %s%n",checker.contentsAsString());
        System.out.printf("   Actual List: %s%n",list);
        String msgErr = String.format("expected list: %s but was: %s", 
                                        checker.contentsAsString(),
                                        list.toString());
        assertTrue(msgErr, checker.hasSameOrder(list));
    }

    
    /**
     * Test of contains method, of class SingleLinkedListWithTranspose.
     */
    @Test
    public void testContains_ShouldChangeList() {
        System.out.println("contains should change list");
        
        for (Character c : samples) {
            list.add(c);
            checker.add(c);
        }
        
        for(char c:samples){
            list.contains(c);
            checker.access(c);
            System.out.printf("   Expected List After contains(\'%c\'): %s%n",c,checker.contentsAsString());
            System.out.printf("   Actual List After contains(\'%c\'): %s%n",c,list);
            String msgErr = String.format("expected list after contains(\'%c\'): %s but was: %s", 
                                        c,
                                        checker.contentsAsString(),
                                        list.toString());
            assertTrue(msgErr, checker.hasSameOrder(list));

        }
    }

    @Test
    public void testContains_ShouldNotChangeList() {
        System.out.println("contains should not change list");
        
        for (Character c : samples) {
            list.add(c);
            checker.add(c);
        }
        
        char c = 'a';

        list.contains(c);
        checker.access(c);
        System.out.printf("   Expected List After contains(\'%c\'): %s%n",c,checker.contentsAsString());
        System.out.printf("   Actual List After contains(\'%c\'): %s%n",c,list);
        String msgErr = String.format("expected list after contains(\'%c\'): %s but was: %s", 
                                    c,
                                    checker.contentsAsString(),
                                    list.toString());
        assertTrue(msgErr, checker.hasSameOrder(list));

    }
    
    /**
     * Test of indexOf method, of class SingleLinkedListWithTranspose.
     */
    @Test
    public void testIndexOf_ShouldReturnUpdatedIndex() {
        System.out.println("indexOf Should Return Updated Index");
        
        for (Character c : samples) {
            list.add(c);
            checker.add(c);
        }
        
        for(char c:samples){
            int i = list.indexOf(c);
            boolean result = checker.isAtIndex(c, i);
            String errMsg = String.format("%c is not expected at index %d", c,i);    
            System.out.printf("   Expected List After indexOf(\'%c\'): %s%n",c,checker.contentsAsString());
            System.out.printf("   Actual List After indexOf(\'%c\'): %s%n",c,list);
            assertTrue(errMsg,result);
        }
        
    }
    
    @Test
    public void testIndexOf_ShouldUpdatedList() {
        System.out.println("indexOf Should Update List");
        
        for (Character c : samples) {
            list.add(c);
            checker.add(c);
        }
        
        for(char c:samples){
            int i = list.indexOf(c);
            checker.access(c);
            System.out.printf("   Expected List After indexOf(\'%c\'): %s%n",c,checker.contentsAsString());
            System.out.printf("   Actual List After indexOf(\'%c\'): %s%n",c,list);
            String msgErr = String.format("expected list after indexOf(\'%c\'): %s but was: %s", 
                                        c,
                                        checker.contentsAsString(),
                                        list.toString());
            assertTrue(msgErr, checker.hasSameOrder(list));
        }
        
    }
    
    @Test
    public void testIndexOf_ShouldNotUpdatedList() {
        System.out.println("indexOf Should Not Update List");
        
        for (Character c : samples) {
            list.add(c);
            checker.add(c);
        }
        

        char c = 'a';
        list.indexOf(c);
        checker.access(c);
        System.out.printf("   Expected List After indexOf(\'%c\'): %s%n",c,checker.contentsAsString());
        System.out.printf("   Actual List After indexOf(\'%c\'): %s%n",c,list);
        String msgErr = String.format("expected list after indexOf(\'%c\'): %s but was: %s", 
                                    c,
                                    checker.contentsAsString(),
                                    list.toString());
        assertTrue(msgErr, checker.hasSameOrder(list));

    }
    
    private Character[] generateSampe(int size){
        Character chars[] = new Character[size];
        Random rnd = new Random(System.currentTimeMillis());
        
        for(int i = 0;i<size;i++){
            int last = ALPHABET.length - i - 1;
            int pos = rnd.nextInt(last+1);
            chars[i] = ALPHABET[pos];
            
            char tmp = ALPHABET[last]; 
            ALPHABET[last] = ALPHABET[pos];
            ALPHABET[pos] = tmp;
        }
        
        return chars;
    }
    
}
