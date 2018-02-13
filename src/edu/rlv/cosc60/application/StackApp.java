package edu.rlv.cosc60.application;

import edu.rlv.cosc60.ArrayStack;
import edu.rlv.cosc60.Stack;

/**
 *
 * @author russel
 */
public class StackApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Stack<String> stack = new ArrayStack<>(1);
        
        stack.push("Mark");
        stack.push("John");
        stack.push("Ana");
        
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack);

    }
    
}
