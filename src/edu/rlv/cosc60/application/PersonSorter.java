package edu.rlv.cosc60.application;

import edu.rlv.cosc60.util.ArrayUtil;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author russel
 */
public class PersonSorter {
    
    private static class FirstNameComparator implements Comparator<Person>{
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    
    }
    
    private static class LastNameComparator implements Comparator<Person>{
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
    
    }
 
    private static class IDComparator implements Comparator<Person>{
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getId() < o2.getId()? -1 : 
                    (o1.getId() == o2.getId()? 0:1);
        }
    
    }

    public static void main(String[] args) {
        Person p[] = {new Person(4, "Mark", "Sanchez"),
                      new Person(2, "Ana", "Smidt"),
                      new Person(3, "Riza", "Santos"),
                      new Person(1, "John", "Kane")};
        
        System.out.println("Original array: ");
        ArrayUtil.print(p);
        
        System.out.println("Sort by id: ");
        Arrays.sort(p,new IDComparator());
        ArrayUtil.print(p);
        
        System.out.println("Sort by first name: ");
        Arrays.sort(p,new FirstNameComparator());        
        ArrayUtil.print(p);
        
        System.out.println("Sort by last name: ");
        Arrays.sort(p,new LastNameComparator());
        ArrayUtil.print(p);
        
        
    }
    
   
}
