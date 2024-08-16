
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverDialog extends JDialog {

    private static final long serialVersionUID = 1L;

	public GameOverDialog(JFrame parent, int score, Runnable showScoresPanel) {
        super(parent, "Game Over", true);

        // Create components
        JLabel messageLabel = new JLabel("Game Over", JLabel.CENTER);
        JLabel scoreLabel = new JLabel("Your Score: " + score, JLabel.CENTER);
        
        JButton scoresButton = new JButton("View Scores");

        // Set layout and add components
        setLayout(new GridLayout(3, 1));
        add(messageLabel);
        add(scoreLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(scoresButton);
        add(buttonPanel);

        // Set dialog properties
        setSize(300, 200);
        setLocationRelativeTo(parent);


        // Action listener for Scores button
        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
                SwingUtilities.invokeLater(showScoresPanel);
                
            }
        });
    }
}
