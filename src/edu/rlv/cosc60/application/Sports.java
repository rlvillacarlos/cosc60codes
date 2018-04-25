package edu.rlv.cosc60.application;

import edu.rlv.cosc60.AVLMap;
import edu.rlv.cosc60.ArrayList;
import edu.rlv.cosc60.List;
import edu.rlv.cosc60.Map;
import edu.rlv.cosc60.TreeMap;
import static edu.rlv.cosc60.TreeMap.TreeOrder.*;

/**
 *
 * @author russel
 */
public class Sports {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Map<String,List<String>> sports = new TreeMap<>();
        
        sports.put("Mark", new ArrayList<>());
        sports.put("Anna", new ArrayList<>());
        sports.put("Paul", new ArrayList<>());
        sports.put("Ned", new ArrayList<>());
        
        sports.get("Mark").add("Basketball");
        sports.get("Mark").add("Baseball");
        sports.get("Mark").add("Footbal");
        sports.get("Anna").add("Swimming");
        sports.get("Anna").add("Volleyball");
        sports.get("Anna").add("Chess");
        sports.get("Paul").add("Tennis");
        sports.get("Paul").add("Skiing");
        sports.get("Paul").add("Bowling");
        
        for(String name:sports.getKeys()){
            System.out.println(name);
        }
        
        System.out.println(sports);
        
        sports.put("Anna", new ArrayList<>());
        
        System.out.println(sports);
        
        sports.remove("Ned");
        
        System.out.println(sports);

        
        System.out.println(((TreeMap)sports).toString(INORDER));        
        System.out.println(((TreeMap)sports).toString(PREORDER));        
        System.out.println(((TreeMap)sports).toString(POSTORDER));        
    }
    
}
