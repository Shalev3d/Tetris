import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private ScoresPanel scoresPanel;

    public MainFrame() {
        setTitle("Tetris Game");
     // Set initial frame size
        setSize(324, 400); // Small size for the menu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        menuPanel = new MenuPanel(this);
        scoresPanel = new ScoresPanel(this);

        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(scoresPanel, "Scores");

        add(mainPanel);

        showMenuPanel();

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    public void showMenuPanel() {
        cardLayout.show(mainPanel, "Menu");
    }

    public void showGamePanel(String player_name) {

        setSize(800, 600);
        gamePanel = new GamePanel(this, player_name);
        mainPanel.add(gamePanel, "Game");
        cardLayout.show(mainPanel, "Game");
    }
    
    // Similar method for showing the scores panel
    public void showScoresPanel() {
        // Switch to a larger frame size or keep the same size
        setSize(800, 600); // Size for the scores
        setLocationRelativeTo(null); // Re-center the frame
        cardLayout.show(mainPanel, "Scores");
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
