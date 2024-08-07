import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Tetromino implements Drawable, Movable {
    private Point originPosition; // Origin position of the Tetromino
    private Color color; // Color of the Tetromino
    private Dimension[] dimensions; // Dimensions of the Tetromino
    private int rotation; // Rotation state of the Tetromino
    
    // Constructor
    public Tetromino(Point originPosition, Color color, Dimension[] dimensions, int rotation) {
        this.originPosition = originPosition;
        this.color = color;
        this.dimensions = dimensions;
        this.rotation = rotation;
    }    
     
    // Setters
    public void setRotate(int rotation) {
    	this.rotation = rotation;
    }
    
    // Getters
    public Point getOriginPosition() {
        return originPosition;
    }

    public Color getColor() {
        return color;
    }

    public Dimension[] getDimensions() {
        return dimensions;
    }

    public int getRotation() {
        return rotation;
    }
    
    // Checks if there is any collide with the edge or in any shape
    public boolean isCollide(int x, int y, Color[][] board) {
    	for (Dimension dim : dimensions) {
            // Check if the point is within the bounds of the dimension
            if (x >= originPosition.x && x <= originPosition.x + dim.width &&
                y >= originPosition.y && y <= originPosition.y + dim.height) {
                // Check if the point (x, y) collides with something on the board
                if (board[x][y] != Color.BLACK) {
                    return true; // Collision detected with something other than empty space
                }
            }
        }
        return false; // No collision detected
    }

    // Rotate the shape
    public void rotate() {
        // Abstract method to be implemented by subclasses
        // Rotation logic will vary depending on the specific shape
        rotateShape();
    }
    

    // Implement the move method
    @Override
    public void move(Consts.Direction direction) {
        switch (direction) {
            case UP:
                rotate(); // Rotate the shape
                break;
            case DOWN: 
                originPosition.translate(0, 1); // Move down by 1 unit
                break;
            case LEFT:
                originPosition.translate(-1, 0); // Move left by 1 unit
                break;
            case RIGHT:
                originPosition.translate(1, 0); // Move right by 1 unit
                break;
        }
    }
    
	
 // Abstract methods to be implemented by subclasses
    public abstract void rotateShape();
    public abstract void draw(Graphics g);
}
