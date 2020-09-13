import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    private void roll(int... pins) {
        for (int p : pins) {
            game.roll(p);
        }
    }


    private void rollXtimes(int pins, int times) {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
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
        rollXtimes(1, 20);

        assertEquals(20, game.score());
    }

    @Test
    void error_if_more_than_twenty_rolls() {
        assertThrows(IllegalStateException.class, () -> rollXtimes(1, 21));
    }

    @Test
    void bonus_on_spare() {
        roll(1, 9, 2);

        assertEquals(14, game.score());
    }

    @Test
    void bonus_on_spare_and_reset_spare_status() {
        roll(1, 9, 2, 1);

        assertEquals(15, game.score());
    }

    @Test
    void spare_must_be_inside_one_frame() {
        roll(1, 4, 6, 1);

        assertEquals(12, game.score());
    }

    @Test
    void bonus_on_strike() {
        roll(10, 1, 2);

        assertEquals(16, game.score());
    }

    @Test
    void spare_then_strike() {
        roll(5, 5, 10, 1, 2);

        assertEquals(5 + 5 + 10 + 10 + 1 + 1 + 2 + 2, game.score());
    }

    @Test
    void spare_on_last_roll() {
        rollXtimes(1, 18);
        roll(1, 9);
        roll(3);

        assertEquals(18 + 1 + 9 + 3 + 3, game.score());
    }

    @Test
    void strike_on_last_roll() {
        rollXtimes(1, 18);
        roll(10);
        roll(3);

        assertEquals(18 + 10 + 3 + 3, game.score());
    }

}
