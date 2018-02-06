package edu.rlv.cosc60.application;

import edu.rlv.cosc60.Counter;

/**
 *
 * @author russel
 */
public class CounterApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create a new counter with no limit
        Counter ctr = new Counter();
        //Output the initial value of the counter
        System.out.printf("Initial value: %d%n",ctr.getValue());
        //Increment the counter
        ctr.increment();
        //Output the value of the counter after increment
        System.out.printf("Value after increment: %d%n",ctr.getValue());
        //Decrement the counter
        ctr.decrement();
       //Output the value of the counter after decrement        
        System.out.printf("Value after decrement: %d%n",ctr.getValue());
    }
    
}
