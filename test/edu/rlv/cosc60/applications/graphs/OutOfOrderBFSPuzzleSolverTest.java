package edu.rlv.cosc60.applications.graphs;

import static edu.rlv.cosc60.matchers.BFSSolver.canFindMinimumSteps;
import static edu.rlv.cosc60.matchers.BFSSolver.canSolve;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russel
 */
public class OutOfOrderBFSPuzzleSolverTest {
    /**
     * Test of solve method, of class OutOfOrderBFSPuzzleSolver.
     */
    
    @Test
    public void testCanSolvePuzzle() {
        System.out.println("Can Solve Puzzle");
        assertThat(new OutOfOrderBFSPuzzleSolver(), canSolve());
    }
    
    @Test
    public void testUsesBFS() {
        System.out.println("Uses BFS");
        assertThat(new OutOfOrderBFSPuzzleSolver(), canFindMinimumSteps());
    }
}
