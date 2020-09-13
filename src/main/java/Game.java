public class Game {
    private static final int MAX_FRAMES_NUMBER = 10;
    private static final int SPARE_POINTS = 10;

    private int framesNumber = 1;
    private int points = 0;
    private int pinsOnLastRoll;
    private boolean spare = false;
    private boolean secondRollOfFrame = false;

    public void roll(int currentRollPins) {
        checkGameNotEnded();

        this.points += currentRollPins;

        applySpareBonus(currentRollPins);

        setSpareStatus(currentRollPins);

        setCurrentFrame();

        pinsOnLastRoll = currentRollPins;
    }

    private void applySpareBonus(int currentRollPins) {
        if (spare) {
            points += currentRollPins;
        }
    }

    private void setSpareStatus(int currentRollPins) {
        spare = secondRollOfFrame && pinsOnLastRoll + currentRollPins == SPARE_POINTS;
    }

    private void setCurrentFrame() {
        if ( secondRollOfFrame) {
            framesNumber++;
        }

        secondRollOfFrame = ! secondRollOfFrame;
    }

    private void checkGameNotEnded() {
        if (framesNumber > MAX_FRAMES_NUMBER) throw new IllegalStateException();
    }

    public int score() {
        return points;
    }
}
