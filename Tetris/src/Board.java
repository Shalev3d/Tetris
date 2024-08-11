import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;



public class Board extends JPanel implements KeyListener {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private static final long serialVersionUID = 1L;
	public Color[][] grid;
	private Game game;
    public Point origin_position = new Point(Consts.COLS/2-1, 1); 

    public Board(Game game) {
        this.grid = new Color[Consts.COLS][Consts.ROWS];
        this.game = game;
        setPreferredSize(new Dimension(Consts.COLS * Consts.BLOCK_SIZE, Consts.ROWS * Consts.BLOCK_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        initializeGrid();
        addKeyListener(this);

    }

    private void initializeGrid() {
    	for (int col = 0; col < Consts.COLS; col++) {
			for (int row = 0; row < Consts.ROWS-1; row++) {
				if (row == Consts.ROWS-2 || col == Consts.COLS-1 ||col ==0) {
					this.grid[col][row] = Color.GRAY;
				} else {
					this.grid[col][row] = Color.BLACK;
				}
			}
		}
    	System.out.println("initializeGrid()");
    }


    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.fillRect(0, 0, Consts.COLS*Consts.BLOCK_SIZE, (Consts.ROWS-1)*Consts.BLOCK_SIZE);
        drawGrid(g);
        System.out.println("painting board");
    }

    private void drawGrid(Graphics g) {
        for (int col = 0; col < Consts.COLS; col++) {
            for (int row = 0; row < Consts.ROWS-1; row++) {
                g.setColor(grid[col][row]);
                g.fillRect(col * Consts.BLOCK_SIZE, row * Consts.BLOCK_SIZE, Consts.BLOCK_SIZE-1, Consts.BLOCK_SIZE-1);
            }
        }

        g.setColor(java.awt.Color.WHITE);
        g.drawString("Score: " + game.getScore().getScore(), 10, 20);

        this.game.getCurrentTetromino().draw(g);
    }


    public void setBlock(int row, int col, Color color) {
        if (row >= 0 && row < Consts.ROWS && col >= 0 && col < Consts.COLS) {
            grid[row][col] = color;
            repaint();
        }
    }

    public Color getBlock(int row, int col) {
        if (row >= 0 && row < Consts.ROWS && col >= 0 && col < Consts.COLS) {
            return grid[row][col];
        }
        return Color.BLACK;
    }

	public void deleteRow(int row) {
		for (int i = row-1; i>0; i--) {
			for (int j = 1;j<Consts.COLS-1;j++) {
				grid[j][i+1] = grid[j][i];
			}
		}
		
	}

	public void clearRows() {
		boolean gap;
		int numClears = 0;
		
		for (int row = Consts.ROWS-3; row > 0; row--) {
			gap = false;
			for (int col = 1; col < Consts.COLS-1; col++) {
				if (grid[col][row] == Color.BLACK) {
					gap = true;
					break;
				}
			}
			if (!gap) {
				deleteRow(row);
				row += 1;
				numClears += 1;
			}
		}
		
		switch (numClears) {
		case 1:
			game.addScore(100);
			break;
		case 2:
			game.addScore(300);
			break;
		case 3:
			game.addScore(500);
			break;
		case 4:
			game.addScore(800);
			break;
		}
	}

	public void newPiece() {
		this.game.placeNewTetromino();
		
	}
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            game.getCurrentTetromino().move(Consts.Direction.LEFT, game.getBoard());
            System.out.println("PRESSED LEFT");
            break;
        case KeyEvent.VK_RIGHT:
            game.getCurrentTetromino().move(Consts.Direction.RIGHT, game.getBoard());
            System.out.println("PRESSED RIGHT");
            break;
        case KeyEvent.VK_DOWN:
            game.getCurrentTetromino().move(Consts.Direction.DOWN, game.getBoard());
            System.out.println("PRESSED DOWN");
            break;
        case KeyEvent.VK_UP:
            game.getCurrentTetromino().rotate(game.getBoard());
            System.out.println("PRESSED UP");
            break;
        case KeyEvent.VK_SPACE:
            game.getCurrentTetromino().drop(game.getBoard()); // Drop instantly to the bottom
            break;
    }
    repaint(); // Repaint the panel to reflect the movement
}

    @Override
    public void keyReleased(KeyEvent e) {}

}
