//import java.util.Random;
//
//public class Shape {
//    private Tetrominoes pieceShape;     // Represents the type of Tetrominoe shape of the piece.
//    private int[][] coordinates;       // Stores the x and y coordinates of each block in the piece.
//
//    public Shape() {
//        coordinates = new int[4][2];   // Initialize the coordinates array with 4 rows and 2 columns.
//        setShape(Tetrominoes.NoShape);  // Set the initial shape to NoShape.
//    }
//
//    public void setShape(Tetrominoes shape) {
//        int[][][] coordsTable = new int[][][] {
//                {{0, 0}, {0, 0}, {0, 0}, {0, 0}},             // NoShape
//                {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},         // ZShape
//                {{0, -1}, {0, 0}, {1, 0}, {1, 1}},           // SShape
//                {{0, -1}, {0, 0}, {0, 1}, {0, 2}},           // LineShape
//                {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},           // TShape
//                {{0, 0}, {1, 0}, {0, 1}, {1, 1}},             // SquareShape
//                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},         // LShape
//                {{1, -1}, {0, -1}, {0, 0}, {0, 1}}           // MirroredLShape
//        };
//
//        // Copy the coordinates from the lookup table for the given shape.
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 2; ++j) {
//                coordinates[i][j] = coordsTable[shape.ordinal()][i][j];
//            }
//        }
//        pieceShape = shape;   // Set the piece shape.
//    }
//
//    private void setX(int index, int x) {
//        coordinates[index][0] = x;   // Set the x-coordinate of the block at the given index.
//    }
//
//    private void setY(int index, int y) {
//        coordinates[index][1] = y;   // Set the y-coordinate of the block at the given index.
//    }
//
//    public int x(int index) {
//        return coordinates[index][0];   // Get the x-coordinate of the block at the given index.
//    }
//
//    public int y(int index) {
//        return coordinates[index][1];   // Get the y-coordinate of the block at the given index.
//    }
//
//    public Tetrominoes getShape() {
//        return pieceShape;   // Get the shape of the piece.
//    }
//
//    public void setRandomShape() {
//        Random rand = new Random();
//        int x = Math.abs(rand.nextInt()) % 7 + 1;
//        Tetrominoes[] values = Tetrominoes.values();
//        setShape(values[x]);   // Set the shape of the piece to a random shape.
//    }
//
//    public int minY() {
//        int minY = coordinates[0][1];
//        for (int i = 0; i < 4; i++) {
//            minY = Math.min(minY, coordinates[i][1]);   // Find the minimum y-coordinate of the piece.
//        }
//        return minY;
//    }
//
//    public Shape rotateLeft() {
//        if (pieceShape == Tetrominoes.SquareShape) {
//            return this;   // Square shape does not change when rotated, so return the current instance.
//        }
//        Shape rotatedShape = new Shape();   // Create a new Shape instance for the rotated shape.
//        rotatedShape.pieceShape = pieceShape;   // Set the shape of the rotated piece.
//
//        // Perform rotation calculations for each block.
//        for (int i = 0; i < 4; ++i) {
//            rotatedShape.setX(i, y(i));
//            rotatedShape.setY(i, -x(i));
//        }
//
//        return rotatedShape;   // Return the rotated shape.
//    }
//
//    public Shape rotateRight() {
//        if (pieceShape == Tetrominoes.SquareShape) {
//            return this;   // Square shape does not change when rotated, so return the current instance.
//        }
//        Shape rotatedShape = new Shape();   // Create a new Shape instance for the rotated shape.
//        rotatedShape.pieceShape = pieceShape;   // Set the shape of the rotated piece.
//
//        // Perform rotation calculations for each block.
//        for (int i = 0; i < 4; ++i) {
//            rotatedShape.setX(i, -y(i));
//            rotatedShape.setY(i, x(i));
//        }
//
//        return rotatedShape;   // Return the rotated shape.
//    }
//}
