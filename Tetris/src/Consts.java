import java.awt.Color;
import java.time.format.DateTimeFormatter;

public class Consts {
	
    public static final String FILE_PATH = "high_scores.csv";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yy HH:mm");

	// Define constants for colors
    public static final Color TETROMINO_COLOR_I = Color.CYAN;
    public static final Color TETROMINO_COLOR_J = Color.BLUE;
    public static final Color TETROMINO_COLOR_L = Color.ORANGE;
    public static final Color TETROMINO_COLOR_O = Color.YELLOW;
    public static final Color TETROMINO_COLOR_S = Color.GREEN;
    public static final Color TETROMINO_COLOR_T = Color.MAGENTA;
    public static final Color TETROMINO_COLOR_Z = Color.RED;

    // Define Direction enum
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    
    public static final int ROWS = 24;
    public static final int COLS = 12;
    public static final int BLOCK_SIZE = 24;
    
    public static final int EASY_TIMER = 800;
    public static final int MEDIUM_TIMER = 400;
    public static final int HARD_TIMER = 200;
    public static final int EXTREME_TIMER = 100;

    public static final int ORIG_X = (COLS-1)/2;
    public static final int ORIG_Y = 0;
}
