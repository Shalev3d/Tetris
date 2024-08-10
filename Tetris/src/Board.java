import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Board extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
	public Color[][] grid;
	private Game game;
    public Point origin_position = new Point(Consts.COLS/2-1, 1); 

    public Board(Game game) {
        this.grid = new Color[Consts.ROWS][Consts.COLS];
        this.game = game;
        setPreferredSize(new Dimension(Consts.COLS * Consts.BLOCK_SIZE, Consts.ROWS * Consts.BLOCK_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        initializeGrid();
        addKeyListener(this);
    }

    private void initializeGrid() {
    	for (int row = 0; row < Consts.ROWS; row++) {
			for (int col = 0; col < Consts.COLS; col++) {
				if (row == 0 || row == Consts.ROWS-1 || col == Consts.COLS-1 ||col ==0) {
					this.grid[row][col] = Color.GRAY;
				} else {
					this.grid[row][col] = Color.BLACK;
				}
			}
		}
    	System.out.println("initializeGrid()");
    }


    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.fillRect(0, 0, Consts.ROWS*Consts.BLOCK_SIZE, Consts.COLS*Consts.BLOCK_SIZE);
        drawGrid(g);
        System.out.println("painting board");
    }

    private void drawGrid(Graphics g) {
        for (int row = 0; row < Consts.ROWS; row++) {
            for (int col = 0; col < Consts.COLS; col++) {
                g.setColor(grid[row][col]);
                g.fillRect(col * Consts.BLOCK_SIZE, row * Consts.BLOCK_SIZE, Consts.BLOCK_SIZE-1, Consts.BLOCK_SIZE-1);
            }
        }

        g.setColor(java.awt.Color.WHITE);
        g.drawString("Score: " + game.getScore().getScore(), 10, 20);

        this.game.getCurrentTetromino().draw(g);

        System.out.println("BOARD::drawGrid");
    }


    public void setBlock(int row, int col, Color color) {
        if (row >= 0 && row < Consts.ROWS && col >= 0 && col < Consts.COLS) {
            grid[row][col] = color;
            repaint();
        }

        System.out.println("setBlock");
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
				grid[i][j+1] = grid[i][j];
			}
		}
		
	}

	public void clearRows() {
		boolean gap;
		int numClears = 0;
		
		for (int row = Consts.ROWS-1; row > 0; row--) {
			gap = false;
			for (int col = 1; col < Consts.COLS-1; col++) {
				if (grid[row][col] == Color.BLACK) {
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
            break;
        case KeyEvent.VK_RIGHT:
            game.getCurrentTetromino().move(Consts.Direction.RIGHT, game.getBoard());
            break;
        case KeyEvent.VK_DOWN:
            game.getCurrentTetromino().move(Consts.Direction.DOWN, game.getBoard());
            break;
        case KeyEvent.VK_UP:
            game.getCurrentTetromino().rotate(game.getBoard());
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
