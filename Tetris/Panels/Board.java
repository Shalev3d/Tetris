import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;



public class Board extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
    private Logger logger;
    
	public Color[][] grid;
	public Game game;
    public Point origin_position = new Point(Consts.COLS/2-1, 1); 
    
    public Board(Game game) {
        this.logger = new Logger(Consts.LOG_LEVEL);
        
        this.logger.info("Initiate Board");
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
    	logger.info("initializeGrid:: grid initiated in board");
    }


    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.fillRect(0, 0, Consts.COLS*Consts.BLOCK_SIZE, (Consts.ROWS-1)*Consts.BLOCK_SIZE);
        drawGrid(g);
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
    }
    repaint(); // Repaint the panel to reflect the movement
}

    @Override
    public void keyReleased(KeyEvent e) {}

}
