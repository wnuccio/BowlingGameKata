public class Game {
    private static final int MAX_ROLLS_NUMBER = 20;
    private int points = 0;
    private int rollsNumber = 0;
    private int lastRoll;
    private boolean spare = false;

    public void roll(int pins) {
        this.rollsNumber++;
        checkGameNotEnded();

        this.points += pins;

        if (spare) {
            points += pins;
            spare = false;

         } else {
            spare = lastRoll + pins == 10;
        }

        lastRoll = pins;
    }

    private void checkGameNotEnded() {
        if (rollsNumber > MAX_ROLLS_NUMBER) throw new IllegalStateException();
    }

    public int score() {
        return points;
    }
}
