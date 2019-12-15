package model;

import static model.MagicSquareModel.SIZE;

/**
 * This class represents an Event in the game of Magic Square, encapsulating information about a move
 * that was just made, such as the position of the move on the grid, the number that was added, and
 * the resulting status of the game.
 *
 * @author Samuel Gamelin
 */
public class MagicSquareEvent {
    /**
     * The x-coordinate of the move that occurred.
     */
    private int x;

    /**
     * The y-coordinate of the move that occurred.
     */
    private int y;

    /**
     * The number that was added to the desired square (located at (x, y)).
     */
    private int number;

    /**
     * The new status of the game.
     */
    private Status status;

    /**
     * Constructs a MagicSquareEvent. Should the coordinates be invalid (less than 0 or greater than or equal to
     * the model's size, they are set to -1.
     *
     * @param x      The x-coordinate associated with this event
     * @param y      The y-coordinate associated with this event
     * @param number The number value associated with this event
     * @param status The status associated with this event
     */
    public MagicSquareEvent(int x, int y, int number, Status status) {
        this.x = (x < 0 || x >= SIZE) ? -1 : x;
        this.y = (x < 0 || x >= SIZE) ? -1 : y;
        this.number = number;
        this.status = status;
    }

    /**
     * Returns the x-coordinate associated with this event.
     *
     * @return the x-coordinate associated with this event
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate associated with this event.
     *
     * @return the y-coordinate associated with this event
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the number associated with this event.
     *
     * @return the number associated with this event
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the status associated with this event.
     *
     * @return the status associated with this event
     */
    public Status getStatus() {
        return status;
    }
}
