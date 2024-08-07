import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Board extends JPanel implements KeyListener {
    static final int ROWS = 22;
    static final int COLS = 12;
    static final int BLOCK_SIZE = 24;
    private Color[][] grid;
    private Game game;
    private Point origin_position; ///???

    public Board(Game game) {
        this.game = game;
        this.grid = new Color[ROWS][COLS];
        setPreferredSize(new Dimension(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        initializeGrid();
        
    }

    private void initializeGrid() {
    	for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (i == 0 || i == ROWS-1 || j == COLS-1 ||j ==0) {
					this.grid[i][j] = Color.GRAY;
				} else {
					this.grid[i][j] = Color.BLACK;
				}
			}
		}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                g.setColor(grid[row][col]);
                g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                game.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                game.moveRight();
                break;
            case KeyEvent.VK_DOWN:
                game.moveDown();
                break;
            case KeyEvent.VK_UP:
                game.rotate();
                break;
            case KeyEvent.VK_SPACE:
                game.drop();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void setBlock(int row, int col, Color color) {
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            grid[row][col] = color;
            repaint();
        }
    }

    public Color getBlock(int row, int col) {
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            return grid[row][col];
        }
        return Color.BLACK;
    }
}
