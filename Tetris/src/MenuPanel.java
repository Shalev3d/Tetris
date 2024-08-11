import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton startButton;
    private JButton highScoresButton;
    private JTextField playerNameField;

    public MenuPanel(MainFrame mainFrame) {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical arrangement

        // Load the image
        ImageIcon headerIcon = new ImageIcon(getClass().getResource("Tetris.jpeg")); // Adjust path as necessary
        JLabel headerLabel = new JLabel(headerIcon);

        // Create and add components
        JLabel nameLabel = new JLabel("Enter Player Name:");
        playerNameField = new JTextField(15);
        startButton = new JButton("Start Game");
        highScoresButton = new JButton("High Scores");

        // Set preferred sizes for the text field and buttons
        playerNameField.setMaximumSize(new Dimension(200, 30));
        startButton.setMaximumSize(new Dimension(200, 30));
        highScoresButton.setMaximumSize(new Dimension(200, 30));

        // Create a container for each component to center them horizontally
        Box headerBox = Box.createHorizontalBox();
        headerBox.add(Box.createHorizontalGlue());
        headerBox.add(headerLabel);
        headerBox.add(Box.createHorizontalGlue());

        Box nameBox = Box.createHorizontalBox();
        nameBox.add(Box.createHorizontalGlue());
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalGlue());

        Box textBox = Box.createHorizontalBox();
        textBox.add(Box.createHorizontalGlue());
        textBox.add(playerNameField);
        textBox.add(Box.createHorizontalGlue());

        Box startButtonBox = Box.createHorizontalBox();
        startButtonBox.add(Box.createHorizontalGlue());
        startButtonBox.add(startButton);
        startButtonBox.add(Box.createHorizontalGlue());

        Box highScoresButtonBox = Box.createHorizontalBox();
        highScoresButtonBox.add(Box.createHorizontalGlue());
        highScoresButtonBox.add(highScoresButton);
        highScoresButtonBox.add(Box.createHorizontalGlue());

        // Add components with some space in between
        add(headerBox); // Add the image at the top
        add(Box.createVerticalStrut(20)); // Space below the image
        add(nameBox);
        add(Box.createVerticalStrut(10));  // Space between label and text field
        add(textBox);
        add(Box.createVerticalStrut(20)); // Space between text field and button
        add(startButtonBox);
        add(Box.createVerticalStrut(10)); // Space between buttons
        add(highScoresButtonBox);
        add(Box.createVerticalGlue()); // Push everything upwards


        // Center all components horizontally
        add(Box.createHorizontalGlue());

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameField.getText();
                mainFrame.showGamePanel(playerName); // Pass player name to the Game class
            }
        });
        
        highScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showScoresPanel(0);
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
