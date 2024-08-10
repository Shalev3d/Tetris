import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
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

        // Add all panels to the main panel with CardLayout
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(scoresPanel, "Scores");

        add(mainPanel);

        // Show the menu panel initially
        cardLayout.show(mainPanel, "Menu");

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    public void showMenuPanel() {
        setSize(324, 400); // Set to the initial small size
        setLocationRelativeTo(null);
        cardLayout.show(mainPanel, "Menu");
    }

    public void showGamePanel(String playerName) {
    	gamePanel = new GamePanel(this, playerName); // Initialize with a placeholder name
        mainPanel.add(gamePanel, "Game");

        // Switch to the game panel and adjust frame size
        setSize(800, 550);
        setLocationRelativeTo(null);
        cardLayout.show(mainPanel, "Game");
    }

    public void showScoresPanel() {
        setSize(800, 550);
        setLocationRelativeTo(null);
        cardLayout.show(mainPanel, "Scores");
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
