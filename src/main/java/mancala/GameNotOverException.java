package mancala;
import java.io.Serializable;

public class GameNotOverException extends Exception implements Serializable {
    private static final long serialVersionUID = 1000000;

    public GameNotOverException() {
        super("Game Not Over Exception");
    }

    public GameNotOverException(final String message) {
        super(message);
    }
}
