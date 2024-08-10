import javax.swing.SwingUtilities;

public class GameTimer implements Runnable {
    private Game game;
    private boolean running;
    private int delay; // Delay in milliseconds

    public GameTimer(Game game, int delay) {
        this.game = game;
        this.delay = delay;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(delay); // Sleep for the specified delay
                game.getCurrentTetromino().drop(this.game.getBoard()); // Call the drop method to move Tetromino down
                
                System.out.println("THREAD is running");
                // Update the UI on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> game.getGamePanel().repaint());
            } catch (InterruptedException e) {
                // Handle interruption (e.g., thread was interrupted)
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}