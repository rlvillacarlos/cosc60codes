package edu.rlv.cosc60.application;

import edu.rlv.cosc60.MinHeapPQ;
import edu.rlv.cosc60.MinPQ;
import edu.rlv.cosc60.util.ArrayUtil;

/**
 *
 * @author russel
 */
public class PQSample1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer a[] = {5,6,1,2,4,10,14,15,21};
        MinPQ<Integer> pq = new MinHeapPQ<>(a);
        for(int i = 1;i<=5;i++){
            System.out.println(pq.removeMin());
        }
        
        pq = new MinHeapPQ<>(a);
        int i = 0;
        while(!pq.isEmpty()){
            a[i] = pq.removeMin();
            i++;
        }
        ArrayUtil.print(a);
        
        
    }
    
}
