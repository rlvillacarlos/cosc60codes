/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rlv.cosc60;

/**
 *
 * @author russel
 */
public class Recursion1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a[] = {1,2,3,4,5,9,15,18}; 
        printDiv(a, 3);
    }
    public static void printNPositive(int n) {
        if (n == 1) {
            System.out.println(1);
        } else {
            printNPositive(n- 1);
            System.out.println(n);
        }
    }
    public static int sum(int[] a) {
        return sum(a,0);
    }

    public static int sum(int[] a,int start) {
        if (start == a.length- 1) {
            return a[start];
        }
        return a[start] + sum(a,start + 1);
    }
    
    public static void printDiv(int[] a, int d){
        printDiv(a, d,0);
    }
    public static void printDiv(int[] a, int d,int i){
        if(a[i]%d==0){
            System.out.println(a[i]);
        }
        
        if(i < a.length-1){
            printDiv(a, d, i+1);
        }
    }
    
    
}
