package model;

import java.util.EventObject;

/**
 * This class represents an Event in the game of TicTacToe.
 *
 * @author Samuel Gamelin
 */
public class MagicSquareEvent extends EventObject {
    /**
     * The x-coordinate of the move that occurred.
     */
    private int x;

    /**
     * The y-coordinate of the move that occurred.
     */
    private int y;

    private int number;

    /**
     * The new status of the game.
     */
    private Status status;

    /**
     * Constructs a TicTacToeEvent.
     *
     * @param source The object on which the Event initially occurred.
     * @param x      The x-coordinate associated with this event
     * @param y      The y-coordinate associated with this event
     * @param status The status associated with this event
     * @throws IllegalArgumentException if source is null.
     */
    public MagicSquareEvent(Object source, int x, int y, int number, Status status) {
        super(source);
        this.x = x;
        this.y = y;
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
