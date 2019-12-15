package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This test class contains a series of tests for the MagicSquareModel class. These tests work under the assumption that
 * the SIZE value of the model is 3.
 *
 * @author Samuel Gamelin
 */
public class MagicSquareModelTest {
    MagicSquareModel model;

    @Before
    public void setUp() {
        model = new MagicSquareModel();
    }

    @Test
    public void play() {
        // The game should start off in an in-progress state
        assertEquals(Status.IN_PROGRESS, model.getStatus());

        /* Case where it is a valid magic square
         *
         * | 2 | 7 | 6 |
         * | 9 | 5 | 1 |
         * | 4 | 3 | 8 |
         *
         */
        model.play(0, 0, 2); // Enter 2, 7, and 6 in the top row
        model.play(0, 1, 7);
        model.play(0, 2, 6);
        model.play(1, 0, 9); // Enter 9, 5, and 1 in the middle row
        model.play(1, 1, 5);
        model.play(1, 2, 1);

        assertEquals(Status.IN_PROGRESS, model.getStatus());    // Check that the game is still in progress

        model.play(2, 0, 4); // Enter 9, 5, and 1 in the bottom row
        model.play(2, 1, 3);
        model.play(2, 2, 8);

        assertEquals(Status.VICTORY, model.getStatus());    // Check that the game was won (a valid magic square was made)

        model.reset();                                      // Check that resetting the game brings it back to an "In Progress" mode
        assertEquals(Status.IN_PROGRESS, model.getStatus());

        /* Case where it is an invalid magic square
         *
         * | 1 | 1 | 1 |
         * | 1 | 1 | 1 |
         * | 1 | 1 | 2 |
         *
         */
        model.play(0, 0, 1); // Enter 1, 1, and 1 in the top row
        model.play(0, 1, 1);
        model.play(0, 2, 1);
        model.play(1, 0, 1); // Enter 1, 1, and 1 in the middle row
        model.play(1, 1, 1);
        model.play(1, 2, 1);
        model.play(2, 0, 1); // Enter 1 and 1 in the bottom row
        model.play(2, 1, 1);

        model.play(-1, -1, 100);               // Ensure that playing an invalid mode does not result in
        assertEquals(Status.IN_PROGRESS, model.getStatus()); // the game finishing when there is only one square left

        model.play(2, 2, 2); // Enter 2 in the bottom-most and right-most square

        assertEquals(Status.NO_VICTORY, model.getStatus());    // Check that the game was not won (an invalid magic square was made)

        model.reset();                                      // Check that resetting the game brings it back to an "In Progress" mode
        assertEquals(Status.IN_PROGRESS, model.getStatus());
    }

    @Test
    public void getStatus() {
        assertEquals(Status.IN_PROGRESS, model.getStatus());
    }
}
