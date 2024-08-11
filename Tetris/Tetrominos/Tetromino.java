import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Tetromino implements Drawable, Movable {
    protected Point originPosition; // Origin position of the Tetromino
    protected Color color; // Color of the Tetromino
    protected Point[][] blocks; // Dimensions of the Tetromino
    protected int rotation; // Rotation state of the Tetromino
    
    // default constructor
    public Tetromino() {}
    
    // Constructor
    public Tetromino(Point originPosition, Color color, Point[][] blocks, int rotation) {
        this.originPosition = originPosition;
        this.color = color;
        this.blocks = blocks;
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

    public Point[] getBlocks() {
        return blocks[rotation];
    }

    public int getRotation() {
        return rotation;
    }
    
    // Checks if there is any collide with the edge or in any shape
    public boolean isCollide(int newX, int newY, Board board) {
    	for (Point block : blocks[rotation]) {
            // Check if the point is within the bounds of the dimension
            if (block.x + newX >= Consts.COLS-1 || block.x + newX <= 0 ||
        		block.y + newY >= Consts.ROWS || block.y + newY <=0) {
            	System.out.println("MORGEN COLLIFES  "+ this.getClass());
                return true;
            }
            // Check if the point (x, y) collides with something on the board
            if (board.grid[block.x + newX][block.y + newY] != Color.BLACK) {
            	System.out.println("COLLISION COLLIFES  "+ this.getClass());
                return true; // Collision detected with something other than empty space
            }
        }
        return false; // No collision detected
    }
    
    
    // Rotate the shape by 90 degrees clockwise, if collides, stay in the same rotation
    public void rotate(Board board) {
    	int newRotation = (rotation + 1) % 4;
        if (newRotation <0) {
        	newRotation = 3;
        }
        int prevRotation = rotation;
        rotation = newRotation;
        if (isCollide(this.originPosition.x, this.originPosition.y, board))
        	rotation = prevRotation; // Update rotation state
        board.repaint();
    }
    
    // Implement the move method
    @Override
    public void move(Consts.Direction direction, Board board) {
        switch (direction) {
            case UP:
                rotate(board); // Rotate the shape
                break;
            case DOWN: 
                if (!isCollide(this.originPosition.x, this.originPosition.y+1, board)) {
                	originPosition.y += 1; // Move down by 1 unit
                }
                break;
            case LEFT:
                if (!isCollide(this.originPosition.x - 1, this.originPosition.y, board)) {
                	originPosition.x -= 1; // Move down by 1 unit
                }
                break;
            case RIGHT:
                if (!isCollide(this.originPosition.x + 1, this.originPosition.y, board)) {
                	originPosition.x += 1; // Move down by 1 unit
                }
                break;
        }
        board.repaint();
    }
    

    
    // Draw the Tetromino on the board
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        for (Point block : blocks[rotation]) {
            int x = originPosition.x + block.x;
            int y = originPosition.y + block.y;
            g.fillRect(x * Consts.BLOCK_SIZE, y * Consts.BLOCK_SIZE, Consts.BLOCK_SIZE-1, Consts.BLOCK_SIZE-1);
        }
    }

	public void drop(Board board) {
		if (!isCollide(this.originPosition.x, this.originPosition.y+1, board)) {
			originPosition.y += 1; // Move down by 1 unit
		}
		else if (this.originPosition.y+1 ==1){
			board.game.gameOver();
		}
		else {
			fixToGrid(board);
			
		}
		board.invalidate();
		board.validate();
		board.repaint();
		
//		System.out.println("Tetromino:: drop() shape="+this.getClass());
		
	}

	public void fixToGrid(Board board) {
		for (Point block : blocks[rotation]) {
			board.grid[originPosition.x + block.x][originPosition.y+block.y] = color;
		}
		board.clearRows();
		board.newPiece();
			
	}
}
