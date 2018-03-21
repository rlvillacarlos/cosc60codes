package edu.rlv.cosc60.activities;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author russel
 */
public class CustomSortOrder {    
    private static class CustomRankComparator implements Comparator<Integer>{
        private final Integer[] ranks;
        
        public CustomRankComparator(Integer o[]) {
            ranks = new Integer[o.length];
            for(int i=0;i<o.length;i++){
                ranks[o[i]]=i;
            }
        }
        
        @Override
        public int compare(Integer o1, Integer o2) {
            return ranks[o1].compareTo(ranks[o2]);
        }
    
    }
    
    private final Integer[] O;
    private final CustomRankComparator cmp;
    
    public CustomSortOrder(Integer O[]) {
//        throw new UnsupportedOperationException("Not Yet Implemented");
        for(Integer e: O){
            if(e < 0 || e > O.length-1){
                throw new IllegalArgumentException();
            }
        }
        
        Integer tmp[] = new Integer[O.length];
        System.arraycopy(O, 0, tmp, 0, O.length);
        Arrays.sort(tmp);
        
        for(int i=0; i< tmp.length-1;i++){
            if(tmp[i].equals(tmp[i+1])){
                throw new IllegalArgumentException();
            }
        }
        
        this.O = O;
        this.cmp = new CustomRankComparator(O);
    }
    
    public void sort(Integer A[]){
        for(Integer e: A){
            if(e < 0 || e > O.length-1){
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(A,cmp);
        
    }
       
    public int distance(Integer A[]){
        throw new UnsupportedOperationException("Not Yet Implemented");
    }
}
