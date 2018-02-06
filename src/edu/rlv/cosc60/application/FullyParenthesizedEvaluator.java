package edu.rlv.cosc60.application;

import edu.rlv.cosc60.Stack;
import java.util.Scanner;

/**
 *
 * @author russel
 */
public class FullyParenthesizedEvaluator {

    /**
     * @param args the command line arguments
     */
    
    public static float evaluate(String exp){
        Stack<Character> operator = new Stack<>(1);
        Stack<Float> operands = new Stack<>(1);
        
        int i = 0;
        
        while(i < exp.length()){
            char c = exp.charAt(i);
            
            if(c == '+' || c == '-' || c == '*' || c=='/'){//If operator
                operator.push(c);
                i++;
            } else if (c == ')') {//If closing parenthesis
                 float op2 = operands.pop(), op1 = operands.pop();
                 switch(operator.pop()){
                     case '+': operands.push(op1 + op2);break;
                     case '-': operands.push(op1 - op2);break;
                     case '*': operands.push(op1 * op2);break;
                     case '/': operands.push(op1 / op2);break;
                 }
                 i++;
            } else if(Character.isDigit(c) || c == '.') {//If a digit
                //Read all consecutove digits in the input and convert to float
                StringBuilder s = new StringBuilder();
                do{
                    s.append(c);
                    i++;
                    c = exp.charAt(i);
                }while(Character.isDigit(c) || c == '.');      
                
                float operand = Float.parseFloat(s.toString());
                operands.push(operand);
            
            } else {//Ignore all other input
                i++;
            }
        }
        
        return operands.size() == 1? operands.pop(): Float.NaN;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Expression: ");
        String exp = in.nextLine();
        System.out.printf("Answer: %.2f %n",evaluate(exp));
        
    }
    
    
}
