package controller;

import model.MagicSquareModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a controller for the Magic Square game. It is intended to be used on individual buttons.
 * It contains an associated position (as two integers, x and y) along with the corresponding model on which a move
 * should be made when a button from the game is clicked.
 *
 * @author Samuel Gamelin
 */
public class MagicSquareController implements ActionListener {
    /**
     * The x-coordinate of the button associated with this controller.
     */
    private int x;

    /**
     * The y-coordinate of the button associated with this controller.
     */
    private int y;

    /**
     * The model associated with this controller.
     */
    private MagicSquareModel model;

    /**
     * Creates a MagicSquareController with the provided parameters.
     *
     * @param x     The x-coordinate for the button associated with this controller
     * @param y     The y-coordinate for the button associated with this controller
     * @param model The model that should be associated with this controller
     */
    public MagicSquareController(int x, int y, MagicSquareModel model) {
        this.x = x;
        this.y = y;
        this.model = model;
    }

    /**
     * This method will be invoked whenever a button in the game is clicked. A move on this controller's associated
     * model will be made once the user has provided a valid number. If the user decides to cancel with inputting a
     * number, no changes will be made to this controller's associated model.
     *
     * @param e The event that occurred
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean invalidNumber = false; // Used to keep track of if the user has put in an invalid number (start off by assuming it's invalid)

        /*
         * This do-while loop will prompt the user to input a valid number until they either input a valid number or
         * cancel their input, at which point the function returns without making a move.
         */
        do {
            String input = JOptionPane.showInputDialog(null, invalidNumber ? "Enter a valid number" : "Enter a number");

            if (input == null) { // If the user cancels, return immediately without making a move
                return;
            } else if (input.matches("-?\\d+")) {
                /*
                 * The above regular expression determines if the string represents a number, which can optionally start
                 * with a negative sign, followed by one or more digits. If the number is valid, the play method in the
                 * model is called with the number.
                 */
                int number = Integer.parseInt(input);
                this.model.play(x, y, number);
                invalidNumber = false;
            } else {
                invalidNumber = true;
            }
        } while (invalidNumber);
    }
}
