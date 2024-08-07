import java.awt.Graphics;

public interface Drawable {
    void draw(Graphics g);

	void move(Consts.Direction direction);
}