import java.awt.Color;
import java.awt.Point;

public class LShape extends Tetromino{

	
	private static final Color COLOR = Consts.TETROMINO_COLOR_L;
    private static final Point[][] DIMENSIONS = {
			{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) },
			{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) },
			{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) },
			{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) }
		};

    public LShape() {
        super(new Point(0,0), COLOR, DIMENSIONS, 0);
    }	
}
