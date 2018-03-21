/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.rlv.cosc60.application;

import edu.rlv.cosc60.InsertionSort;
import edu.rlv.cosc60.util.ArrayUtil;
import java.util.Arrays;

/**
 *
 * @author russel
 */
public class CircleSorter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Circle circles[] = {new Circle(10),new Circle(5),new Circle(7)};
        ArrayUtil.print(circles);
        Arrays.sort(circles);
        ArrayUtil.print(circles);
    }
    
}
