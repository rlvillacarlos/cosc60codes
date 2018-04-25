package edu.rlv.cosc60.application;

import edu.rlv.cosc60.AVLMap;
import edu.rlv.cosc60.ChainedHashMap;
import edu.rlv.cosc60.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author russel
 */
public class WordCount {
    private static Map<String,Integer> wordCounter = new AVLMap<>();
    
    private static void showWords(){
        System.out.println("-Start Words-");
        int i = 0;
        for(String s:wordCounter.getKeys()){
            System.out.printf("%s ",s);
            i = (i + 1) % 10;
            
            if(i == 0){
                System.out.println("");
            }
        }
        System.out.println("-End Words-");
    }
    private static void hasWord(String s){
        System.out.printf("%b%n",wordCounter.contains(s.toLowerCase()));
    }
    
    private static void showFrequency(String s){
        Integer count = wordCounter.get(s.toLowerCase());
        
        System.out.printf("%s: %d%n",s,count!=null? count: 0);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        try (Scanner fin = new Scanner(new File("resources/great expectations.txt"))) {
            fin.useDelimiter("\\s|[0-9]");
            
            while(fin.hasNext()){
                String w = fin.next();
                w = w.replaceAll("\\W", "").toLowerCase();
                if(w.length()>0){
                    int count = 0;
                    if(wordCounter.contains(w)){
                        count = wordCounter.get(w);
                    }
                    
                    wordCounter.put(w, count + 1);
                }
            }
        }
        
        int opt = -1;
        
        do{
            System.out.println("Choices: ");
            System.out.println("   [0] Exit");
            System.out.println("   [1] Show Words");
            System.out.println("   [2] Check Word");
            System.out.println("   [3] Get Frequency");
            System.out.print(">>");
            
            opt = in.nextInt();
            in.nextLine();
            
            switch (opt) {
                case 1:
                    showWords();
                    break;
                case 2:
                    System.out.print("Word: ");
                    hasWord(in.nextLine());
                    break;
                case 3:
                    System.out.print("Word: ");
                    showFrequency(in.nextLine());
                    break;
            }
        }while(opt != 0);
        
    }
    
}
