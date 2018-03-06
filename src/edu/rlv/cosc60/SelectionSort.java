package edu.rlv.cosc60;

/**
 *
 * @author russel
 */
public class SelectionSort {
    public static <T extends Comparable<T>> void sort(T L[]){
        for (int i = 0; i < L.length -1; i++){
            int k = i;
            for(int j = i+1; j < L.length; j++){
                if(L[j].compareTo(L[k]) < 0){
                    k = j;
                }
            }
            T tmp = L[i];
            L[i] = L[k];
            L[k] = tmp;
        }
    }
}
