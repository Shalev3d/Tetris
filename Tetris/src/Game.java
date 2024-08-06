import javax.swing.JFrame;

public class Game extends JFrame {
    private Board board;
    private Score score;
    private String playerName;
    private DBHandler dbHandler;

    public Game(String playerName) {
        this.playerName = playerName;
        this.score = new Score();
        this.dbHandler = new DBHandler();
        this.board = new Board(this);
        
        setTitle("Tetris Game");
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Board getBoard() {
        return board;
    }

    public Score getScore() {
        return score;
    }

    public void moveLeft() {
        // Implement logic to move the current Tetromino left
    }

    public void moveRight() {
        // Implement logic to move the current Tetromino right
    }

    public void moveDown() {
        // Implement logic to move the current Tetromino down
    }

    public void rotate() {
        // Implement logic to rotate the current Tetromino
    }

    public void drop() {
        // Implement logic to drop the current Tetromino instantly
    }

    public void updateScore(int points) {
        score.addPoints(points);
        dbHandler.saveScore(playerName, score.getScore());
    }

    public static void main(String[] args) {
        new Game("Player1");
    }
}
