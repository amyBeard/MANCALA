package mancala;
import java.io.Serializable;

public class PitNotFoundException extends Exception implements Serializable{
    private static final long serialVersionUID = 1000003;
    public PitNotFoundException(){
        super("Pit Not Found Exception");
    }
    public PitNotFoundException(final String message) {
        super(message);
    }
}

