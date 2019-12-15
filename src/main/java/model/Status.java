package model;

/**
 * This enumeration represents the possible states of a Magic Square game.
 *
 * @author Samuel Gamelin
 */
public enum Status {
    VICTORY, NO_VICTORY, IN_PROGRESS;

    /**
     * The purpose of this toString() override is to return a neatly-formatted string representation of any instance
     * of this enumeration, which can be used in dialogs for the view.
     *
     * @return A neatly-formatted string representation of this enumerated instance
     */
    @Override
    public String toString() {
        switch (this) {
            case VICTORY:
                return "Victory!";
            case NO_VICTORY:
                return "No Victory";
            default:
                return "";
        }
    }
}