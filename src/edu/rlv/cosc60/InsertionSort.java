package edu.rlv.cosc60;

import java.util.Comparator;

/**
 *
 * @author russel
 */
public class InsertionSort {
    public static <T extends Comparable<T>> void sort(T L[]){
        for(int i = 1; i < L.length; i++){
            for(int j = i;j > 0;j--){
                if(L[j].compareTo(L[j-1]) >= 0){
                    break;
                }
                T tmp = L[j];
                L[j] = L[j-1];
                L[j-1] = tmp;
            }
        }
    }
    
    public static <T> void sort(T L[],Comparator<T> cmp){
        for(int i = 1; i < L.length; i++){
            for(int j = i;j > 0;j--){
                if(cmp.compare(L[j],L[j-1]) >= 0){
                    break;
                }
                T tmp = L[j];
                L[j] = L[j-1];
                L[j-1] = tmp;
            }
        }
    }
    
}
