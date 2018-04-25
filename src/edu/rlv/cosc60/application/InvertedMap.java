package edu.rlv.cosc60.application;

import edu.rlv.cosc60.Map;
import edu.rlv.cosc60.TreeMap;

/**
 *
 * @author russel
 */
public class InvertedMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Map<Integer,String> id = new TreeMap<>();
        Map<String,Integer> person = new TreeMap<>();
        
        id.put(123, "Mark");
        id.put(223, "Anna");
        id.put(7564, "Ned");
        
        person.put("Mark",123);
        person.put("Anna",223);
        person.put("Ned",7564);
        
        System.out.println(id.get(223));
        System.out.println(person.get("Ned"));
        
    }
    
}
