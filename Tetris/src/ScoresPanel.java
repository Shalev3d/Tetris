import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

public class ScoresPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int playerScore;
    private DBHandler dbHandler;
    
    public ScoresPanel(MainFrame mainFrame, int playerScore) {
        this.playerScore = playerScore;
        this.dbHandler = new DBHandler();

        setLayout(new BorderLayout());

        if (playerScore > 0) {
            JLabel scoreLabel = new JLabel("Your Score: " + playerScore, JLabel.CENTER);
            add(scoreLabel, BorderLayout.NORTH);
        }
        
        JLabel highScoresLabel = new JLabel("High Scores:", JLabel.CENTER);
        add(highScoresLabel, BorderLayout.CENTER);
        
        String[] columnNames = {"Player Name", "Score", "Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<String[]> highScores = dbHandler.getHighScores();
        for (String[] scoreData : highScores) {
            model.addRow(scoreData);
        }
        
        JTable scoresTable = new JTable(model);
        scoresTable.setPreferredScrollableViewportSize(new Dimension(300, 150)); // Set table size

        JScrollPane scrollPane = new JScrollPane(scoresTable);

        // Panel for the label and table with padding
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0)); // Padding at the top
        tablePanel.add(highScoresLabel, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> mainFrame.showMenuPanel());
        add(backButton, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (playerScore <= 0) {
            g.drawString("High Scores", 100, 100);
        }
    }
}
