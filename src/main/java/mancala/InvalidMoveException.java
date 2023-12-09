package mancala;
import java.io.Serializable;

public class InvalidMoveException extends Exception implements Serializable {
    private static final long serialVersionUID = 1000001;
    public InvalidMoveException() {
        super("Invalid Move Exception");
    }
    public InvalidMoveException(final String message) {
        super(message);
    }
}