/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rlv.cosc60.application;

import edu.rlv.cosc60.Bag;

/**
 *
 * @author russel
 */
public class UnBoundedBagApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bag<String> bag = new Bag<>(1);
        
        bag.add("Mike");
        bag.add("John");
        bag.add("James");
        bag.add("Mark");
        bag.add("Anna");
        bag.add("Mary");
        
        System.out.printf("Bag: %s%n",bag);
        bag.remove("John");
        
        System.out.printf("Bag removing John: %s%n",bag);
        System.out.printf("Bag contains Mike?: %B%n",bag.contains("Mike"));
        System.out.printf("Bag contains John?: %B%n",bag.contains("John"));
        
        System.out.printf("Number of occurences of Anna in the bag: %d%n",bag.count("Anna"));
        bag.add("Anna");
        System.out.printf("Number of occurences of Anna in the bag after adding Anna again: %d%n",bag.count("Anna"));
        
        System.out.println("Iterating the elements: ");
        
        for(String e:bag){
            System.out.printf("   %s%n",e);
        }
    }
    
}
