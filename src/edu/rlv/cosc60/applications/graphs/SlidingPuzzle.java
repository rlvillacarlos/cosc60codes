package edu.rlv.cosc60.applications.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author russel
 */
public class SlidingPuzzle {   
    public enum Move{
        UP("Up"),
        RIGHT("Right"),
        DOWN("Down"),
        LEFT("Left");

        private final String move;
        
        private Move(String move) {
            this.move = move;
        }
        
        public Move inverse(){
            switch (this) {
                case UP:
                    return DOWN;
                case DOWN:
                    return UP;
                case LEFT:
                    return RIGHT;
                default:
                    return LEFT;
            }
        }
        
        @Override
        public String toString() {
            return move;
        }
    }
    
    public class SlidingPuzzleConfiguration{
        private final SlidingPuzzle owner;
        private final String configuration;
        private final String puzzle;
        private final int blankPos;
        private final Move move;
        
        private SlidingPuzzleConfiguration(SlidingPuzzle owner, String configuration, Move move){
            if(owner==null){
                throw new IllegalArgumentException();
            }
            this.owner = owner;
            this.configuration = configuration;
            if(!configuration.isEmpty()){
                String parts[] = configuration.split(";");
                this.puzzle = parts[0];
                this.blankPos = Integer.parseInt(parts[1]);
                this.move = move;
            }else{
                this.puzzle = "";
                this.blankPos = -1;
                this.move = null;
            }
        }
        
        public String getPuzzle(){
            return puzzle;
        }
        
        public String[][] getPuzzleArray(){
            String[][] puzzleArray = new String[size][size];
            String tmp[] = puzzle.split(",");
            
            for(int i = 0; i < tmp.length;i++){
                puzzleArray[i/size][i%size] = tmp[i].trim();
            }
            
            return puzzleArray;
        }

        public int getBlankPosition() {
            return blankPos;
        }

        public Move getMove() {
            return move;
        }
        
        private boolean isValid(){
            return !puzzle.isEmpty();
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 37 * hash + Objects.hashCode(this.configuration);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final SlidingPuzzleConfiguration other = (SlidingPuzzleConfiguration) obj;
            if (!Objects.equals(this.configuration, other.configuration)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return configuration;
        }
        
        
    }
    
    
    private final int size;
    private final SlidingPuzzleConfiguration startState;
    private final int width;

    private SlidingPuzzleConfiguration curState;
    private List<SlidingPuzzleConfiguration> configs = null;

    public SlidingPuzzle(SlidingPuzzle puzzle) {
        this(puzzle.size);
        this.curState = new SlidingPuzzleConfiguration(this,puzzle.curState.toString(),null);
    }
    
    public SlidingPuzzle(int size) {
        this.size = size;
        
        StringBuilder tmp = new StringBuilder();
        int max = size*size;
        width = Integer.toString(max).length();
        for(int i = 1;i<max;i++){
            tmp.append(String.format("%"+width+"d", i)).append(",");
        }
        String start = tmp.append("B").append(";").append(max-1).toString();
     
        this.startState = new SlidingPuzzleConfiguration(this,start,null);
        this.curState = new SlidingPuzzleConfiguration(this,start,null);
    }
    
        
    public List<SlidingPuzzleConfiguration> nextConfigurations(SlidingPuzzleConfiguration cur){
        List<SlidingPuzzleConfiguration> next = new ArrayList<>();
        Move[] moves = {Move.UP,Move.RIGHT,Move.DOWN,Move.LEFT};
        
        for(int i = 0;i<4;i++){
            SlidingPuzzleConfiguration n = nextConfiguration(cur,moves[i]);
            if(n.isValid()){
                next.add(n);
            }
        }
        
        return next;
    }
    
    public SlidingPuzzleConfiguration nextConfiguration(SlidingPuzzleConfiguration cur, Move move){
        String n = "";
        String curConfig = cur.getPuzzle();
        
        int bPos = cur.getBlankPosition();
        
        int r = row(bPos);
        int c = col(bPos);
        
        String[] next = curConfig.split(",");

        int sPos = -1;

        if(move == Move.UP && r > 0){
            sPos = strPos(r-1, c);    
        }else if(move == Move.RIGHT && c < size - 1){
            sPos = strPos(r, c+1);    
        }else if(move == Move.DOWN && r < size - 1){
            sPos = strPos(r+1, c);    
        }else if(move == Move.LEFT && c > 0){
            sPos = strPos(r, c-1);    
        }

        if(sPos > -1){
            String s = next[sPos];
            next[sPos] = "B";
            next[bPos] = s;
            n = String.join(",", next).concat(";").concat(Integer.toString(sPos));
        }
        return new SlidingPuzzleConfiguration(this,n,move);
    }
    
    private int row(int strPos){
        return strPos / size;
    }
    
    private int col(int strPos){
        return strPos % size;
    }
    
    private int strPos(int row, int col){
        return row * size + col;
    }
    
    public SlidingPuzzleConfiguration move(Move m){
        SlidingPuzzleConfiguration n = nextConfiguration(curState,m);
        
        if(n.isValid()){
            curState = n;
        }
        
        return curState;
    }

    public SlidingPuzzleConfiguration getSolvedConfiguration(){
        return startState;
    }
    
    public SlidingPuzzleConfiguration getConfiguration() {
        return curState;
    }
  
    public int getSize() {
        return size;
    }
    
    public SlidingPuzzleConfiguration randomize(){
        if(configs == null){
            configs = generateAllConfigurations(1_000_000);
        }
        Random rnd = new Random(System.currentTimeMillis());
        curState = configs.get(rnd.nextInt(configs.size()));
        return new SlidingPuzzleConfiguration(this,curState.configuration, null);
    }
    
    private List<SlidingPuzzleConfiguration> generateAllConfigurations(int limit){
        Queue<SlidingPuzzleConfiguration> q = new LinkedList<>();
        q.add(curState);
        Set<SlidingPuzzleConfiguration> visited = new HashSet<>();
        visited.add(curState);
        
        while(!q.isEmpty() && visited.size() <= limit){
            SlidingPuzzleConfiguration cur = q.remove();
            int i = 0;
            for(SlidingPuzzleConfiguration c:nextConfigurations(cur)){
                if(c.isValid()&& !visited.contains(c)) {
                    visited.add(new SlidingPuzzleConfiguration(this,c.configuration, null));
                    if (c.equals(startState)) {
                        q.clear();
                        break;
                    } else {
                        q.add(c);
                    }
                }
                i++;
            }
        }
        return new ArrayList(visited);
    }
}
