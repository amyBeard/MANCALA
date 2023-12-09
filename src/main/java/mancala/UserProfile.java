package mancala;
import java.io.Serializable;

public class UserProfile implements Serializable{

    private static final long serialVersionUID = 1000012;
    final private String userName;
    private int numKalahGamesPlayed;
    private int numAyoGamesPlayed;
    private int numKalahGamesWon;
    private int numAyoGamesWon;

    public UserProfile(){
        this.userName = "noName";
        this.numKalahGamesPlayed = 0;
        this.numAyoGamesPlayed = 0;
        this.numKalahGamesWon = 0;
        this.numAyoGamesWon = 0;
    }

    public UserProfile(final String userName) {
        this.userName = userName;
        this.numKalahGamesPlayed = 0;
        this.numAyoGamesPlayed = 0;
        this.numKalahGamesWon = 0;
        this.numAyoGamesWon = 0;
    }


    public String getUserName() {
        return userName;
    }

    public int getNumKalahGamesPlayed() {
        return numKalahGamesPlayed;
    }

    public void incrementNumKalahGamesPlayed() {
        this.numKalahGamesPlayed++;
    }

    public int getNumAyoGamesPlayed() {
        return numAyoGamesPlayed;
    }

    public void incrementNumAyoGamesPlayed() {
        this.numAyoGamesPlayed++;
    }

    public int getNumKalahGamesWon() {
        return numKalahGamesWon;
    }

    public void incrementNumKalahGamesWon() {
        this.numKalahGamesWon++;
    }

    public int getNumAyoGamesWon() {
        return numAyoGamesWon;
    }

    public void incrementNumAyoGamesWon() {
        this.numAyoGamesWon++;
    }
}