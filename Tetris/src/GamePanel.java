import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    private Game game;

    public GamePanel(MainFrame mainFrame, String player_name) {
        this.game = new Game(player_name);
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    private void drawGame(Graphics g) {
        Board board = game.getBoard();
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                g.setColor(board.getBlock(row, col));
                g.fillRect(col * Board.BLOCK_SIZE, row * Board.BLOCK_SIZE, Board.BLOCK_SIZE, Board.BLOCK_SIZE);
                g.setColor(java.awt.Color.GRAY);
                g.drawRect(col * Board.BLOCK_SIZE, row * Board.BLOCK_SIZE, Board.BLOCK_SIZE, Board.BLOCK_SIZE);
            }
        }
        g.setColor(java.awt.Color.WHITE);
        g.drawString("Score: " + game.getScore().getScore(), 10, 20);
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

    public Game getGame() {
        return game;
    }
}
