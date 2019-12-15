package model;

/**
 * This interface represents the behaviour that a listener of the MagicSquare game should have.
 *
 * @author Samuel Gamelin
 */
public interface MagicSquareListener {
    void handleMagicSquareEvent(MagicSquareEvent e);
}
