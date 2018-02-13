package edu.rlv.cosc60.application;

import edu.rlv.cosc60.ArrayStack;
import java.util.Scanner;

/**
 *
 * @author russel
 */
public class StringReversal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input: ");
        String str = in.nextLine();
        ArrayStack<Character> stack = new ArrayStack<>(1);
        
        for(int i =0; i<str.length();i++){
            stack.push(str.charAt(i));
        }
        
        StringBuilder tmp = new StringBuilder();
        
        while(!stack.isEmpty()){
            tmp.append(stack.pop());
        }
        
        str = tmp.toString();
        
        System.out.printf("Reversed: %s%n",str);
    }
    
}
