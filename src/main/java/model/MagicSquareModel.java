package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a Magic Square model.
 *
 * @author Samuel Gamelin
 */
public class MagicSquareModel {
    /**
     * A constant representing the size of the Magic Square grid.
     */
    public static final int SIZE = 3;

    /**
     * A 2D array of strings representing which squares are occupied.
     */
    private int[][] grid;

    /**
     * A counter representing the number of free squares left.
     */
    private int numFreeSquares;

    /**
     * Represents the current status of the game.
     */
    private Status status;

    /**
     * A list containing all listeners of this model.
     */
    private transient List<MagicSquareListener> magicSquareListenerList;

    /**
     * Constructs a MagicSquare model.
     */
    public MagicSquareModel() {
        this.grid = new int[SIZE][SIZE];
        this.numFreeSquares = SIZE * SIZE;
        this.status = Status.IN_PROGRESS;
        this.magicSquareListenerList = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.grid[i][j] = -1;
            }
        }
    }

    /**
     * Adds a listener to this model.
     *
     * @param magicSquareListener The listener to add
     */
    public void addMagicSquareListener(MagicSquareListener magicSquareListener) {
        this.magicSquareListenerList.add(magicSquareListener);
    }

    /**
     * Makes a move on this model's grid. Notifies all listeners of the updated status after the move has been made.
     *
     * @param x The x-coordinate of the move that is to be made
     * @param y The y-coordinate of the move that is to be made
     */
    public void play(int x, int y, int number) {
        this.grid[x][y] = number;
        this.numFreeSquares--;

        if (numFreeSquares == 0) { // Update the status when all squares have been filled
            updateStatus();
        }

        MagicSquareEvent event = new MagicSquareEvent(this, x, y, number, status);

        for (MagicSquareListener magicSquareListener : magicSquareListenerList) {
            magicSquareListener.handleMagicSquareEvent(event);
        }
    }

    /**
     * Resets this model to default configurations.
     */
    public void reset() {
        this.status = Status.IN_PROGRESS;
        this.numFreeSquares = SIZE * SIZE;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.grid[i][j] = -1;
            }
        }
    }

    /**
     * Returns this model's status.
     *
     * @return this model's status
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns the number on this model's grid at the specified coordinates
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @return The number at the specified coordinates
     */
    public int getPiece(int x, int y) {
        return this.grid[x][y];
    }

    /**
     * Updates the status of this model based on the underlying grid representing the game's state.
     */
    private void updateStatus() {
        Set<Integer> all = new HashSet<>();

        List<Integer> integerListRow = new ArrayList<>();    // This set will be used to add numbers along rows
        List<Integer> integerListColumn = new ArrayList<>();    // This set will be used to add characters along columns
        List<Integer> integerListDiagonal = new ArrayList<>();    // This set will be used to add characters along the two diagonals

        for (int i = 0; i < SIZE; i++) {        // Here, characters in each set of rows and column are added to the set
            for (int j = 0; j < SIZE; j++) {
                integerListRow.add(this.grid[i][j]);
                integerListColumn.add(this.grid[i][j]);
            }

            all.add(integerListRow.stream().mapToInt(Integer::intValue).sum());     // This adds the sum of the numbers in the row to the set
            all.add(integerListColumn.stream().mapToInt(Integer::intValue).sum());  // This adds the sum of the numbers in the column to the set

            integerListRow.clear();
            integerListColumn.clear();
        }

        // The same idea applies here to the two following checks for diagonals
        for (int i = 0; i < SIZE; i++) {
            integerListDiagonal.add(this.grid[i][i]);
        }

        all.add(integerListDiagonal.stream().mapToInt(Integer::intValue).sum()); // This adds the sum of the numbers in the diagonal to the set
        integerListDiagonal.clear();

        for (int i = 0; i < SIZE; i++) {
            integerListDiagonal.add(this.grid[SIZE - 1 - i][i]);
        }

        all.add(integerListDiagonal.stream().mapToInt(Integer::intValue).sum()); // This adds the sum of the numbers in the diagonal to the set

        if (all.size() == 1) {  // If all sums that were added to the set are the same (1 element in the set), then it is a victory
            this.status = Status.VICTORY;
        } else {    // Otherwise it is not a victory
            this.status = Status.NO_VICTORY;
        }
    }

    /**
     * Determines if the provided set represents a winning set of characters, and if so sets the appropriate status
     * of this model.
     *
     * @param stringSet The set to validate
     * @return True if the only character in the set is "X" or "O", false otherwise.
     */
    private boolean isWinning(Set<String> stringSet) {
        if (stringSet.size() == 1) {
            String toValidate = stringSet.iterator().next();
            if (toValidate.equals("X")) {       // If the set has only one character, and it is "X", set the appropriate status
                //this.status = Status.X_WON;
                return true;
            } else if (toValidate.equals("O")) {    // Likewise for "O"
                //this.status = Status.O_WON;
                return true;
            }
        }
        return false;
    }
}
