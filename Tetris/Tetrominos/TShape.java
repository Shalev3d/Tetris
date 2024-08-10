import java.awt.Color;
import java.awt.Point;

public class TShape extends Tetromino{

	
	private static final Color COLOR = Consts.TETROMINO_COLOR_T;
    private static final Point[][] DIMENSIONS = {
			{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) },
			{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
			{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) },
			{ new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) }
		};

    public TShape(Point originPosition) {
        super(originPosition, COLOR, DIMENSIONS, 0);
    }
}
