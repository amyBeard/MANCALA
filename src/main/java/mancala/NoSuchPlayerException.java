package mancala;
import java.io.Serializable;

public class NoSuchPlayerException extends Exception implements Serializable {
    private static final long serialVersionUID = 1000002;
    public NoSuchPlayerException(){
        super("No Such Player Exception");
    }
    public NoSuchPlayerException(final String message){
        super(message);
    }
}
