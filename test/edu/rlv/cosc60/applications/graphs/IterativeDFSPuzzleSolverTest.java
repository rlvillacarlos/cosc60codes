package edu.rlv.cosc60.applications.graphs;

import static edu.rlv.cosc60.matchers.BFSSolver.canSolve;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russel
 */
public class IterativeDFSPuzzleSolverTest {
    /**
     * Test of solve method, of class IterativeDFSPuzzleSolver.
     */
    
    @Test
    public void testCanSolvePuzzle() {
        System.out.println("Can Solve Puzzle");
        assertThat(new IterativeDFSPuzzleSolver(), canSolve());
    }
}
