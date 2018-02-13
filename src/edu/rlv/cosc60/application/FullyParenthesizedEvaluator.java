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
    
    public static String evaluate(String exp){
        Stack<Character> operator = new Stack<>(1);
        Stack<String> operands = new Stack<>(1);
        
        int i = 0;
        
        while(i < exp.length()){
            char c = exp.charAt(i);
            
            if(c == '+' || c == '-' || c == '*' || c=='/'){//If operator
                operator.push(c);
                i++;
            } else if (c == ')') {//If closing parenthesis
                 String op2 = operands.pop(), op1 = operands.pop();
                 char op = operator.pop();
                 operands.push(op + " " + op1 + " " + op2);
//                 switch(operator.pop()){
//                     case '+': operands.push(op1 + op2);break;
//                     case '-': operands.push(op1 - op2);break;
//                     case '*': operands.push(op1 * op2);break;
//                     case '/': operands.push(op1 / op2);break;
//                 }
                 i++;
            } else if(Character.isDigit(c)) {//If a digit
                //Read all consecutove digits in the input and convert to float
                StringBuilder s = new StringBuilder();
                do{
                    s.append(c);
                    i++;
                    c = exp.charAt(i);
                }while(Character.isDigit(c));      
                
//                int operand = Integer.parseInt(s.toString());
                operands.push(s.toString());
            
            } else {//Ignore all other input
                i++;
            }
        }
        
        return operands.size() == 1? operands.pop(): "";
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Expression: ");
        String exp = in.nextLine();
        System.out.printf("Answer: %s %n",evaluate(exp));
        
    }
    
    
}
