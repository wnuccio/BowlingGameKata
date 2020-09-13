public class Game {
    private static final int MAX_FRAMES_NUMBER = 10;
    private static final int ALL_PINS = 10;
    private static final int NORMAL = 0;
    private static final int SPARE = 1;
    private static final int STRIKE = 2;

    private int frame = 1;
    private int points = 0;
    private boolean secondRollOfFrame = false;

    private int previousRollPins;
    private int status = NORMAL;

    public void roll(int currentRollPins) {
        checkGameNotEnded();

        addCurrentRollPoints(currentRollPins);

        applyBonus(currentRollPins);

        setStatus(currentRollPins);

        setCurrentFrame();

        setCurrentRoll();

        previousRollPins = currentRollPins;
    }

    private void setStatus(int currentRollPins) {
        if (secondRollOfFrame && currentRollPins + previousRollPins == ALL_PINS) {
            this.status = SPARE;
        } else if (! secondRollOfFrame && currentRollPins == ALL_PINS) {
            this.status = STRIKE;
        } else if (status != STRIKE) {
            this.status = NORMAL;
        }
    }

    private void checkGameNotEnded() {
        boolean moreThanMaxRolls = status == NORMAL && frame > MAX_FRAMES_NUMBER;
        boolean moreThanMaxRollsAfterSpareOrStrike = status != NORMAL && frame > MAX_FRAMES_NUMBER + 1;

        if (moreThanMaxRolls || moreThanMaxRollsAfterSpareOrStrike) throw new IllegalStateException();
    }

    private void addCurrentRollPoints(int currentRollPins) {
        this.points += currentRollPins;
    }

    private void applyBonus(int currentRollPins) {
        if (status == SPARE) {
            points += currentRollPins;
        } else if (status == STRIKE) {
            points += currentRollPins;
        } // status == NORMAL
    }

    private void setCurrentRoll() {
        if (status == SPARE) {
            secondRollOfFrame = !secondRollOfFrame;

        } else if (status == STRIKE) {
            secondRollOfFrame = false;

        } else { // status == NORMAL
            secondRollOfFrame = !secondRollOfFrame;
        }
    }

    private void setCurrentFrame() {
        if (status == SPARE) {
            if (secondRollOfFrame) {
                frame++;
            }

        } else if (status == STRIKE) {
            frame++;

        } else { // status == NORMAL
            if (secondRollOfFrame) {
                frame++;
            }
        }
    }

    public int score() {
        return points;
    }
}
