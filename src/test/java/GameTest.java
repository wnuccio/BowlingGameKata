import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void score_zero_after_one_roll_of_zero() {
        Game game = new Game();
        game.roll(0);

        assertEquals(0, game.score());
    }

    @Test
    void score_one_after_one_roll_of_one() {
        Game game = new Game();
        game.roll(1);

        assertEquals(1, game.score());
    }
}