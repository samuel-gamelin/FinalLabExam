package model;

import org.junit.Before;
import org.junit.Test;

import static model.MagicSquareModel.SIZE;
import static org.junit.Assert.assertEquals;

/**
 * This test class contains a series of tests for the MagicSquareEvent class. All methods in the class under test are
 * being tested.
 *
 * @author Samuel Gamelin
 */
public class MagicSquareEventTest {
    MagicSquareEvent validEvent;
    MagicSquareEvent invalidEvent;

    @Before
    public void setUp() {
        validEvent = new MagicSquareEvent(2, 1, 10, Status.IN_PROGRESS);
        invalidEvent = new MagicSquareEvent(-500, SIZE + 10, 5, Status.NO_VICTORY);
    }

    @Test
    public void getX() {
        assertEquals(2, validEvent.getX());
        assertEquals(-1, invalidEvent.getX());
    }

    @Test
    public void getY() {
        assertEquals(1, validEvent.getY());
        assertEquals(-1, invalidEvent.getY());
    }

    @Test
    public void getNumber() {
        assertEquals(10, validEvent.getNumber());
        assertEquals(5, invalidEvent.getNumber());
    }

    @Test
    public void getStatus() {
        assertEquals(Status.IN_PROGRESS, validEvent.getStatus());
        assertEquals(Status.NO_VICTORY, invalidEvent.getStatus());
    }
}