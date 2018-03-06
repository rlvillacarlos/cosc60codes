package edu.rlv.cosc60.util;

import java.lang.reflect.Array;
import java.util.Random;

/**
 *
 * @author russel
 */
public class ArrayUtil {
    public static Integer[] range(int lo, int hi){
        Integer out[] = new Integer[hi-lo+1];
        for(int i=lo;i<=hi;i++){
            out[i-lo] = i;
        }
        return out;
    }
    public static <T> T[] permute(T a[]){
        T[] permuted = (T[]) Array.newInstance(a[0].getClass(), a.length);
        System.arraycopy(a, 0, permuted, 0, a.length);
        Random rnd = new Random(System.currentTimeMillis());
        for(int i =0;i<a.length;i++){
            int size = a.length - i;
            int indx = rnd.nextInt(size);

            T tmp = permuted[size - 1];
            permuted[size-1] = permuted[indx];
            permuted[indx] = tmp;
        }
        return permuted;
    }
    
    public static <T> void print(T a[]){
        System.out.println(toString(a));
    }
    
    public static <T> String toString(T a[]){
        StringBuilder tmp = new StringBuilder("[");

        for(T e:a){
            tmp.append(e).append(", ");
        }
        if(tmp.length() > 1){
            tmp.delete(tmp.length()-2, tmp.length());
        }
        return tmp.append("]").toString();
    }
}
