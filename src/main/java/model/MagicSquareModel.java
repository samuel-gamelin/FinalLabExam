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
    private int[][] numberGrid;

    /**
     * A 2D array of boolean representing which squares have been occupied.
     */
    private boolean[][] occupiedGrid;

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
    private List<MagicSquareListener> magicSquareListenerList;

    /**
     * Constructs a MagicSquare model.
     */
    public MagicSquareModel() {
        this.numberGrid = new int[SIZE][SIZE];
        this.occupiedGrid = new boolean[SIZE][SIZE];
        this.numFreeSquares = SIZE * SIZE;
        this.status = Status.IN_PROGRESS;
        this.magicSquareListenerList = new ArrayList<>();
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
     * Makes a move on this model's grid. Notifies all listeners of the updated status after the move has been made if
     * the coordinates of the move are valid (i.e. within the grid's size and not on an occupied square), otherwise the
     * method returns immediately without making any changes to this model.
     *
     * @param x      The x-coordinate of the move that is to be made
     * @param y      The y-coordinate of the move that is to be made
     * @param number The number that is to be placed on the square outlined by the provided x and y coordinates
     */
    public void play(int x, int y, int number) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE || occupiedGrid[x][y]) {
            return;
        }

        this.numberGrid[x][y] = number;
        this.occupiedGrid[x][y] = true;
        this.numFreeSquares--;

        if (numFreeSquares == 0) { // Update the status when all squares have been filled
            updateStatus();
        }

        MagicSquareEvent event = new MagicSquareEvent(x, y, number, status);

        for (MagicSquareListener magicSquareListener : magicSquareListenerList) {
            magicSquareListener.handleMagicSquareEvent(event);
        }
    }

    /**
     * Resets this model to default configurations.
     */
    public void reset() {
        /*
         * There is no need to reset the grid of integers as a winner will only
         * be determined when all values have been overwritten by new values from the user.
         */
        this.status = Status.IN_PROGRESS;
        this.numFreeSquares = SIZE * SIZE;
        this.occupiedGrid = new boolean[SIZE][SIZE];
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
     * Updates the status of this model based on the underlying grid representing the game's state. Intended to be used
     * only when the game has been completed (all squares filled in).
     */
    private void updateStatus() {
        Set<Integer> allSums = new HashSet<>(); // This set will keep track of all individual sums across all rows, columns, and diagonals

        List<Integer> integerListRow = new ArrayList<>();       // This list will be used to add numbers along rows
        List<Integer> integerListColumn = new ArrayList<>();    // This list will be used to add characters along columns
        List<Integer> integerListDiagonal = new ArrayList<>();  // This list will be used to add characters along the two diagonals

        for (int i = 0; i < SIZE; i++) {        // Here, characters in each set of rows and column are added to the corresponding lists
            for (int j = 0; j < SIZE; j++) {
                integerListRow.add(this.numberGrid[i][j]);
                integerListColumn.add(this.numberGrid[j][i]);
            }

            allSums.add(integerListRow.stream().mapToInt(Integer::intValue).sum());     // This adds the sum of the numbers in the row to the set
            allSums.add(integerListColumn.stream().mapToInt(Integer::intValue).sum());  // This adds the sum of the numbers in the column to the set

            integerListRow.clear();     // Clear both lists so they can be used for the next for loop iteration
            integerListColumn.clear();
        }

        // The same idea applies here to the two following checks for diagonals
        for (int i = 0; i < SIZE; i++) {
            integerListDiagonal.add(this.numberGrid[i][i]);  // This puts all numbers along the negative-sloped diagonal in the list
        }

        allSums.add(integerListDiagonal.stream().mapToInt(Integer::intValue).sum()); // This adds the sum of the numbers in the negative-sloped diagonal to the list
        integerListDiagonal.clear();    // Clear the list so it can be used for the last remaining diagonal

        for (int i = 0; i < SIZE; i++) {
            integerListDiagonal.add(this.numberGrid[SIZE - 1 - i][i]);  // This puts all numbers along the positive-sloped diagonal in the list
        }

        allSums.add(integerListDiagonal.stream().mapToInt(Integer::intValue).sum()); // This adds the sum of the numbers in the positive-sloped diagonal to the set

        if (allSums.size() == 1) {  // If all sums that were added to the set are the same (1 element in the set), then it is a victory
            this.status = Status.VICTORY;
        } else {    // Otherwise it is not a victory
            this.status = Status.NO_VICTORY;
        }
    }
}
