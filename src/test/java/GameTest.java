import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void score_zero_before_any_roll() {
        assertEquals(0, game.score());
    }

    @Test
    void score_zero_after_one_roll_of_zero() {
        game.roll(0);

        assertEquals(0, game.score());
    }

    @Test
    void score_one_after_one_roll_of_one() {
        game.roll(1);

        assertEquals(1, game.score());
    }
}