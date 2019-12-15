package view;

import controller.MagicSquareController;
import model.MagicSquareEvent;
import model.MagicSquareListener;
import model.MagicSquareModel;
import model.Status;

import javax.swing.*;
import java.awt.*;

import static model.MagicSquareModel.SIZE;

/**
 * This class represents the view portion of the Magic Square game.
 *
 * @author Samuel Gamelin
 */
public class MagicSquareView extends JFrame implements MagicSquareListener {
    /**
     * A scaling factor based on the current display's height (or width, depending on
     * which is greater), which will be used in calculations to determine
     * appropriate scaling of buttons and GUI elements.
     */
    public static final double SCALE_FACTOR = (Toolkit.getDefaultToolkit().getScreenSize().getWidth() > Toolkit
            .getDefaultToolkit().getScreenSize().getHeight()
            ? (0.5 * Toolkit.getDefaultToolkit().getScreenSize().getHeight())
            : (0.5 * Toolkit.getDefaultToolkit().getScreenSize().getWidth())) / SIZE;

    /**
     * The model associated with this view.
     */
    private MagicSquareModel model;

    /**
     * A 2D array of buttons used to play the game.
     */
    private JButton[][] buttons;

    /**
     * Constructs a view for the Magic Square game.
     */
    public MagicSquareView() {
        this.model = new MagicSquareModel();
        this.model.addMagicSquareListener(this);

        // Set the default look and feel to remain consistent across platforms
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }

        // Create a button panel with a SIZExSIZE grid layout
        JPanel buttonGrid = new JPanel(new GridLayout(SIZE, SIZE));

        // Create grid of buttons and add them to this frame
        this.buttons = new JButton[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton newButton = new JButton();
                newButton.setPreferredSize(new Dimension((int) SCALE_FACTOR, (int) SCALE_FACTOR));
                newButton.addActionListener(new MagicSquareController(i, j, model));
                newButton.setFont(new Font("Arial", Font.BOLD, (int) (SCALE_FACTOR / 1.5)));

                buttonGrid.add(this.buttons[i][j] = newButton);
            }
        }

        this.add(buttonGrid, BorderLayout.CENTER);

        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Magic Square");
        this.setVisible(true);
    }

    @Override
    public void handleMagicSquareEvent(MagicSquareEvent e) {
        int x = e.getX();
        int y = e.getY();
        int number = e.getNumber();
        Status status = e.getStatus();

        this.buttons[x][y].setEnabled(false);
        this.buttons[x][y].setText("" + number);

        if (status == Status.VICTORY) {
            this.disableButtons();
            int choice = JOptionPane.showOptionDialog(this, "Play again?", "Victory!", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, new String[]{"Yes", "No"}, "Yes");
            if (choice == 0) {
                this.model.reset();
                this.reset();
            } else {
                System.exit(0);
            }
        }
    }

    /**
     * Disables all buttons for the game.
     */
    private void disableButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.buttons[i][j].setEnabled(false);
            }
        }
    }

    /**
     * Resets view-related components.
     */
    private void reset() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.buttons[i][j].setText("");
                this.buttons[i][j].setEnabled(true);
            }
        }
    }

    /**
     * This is the entry point for the Magic Square game.
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MagicSquareView::new);
    }
}
