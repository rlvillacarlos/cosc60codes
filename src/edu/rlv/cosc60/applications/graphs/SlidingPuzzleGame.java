package edu.rlv.cosc60.applications.graphs;

import edu.rlv.cosc60.applications.graphs.SlidingPuzzle.Move;
import edu.rlv.cosc60.applications.graphs.SlidingPuzzle.SlidingPuzzleConfiguration;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author russel
 */
public class SlidingPuzzleGame {
    private static final int PUZZLE_SIZE = 3;
    private static final SlidingPuzzle puzzle = new SlidingPuzzle(PUZZLE_SIZE);
    private static final JFrame game = new JFrame("Sliding Block Puzzle");
    private static final JPanel board = new JPanel(new GridLayout(puzzle.getSize(), puzzle.getSize()));
    private static final JPanel control = new JPanel();
    private static final JLabel tiles[] = new JLabel[puzzle.getSize() * puzzle.getSize()];
    
    private static final String[] solverNames = {"BFS Solver (BFS)",
                                                 "Iterative DFS Solver (IDFS)",
                                                 "Manhattan BFS Solver",
                                                 "Out of Order BFS Solver"
                                                };
    private static final JMenuBar mnubarMain = new JMenuBar();
    private static final JMenu mnuGame = new JMenu("Game");
    private static final JMenuItem mnuitmNewGame = new JMenuItem("New Game");
    private static final JMenu mnuSolver = new JMenu("Solver");
    private static final JMenuItem mnuitmSolvers[] = new JMenuItem[solverNames.length];
    
    private static boolean bEnabled = true;
    private static Thread solverThread;
    private static ExecutorService solverExecutor = Executors.newFixedThreadPool(1);
    private static JLabel lblControlMsg = new JLabel();
    
    public static void main(String[] args) {
        layout();
        setEventHandlers();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(game, "Initializing game. Please wait.");
                puzzle.randomize();
                game.setVisible(true);
                int length = puzzle.getSize()*96;
                game.setSize(length, length);
                game.setResizable(false);
            }
        });
        
    }
    
    private static void layout(){
        control.setLayout(new FlowLayout(FlowLayout.CENTER));
        control.add(lblControlMsg);
        
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLayout(new BorderLayout());
        game.add(board, BorderLayout.CENTER);
        game.add(control, BorderLayout.NORTH);
        game.setFocusable(true);
        
        control.setVisible(false);
        
        int max = puzzle.getSize()*puzzle.getSize();
        
        for(int i=0;i<max;i++){
            tiles[i] = new JLabel();
            tiles[i].setHorizontalAlignment(JLabel.CENTER);
            tiles[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
            tiles[i].setOpaque(true);
            tiles[i].setBackground(Color.white);
            tiles[i].setBorder(new LineBorder(Color.black));
            board.add(tiles[i]);
        }
        showPuzzleState(puzzle.getConfiguration());
        
        for(int i = 0; i < mnuitmSolvers.length;i++){
            mnuitmSolvers[i] = new JMenuItem(solverNames[i]);
            mnuitmSolvers[i].addActionListener(new SolverAction(i));
            mnuSolver.add(mnuitmSolvers[i]);
        }
        mnuGame.add(mnuitmNewGame);
        
        mnubarMain.add(mnuGame);
        mnubarMain.add(mnuSolver);
        
        game.setJMenuBar(mnubarMain);
    }
    
    private static void showPuzzleState(SlidingPuzzleConfiguration state){
        int i = 0;
        for(String s: state.getPuzzle().split(",")){
            if(s.equals("B")){
                tiles[i].setText("");
                tiles[i].setOpaque(false);
            }else{
                tiles[i].setText(s);
                tiles[i].setOpaque(true);
            }
            i++;
        }
    }
    
    private static void setEventHandlers(){
        game.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(bEnabled){
                    switch(e.getKeyCode()){
                        case KeyEvent.VK_UP:showPuzzleState(puzzle.move(SlidingPuzzle.Move.UP));break;
                        case KeyEvent.VK_RIGHT:showPuzzleState(puzzle.move(SlidingPuzzle.Move.RIGHT));break;
                        case KeyEvent.VK_DOWN:showPuzzleState(puzzle.move(SlidingPuzzle.Move.DOWN));break;
                        case KeyEvent.VK_LEFT:showPuzzleState(puzzle.move(SlidingPuzzle.Move.LEFT));break;
                    }
                }
            }
        });
        
        mnuitmNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleEnable(false);
                showPuzzleState(puzzle.randomize());
                toggleEnable(true);
            }
        });
    }
    
    private static void toggleEnable(boolean b){
        bEnabled = b;
        mnuGame.setEnabled(bEnabled);
        mnuSolver.setEnabled(bEnabled);
        game.setCursor(new Cursor(bEnabled?Cursor.DEFAULT_CURSOR:Cursor.WAIT_CURSOR));
    }
    
    private static class SolverAction implements ActionListener{
        
        final SlidingPuzzleSolver solver;
        
        public SolverAction(int solver) {
            switch(solver){
                case 1: this.solver = new IterativeDFSPuzzleSolver();break;
                case 2: this.solver = new ManhattanBFSPuzzleSolver();break;
                case 3: this.solver = new OutOfOrderBFSPuzzleSolver();break;
                default: this.solver = new BFSPuzzleSolver();break;
            }
        }
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            solverThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    lblControlMsg.setText("Solving Puzzle...");
                    control.setVisible(true);
                    toggleEnable(false);
                    List<Move> solution = solver.solve(puzzle);
                    try {
                        for (Move m : solution) {
                            showPuzzleState(puzzle.move(m));
                            Thread.sleep(200);
                        }
                    } catch (InterruptedException ex) {/*Ignore*/ 
                        System.out.println("Interrupted");
                    }
                    toggleEnable(true);
                    control.setVisible(false);
                }
            });
            solverExecutor.submit(solverThread);
            
        }
    }
}
