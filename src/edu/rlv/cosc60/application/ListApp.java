package edu.rlv.cosc60.application;

import edu.rlv.cosc60.ArrayList;
import edu.rlv.cosc60.List;

/**
 *
 * @author russel
 */
public class ListApp {

    /**
     * @param args the command line arguments
     */
    
    public static <E> void reverse(List<E> list){
        
    }
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(1);
        
        list.add("James");
        list.add("Ana");
        list.add(0,"Mark");
        list.add(1,"Beth");
        
        System.out.println(list);
        
        System.out.println(list.remove(1));
        
        System.out.println(list);
        
        list.remove("James");

        System.out.println(list);

        list.set(0, "Peter");
        
        System.out.println(list);

        System.out.println(list.get(1));
        
        list.clear();

        System.out.println(list);
        

    }
    
}
