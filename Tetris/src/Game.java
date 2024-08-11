import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.SwingUtilities;

public class Game {
    private Board board;
    private Queue<Tetromino> tetrominoQueue;
    private Tetromino currentTetromino; // The Tetromino currently being controlled
    private Score score;
    private String playerName;
    private DBHandler dbHandler;
    private GamePanel gamePanel;
    private GameTimer timer;
    private Thread timerThread;

    public Game(GamePanel gamePanel, String playerName) {
    	this.gamePanel = gamePanel;
        this.playerName = playerName;
        this.score = new Score();
        this.dbHandler = new DBHandler();
        this.board = new Board(this);
        this.tetrominoQueue = new LinkedList<>();
        initializeQueue();
        currentTetromino = tetrominoQueue.poll(); // Get the first Tetromino from the queue
        currentTetromino.originPosition.setLocation(Consts.ORIG_X, Consts.ORIG_Y);
        this.timer= new GameTimer(this, Consts.EASY_TIMER);
        timerThread = new Thread(timer);
        timerThread.start();
        System.out.println("GAME():: started");
    }
    
	private void initializeQueue() {
	    // Randomize and add the first two Tetrominos
	    tetrominoQueue.add(randomizeTetromino());
	    tetrominoQueue.add(randomizeTetromino());
	}
	
    private Tetromino randomizeTetromino() {
        Random random = new Random();
        int tetrominoType = random.nextInt(7); // Assume there are 7 types of Tetrominos
        System.out.println("RANDOMIZING TETROMINO");
        switch (tetrominoType) {
            case 0: return new IShape();
            case 1: return new JShape();
            case 2: return new LShape();
            case 3: return new OShape();
            case 4: return new SShape();
            case 5: return new TShape();
            case 6: return new ZShape();
            default: return new IShape(); // Fallback to IShape
        }
        
    }
    
    public void placeNewTetromino() {
        // Logic to place the Tetromino on the board
        
        // Update the queue
        currentTetromino = tetrominoQueue.poll(); // Get the next Tetromino
        currentTetromino.originPosition.setLocation(Consts.ORIG_X, Consts.ORIG_Y);
        tetrominoQueue.add(randomizeTetromino()); // Add a new random Tetromino to the queue
        this.board.repaint();
    }
    
    // Method to return the current active Tetromino
    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }    
    
    // Method to return the next active Tetromino
    public Tetromino getNextTetromino() {
        return tetrominoQueue.peek();
    }
    
	public Board getBoard() {
        return board;
    }

    public Score getScore() {
        return score;
    }

    public void addScore(int points) {
        score.addPoints(points);
        checkTime();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
    
    public void gameOver() {
        SwingUtilities.invokeLater(() -> {
            GameOverDialog dialog = new GameOverDialog(
                gamePanel.parent, // Pass the parent JFrame
                score.getScore(),           // Pass the current score
                () -> gamePanel.parent.showScoresPanel() // Define action to navigate to ScoresPanel
            );
            this.timer.stop();
            dialog.setVisible(true);
        });
    }
    
    private void checkTime() {
        // Determine the level based on the score
        int level = getLevelForScore(this.score.getScore());

        switch (level) {
            case 0:
                timer.setDelay(Consts.EASY_TIMER);
                break;
            case 1:
                timer.setDelay(Consts.MEDIUM_TIMER);
                break;
            case 2:
                timer.setDelay(Consts.HARD_TIMER);
                break;
            case 3:
                timer.setDelay(Consts.EXTREME_TIMER);
                break;
            default:
                // Keep the hardest timer if score is above the highest threshold
                timer.setDelay(Consts.EXTREME_TIMER);
                break;
        }
    }
        
    private int getLevelForScore(int score) {
        // Define the thresholds
        int[] thresholds = {500, 1500, 4500};
        // Check the level based on the score and thresholds
        for (int i = 0; i < thresholds.length; i++) {
            if (score < thresholds[i]) {
                return i; // Return the level for the current score
            }
        }
        return thresholds.length; // Return the highest level if score exceeds all thresholds
    }
}
