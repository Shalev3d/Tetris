import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

public class ScoresPanel extends JPanel {
    private static final long serialVersionUID = 1L;

	public ScoresPanel(MainFrame mainFrame) {
        JButton backButton = new JButton("Back to Menu");
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showMenuPanel();
            }
        });
        
        add(backButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Scores Panel", 100, 100);
    }
}
