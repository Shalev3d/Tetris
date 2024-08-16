
import javax.swing.*;

import java.awt.*;

public class InstructionsPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public InstructionsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Instructions");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        add(Box.createRigidArea(new Dimension(0, 10)));

        // Instruction for moving right
        JLabel rightLabel = createInstructionLabel("→", "Move Right");
        add(rightLabel);

        // Instruction for moving left
        JLabel leftLabel = createInstructionLabel("←", "Move Left");
        add(leftLabel);

        // Instruction for moving down
        JLabel downLabel = createInstructionLabel("↓", "Move Down");
        add(downLabel);

        // Instruction for rotating
        JLabel rotateLabel = createInstructionLabel("↑", "Rotate");
        add(rotateLabel);

        add(Box.createRigidArea(new Dimension(0, 20)));

        // Credits at the bottom
        JLabel authorLabel = new JLabel("\u00A9 " +Consts.GROUP_NAMES);
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(authorLabel);
    }

    private JLabel createInstructionLabel(String symbol, String text) {
        JLabel label = new JLabel(symbol + "  " + text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
}
