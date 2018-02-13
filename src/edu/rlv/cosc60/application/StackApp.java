package edu.rlv.cosc60.application;

import edu.rlv.cosc60.ArrayStack;
import edu.rlv.cosc60.SLLStack;
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
        Stack<String> stack = new SLLStack<>();
        
        stack.push("John");
        stack.push("Mark");
        stack.push("Ana");
        
        for(String s:stack){
            System.out.println(s);
        }
        
        System.out.println(stack);
//        System.out.println(stack.peek());
//        System.out.println(stack.pop());
//        System.out.println(stack);

    }
    
}
