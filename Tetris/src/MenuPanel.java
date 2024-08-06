import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JButton startButton;
    private JTextField playerNameField;

    public MenuPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical arrangement

        // Create and add components
        JLabel nameLabel = new JLabel("Enter Player Name:");
        playerNameField = new JTextField(15);
        startButton = new JButton("Start Game");

        // Add components with some space in between
        add(Box.createVerticalStrut(20)); // Space above the label
        add(nameLabel);
        add(Box.createVerticalStrut(5));  // Space between label and text field
        add(playerNameField);
        add(Box.createVerticalGlue()); // Push the button to the bottom
        add(startButton);
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameField.getText();
                mainFrame.showGamePanel(playerName); // Pass player name to the Game class
            }
        });
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JTextField getPlayerNameField() {
        return playerNameField;
    }
}
