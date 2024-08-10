import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

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
        this.timer = new GameTimer(this, Consts.HARD_TIMER);
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
            case 0: return new IShape(board.origin_position);
            case 1: return new JShape(board.origin_position);
            case 2: return new LShape(board.origin_position);
            case 3: return new OShape(board.origin_position);
            case 4: return new SShape(board.origin_position);
            case 5: return new TShape(board.origin_position);
            case 6: return new ZShape(board.origin_position);
            default: return new IShape(board.origin_position); // Fallback to IShape
        }
        
    }
    
    public void placeNewTetromino() {
        // Logic to place the Tetromino on the board
        
        // Update the queue
        currentTetromino = tetrominoQueue.poll(); // Get the next Tetromino
        tetrominoQueue.add(randomizeTetromino()); // Add a new random Tetromino to the queue
        this.board.repaint();
        System.out.println("placeNewTetromino::GAME");
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
        dbHandler.saveScore(playerName, score.getScore());
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
