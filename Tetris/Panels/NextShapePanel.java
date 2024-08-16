
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class NextShapePanel extends JPanel {
    private static final long serialVersionUID = 1L;
	private Tetromino nextShape;

    public Point origin_position = new Point(0,0); 

    public NextShapePanel() {
        setPreferredSize(new Dimension(75, 75)); // Set size for next shape box
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
    }
    
        
}