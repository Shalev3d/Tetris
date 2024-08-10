import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class NextShapePanel extends JPanel {
        private static final long serialVersionUID = 1L;
		private Tetromino nextShape;

        public NextShapePanel() {
            setPreferredSize(new Dimension(100, 100)); // Set size for next shape box
            setBackground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }

        public void setNextShape(Tetromino nextShape) {
            this.nextShape = nextShape;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (nextShape != null) {
                g.setColor(nextShape.getColor());

                nextShape.draw(g);
            }
            System.out.println("paintComponent::NEXTSHAPE");
        }
}