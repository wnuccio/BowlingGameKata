import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void score_as_sum_of_the_first_two_rolls() {
        game.roll(1);
        game.roll(2);

        assertEquals(3, game.score());
    }

    @Test
    void score_as_sum_of_twenty_rolls() {
        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }

        assertEquals(20, game.score());
    }

    @Test
    void error_if_more_than_twenty_rolls() {
        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }

        assertThrows(IllegalStateException.class, () -> game.roll(0));
    }

    @Test
    void bonus_on_spare() {
        game.roll(1);
        game.roll(9);
        game.roll(2);

        assertEquals(14, game.score());
    }

    @Test
    void bonus_on_spare_and_reset_spare_status() {
        game.roll(1);
        game.roll(9);
        game.roll(2);
        game.roll(1);

        assertEquals(15, game.score());
    }

    @Test
    void spare_must_be_inside_one_frame() {
        game.roll(1);
        game.roll(4);
        game.roll(6);
        game.roll(1);

        assertEquals(12, game.score());
    }

    @Test
    void bonus_on_strike() {
        game.roll(10);
        game.roll(1);
        game.roll(2);

        assertEquals(16, game.score());
    }

    @Test
    void spare_than_strike() {
        game.roll(5);
        game.roll(5);
        game.roll(10);
        game.roll(1);
        game.roll(2);

        assertEquals(5 + 5 + 10 + 10 + 1 + 1 + 2 + 2, game.score());
    }
}
