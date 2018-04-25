package edu.rlv.cosc60.application;

import edu.rlv.cosc60.ChainedHashMap;
import edu.rlv.cosc60.List;
import edu.rlv.cosc60.Map;
import edu.rlv.cosc60.SingleLinkedList;
import edu.rlv.cosc60.activities.SortedBag;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author russel
 */
public class WordSuggest {
    private static Map<String,Integer> wordCounter = new ChainedHashMap<>();
    private static List<Map<String,SortedBag<String>>> words;
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Loading words....");
        loadWords("resources/wordlist.txt");
        System.out.println("Done loading words...");
        
        while(true){
            System.out.print("Start word: ");        
            String w = in.nextLine().trim();
            if(w.length() == 0){
                break;
            }
            
            SortedBag<String> suggestions =  getSuggestions(w, 0);
            System.out.println("Suggestions:");
            int i = 0;
            System.out.print("   ");
            if(suggestions.isEmpty()){
                System.out.println("No suggestion");
            }else{
                for(String s:suggestions){
                    System.out.printf("%s ",s);
                    i=(i+1)%8;
                    if(i==0){
                        System.out.println("");
                        System.out.print("   ");
                    }
                }
                System.out.println("");
            }
        }
    }
    
    public static void loadWords(String filename) throws FileNotFoundException{
        try (Scanner fin = new Scanner(new File(filename))) {
            words = new SingleLinkedList<>();
            while(fin.hasNextLine()){
                String w = fin.nextLine();
                w = w.toLowerCase();
                String prev = null;
                
                for(int i=0;i<w.length();i++){
                    String subs = w.substring(0, i+1);
                    
                    if(i == words.size()){
                        words.add(i, new ChainedHashMap<>());
                    }
                    
                    Map<String,SortedBag<String>> curMap = words.get(i);
                    
                    
                    if(prev == null){
                        if(!curMap.contains(subs)){
                            curMap.put(subs, new SortedBag<>());
                        }
                    }else{
                        Map<String,SortedBag<String>> prevMap = words.get(i-1);
                        String s = subs.substring(subs.length()-1,subs.length());
                        
                        if(!prevMap.get(prev).contains(s)){
                            prevMap.get(prev).add(s);
                        }
                        
                        if(!curMap.contains(subs)){
                            curMap.put(subs, new SortedBag<>());
                        }
                    }
                    prev = subs;
                }
                words.get(w.length()-1).get(w).add("$");
            }
        }
    }
    
    public static SortedBag<String> getSuggestions(String start, int limit){
        SortedBag<String> suggestions = new SortedBag<>();
        getSuggestions(start, suggestions, limit);
        return suggestions;
    }
    
    private static void getSuggestions(String start,SortedBag<String> suggestions,int limit){
        int len = start.length()-1;
        if(len < words.size() && (suggestions.size() < limit || limit == 0 )){
            Map<String,SortedBag<String>> w = words.get(len);
            SortedBag<String> nextW = w.get(start);
            
            if(nextW!=null){
                for(String s:nextW){
                    if(s.equals("$")){
                        suggestions.add(start);
                    }else{
                        getSuggestions(start+s, suggestions, limit);
                    }
                }

            }
        }
    }
    
}
