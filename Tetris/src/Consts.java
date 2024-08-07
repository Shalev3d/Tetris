import java.awt.Color;

public class Consts {
	
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
    
    public static final int ROWS = 22;
    public static final int COLS = 12;

}
