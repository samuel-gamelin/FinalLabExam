package controller;

import model.MagicSquareModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a controller for the TicTacToe game. It is intended to be used on individual buttons.
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
     * Creates a TicTacToeController with the provided parameters.
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
     * Makes a move on this controller's associated model.
     *
     * @param e The event that occurred
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //prompt user here until they input a number
        int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter a number"));
        this.model.play(x, y, number);
    }
}
