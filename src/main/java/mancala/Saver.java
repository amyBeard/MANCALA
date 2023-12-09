package mancala;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Saver{
    
    public static void saveObject(final Serializable toSave, final String filename) throws IOException{ 
 
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(toSave);
        }
    }

    public static Serializable loadObject(final String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Serializable) ois.readObject();
        }
    }


}