public class Score {
    private int score;

    public Score() {
        this.score = 0;
    }

    public void addPoints(int points) {
        this.score += points;
    }

    public int getScore() {
        return this.score;
    }

    public void reset() {
        this.score = 0;
    }
}
