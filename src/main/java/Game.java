public class Game {
    private static final int MAX_ROLLS_NUMBER = 20;
    private int points = 0;
    private int rollsNumber = 0;

    public void roll(int i) {
        this.rollsNumber++;
        if (rollsNumber > MAX_ROLLS_NUMBER) throw new IllegalStateException();

        this.points += i;
    }

    public int score() {
        return points;
    }
}
