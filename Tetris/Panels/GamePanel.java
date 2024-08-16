
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

public class GamePanel extends JPanel  {
    private static final long serialVersionUID = 1L;
	private Game game;
    public NextShapePanel nextShapePanel;
    MainFrame parent;

    public GamePanel(MainFrame mainFrame, String player_name) {
    	this.parent = mainFrame;
        this.game = new Game(this, player_name);
        setLayout(new BorderLayout());
        
        // Add the board panel to the right
        add(this.game.getBoard(), BorderLayout.WEST);

        // Right panel with two sections
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1)); // 3 rows, 1 column

        // Add the next shape panel to the right
        JLabel nextShapeLabel = new JLabel("Next Shape");
		nextShapeLabel.setForeground(Color.BLACK); // Set text color to blue
        nextShapeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        nextShapeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.nextShapePanel = new NextShapePanel();
        rightPanel.add(nextShapeLabel);
        rightPanel.add(nextShapePanel);
        
        InstructionsPanel instructionsPanel = new InstructionsPanel();
        rightPanel.add(instructionsPanel);

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
