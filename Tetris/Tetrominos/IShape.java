import java.awt.Color;
import java.awt.Point;

public class IShape extends Tetromino{

	
	private static final Color COLOR = Consts.TETROMINO_COLOR_I;
    private static final Point[][] DIMENSIONS = {
			{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
			{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) },
			{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
			{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) }
		};

    public IShape(Point originPosition) {
        super(originPosition, COLOR, DIMENSIONS, 0);
    }
}
