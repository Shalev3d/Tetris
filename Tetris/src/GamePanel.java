import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Graphics;

public class GamePanel extends JPanel  {
    private static final long serialVersionUID = 1L;
	private Game game;
    public NextShapePanel nextShapePanel;
    MainFrame parent;

    public GamePanel(MainFrame mainFrame, String player_name) {
    	this.parent = mainFrame;
        this.game = new Game(this, player_name);
        this.nextShapePanel = new NextShapePanel();
        setLayout(new BorderLayout());
        

        // Add the board panel to the right
        add(this.game.getBoard(), BorderLayout.WEST);
        
        // Add the next shape panel to the right
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel nextShapeLabel = new JLabel("Next Shape");
        rightPanel.add(nextShapeLabel, BorderLayout.NORTH);
        rightPanel.add(nextShapePanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        
        SwingUtilities.invokeLater(()-> {
        	this.game.getBoard().requestFocusInWindow();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Update the next shape display
        nextShapePanel.setNextShape(game.getNextTetromino());
        this.game.getBoard().repaint();
    }
}
