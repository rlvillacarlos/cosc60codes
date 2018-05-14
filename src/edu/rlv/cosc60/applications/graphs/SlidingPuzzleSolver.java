package edu.rlv.cosc60.applications.graphs;

import edu.rlv.cosc60.applications.graphs.SlidingPuzzle.Move;
import java.util.List;

/**
 *
 * @author russel
 */
public interface SlidingPuzzleSolver {
    public List<Move> solve(SlidingPuzzle puzzle);
}
