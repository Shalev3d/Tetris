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
        setSize(800, 600);
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
        gamePanel = new GamePanel(this, player_name);
        mainPanel.add(gamePanel, "Game");
        cardLayout.show(mainPanel, "Game");
    }

    public void showScoresPanel() {
        cardLayout.show(mainPanel, "Scores");
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
