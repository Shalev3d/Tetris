import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class IShape extends Tetromino{

	
	private static final Color COLOR = Consts.TETROMINO_COLOR_I;
    private static final Dimension[] DIMENSIONS = {
        new Dimension(4, 1), // Initial orientation (horizontal line)
        new Dimension(1, 4)  // Rotated orientation (vertical line)
    };

    public IShape(Point originPosition) {
        super(originPosition, COLOR, DIMENSIONS, 0);
    }
    
    

	@Override
	public void rotateShape() {
		// TODO Auto-generated method stub
		setRotate((getRotation()+1)%2);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}
