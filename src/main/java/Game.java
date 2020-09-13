public class Game {
    private int points;

    public void roll(int i) {
        this.points = i;
    }

    public int score() {
        return points;
    }
}
