package edu.rlv.cosc60.applications.graphs;

import edu.rlv.cosc60.applications.graphs.SlidingPuzzle.Move;
import edu.rlv.cosc60.applications.graphs.SlidingPuzzle.SlidingPuzzleConfiguration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author russel
 */
public class BFSPuzzleSolver implements SlidingPuzzleSolver{

    @Override
    public List<Move> solve(SlidingPuzzle puzzle) {
        //Create a list for storing the moves
        List<Move> solution = new LinkedList<>();
        
        if(puzzle != null){
            //The parent map for tracing the path to the solution later
            Map<SlidingPuzzleConfiguration, SlidingPuzzleConfiguration> parent = new HashMap<>();
            //The queue used for BFS
            Queue<SlidingPuzzleConfiguration> q = new LinkedList<>();
            
            //Get the starting configuration of the puzzle
            SlidingPuzzleConfiguration startConfig = puzzle.getConfiguration();
            //Get the target (solved) configuration
            SlidingPuzzleConfiguration solvedConfig = puzzle.getSolvedConfiguration();
            //Add the current state to the queue
            q.add(startConfig);
            //Set the parent of the starting configuration to null
            parent.put(startConfig, null);
            
            //Proceed if the starting configuration is not the target
            if (!startConfig.equals(solvedConfig)) {
                while (!q.isEmpty()) {
                    //Get a configuration to explore
                    SlidingPuzzleConfiguration cur = q.remove();
                    
                    //*Iterate through all the neighbors of the current configuration
                    for (SlidingPuzzleConfiguration s : puzzle.nextConfigurations(cur)) {
                        //The neighbor has no parent if it is discovered for the first time
                        if (!parent.containsKey(s)) {
                            //Set the parent of the newly discovered vertex
                            parent.put(s, cur);
                            //Check if the newly discovered neighbor is the target
                            if (s.equals(solvedConfig)) {
                                //If the target is found, set it to be the solve configuration
                                solvedConfig = s;
                                //Clear the queue to stop bfs
                                q.clear();
                                //Stop iterating the neighbors
                                break;
                            } else {
                                //If the neighbor is not the target, add it in the queue
                                q.add(s);
                            }
                        }
                    }
                }
            }
            
            //Set the current configuration to the solved state
            SlidingPuzzleConfiguration cur = solvedConfig;

            //Trace back the step that lead to the solved configuration
            while (cur != null) {
                //Check if the current configuration is the start configuration
                if(!cur.equals(startConfig)){
                    //If it is not the start configuration, add the move that leads to this configuration
                    solution.add(0, cur.getMove());
                }
                //Update the current configuration to be equal to the parent of the current configuration
                cur = parent.get(cur);
            }
            
        
        }
        //Return the list of moves
        return solution;
    }
    
}
