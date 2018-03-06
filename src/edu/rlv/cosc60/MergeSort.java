package edu.rlv.cosc60;

/**
 *
 * @author russel
 */
public class MergeSort {
        
    public static <T extends Comparable<T>> void sort(T L[]){
        T tmp[] = (T[]) new Comparable[L.length];
        sort(L,tmp,0,L.length-1);
    }
    
    private static <T extends Comparable<T>> void sort(T L[], T tmp[], int lo, int hi){
        if(lo<hi){
            int mid = lo + (hi-lo)/2;
            sort(L,tmp,lo,mid);
            sort(L,tmp,mid+1,hi);
            merge(L,tmp, lo, mid, hi);
        }
    }
    
    private static <T extends Comparable<T>> void merge(T L[], T tmp[],int lo, int mid,int hi){
        if(lo < hi){
            int l = lo, r = mid+1;
            for(int i=lo;i<=hi;i++){
                if(r > hi){
                    tmp[i] = L[l];
                    l++;
                }else if(l > mid){
                    tmp[i] = L[r];
                    r++;
                }else if(L[l].compareTo(L[r]) < 0){
                    tmp[i] = L[l];
                    l++;
                }else{
                    tmp[i] = L[r];
                    r++;
                }
            }
            System.arraycopy(tmp, lo, L, lo, hi-lo+1);
        }
    }
}
