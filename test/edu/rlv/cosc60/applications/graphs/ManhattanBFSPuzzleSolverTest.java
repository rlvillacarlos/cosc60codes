package edu.rlv.cosc60.applications.graphs;

import static edu.rlv.cosc60.matchers.BFSSolver.canFindMinimumSteps;
import static edu.rlv.cosc60.matchers.BFSSolver.canSolve;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russel
 */
public class ManhattanBFSPuzzleSolverTest {
    /**
     * Test of solve method, of class ManhattanBFSPuzzleSolver.
     */
    
    @Test
    public void testCanSolvePuzzle() {
        System.out.println("Can Solve Puzzle");
        assertThat(new ManhattanBFSPuzzleSolver(), canSolve());
    }
    
    @Test
    public void testUsesBFS() {
        System.out.println("Uses BFS");
        assertThat(new ManhattanBFSPuzzleSolver(), canFindMinimumSteps());
    }
}
