public class Game {
    private int points = 0;

    public void roll(int i) {
        this.points += i;
    }

    public int score() {
        return points;
    }
}
