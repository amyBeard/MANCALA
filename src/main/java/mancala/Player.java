package mancala;
import java.io.Serializable;

public class Player implements Serializable{

    private String playerName;
    private Store playerStore;
    private static final long serialVersionUID = 1000005;
    private UserProfile userProfile;

    public Player(){
        this.playerStore = new Store();
    }

    public Player(final String name){
        this.setName(name);
        this.playerStore = new Store();
    }

    public Player(final UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getName() {
        return userProfile.getUserName();
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setName(final String name){
        playerName = name;
    }

    public void setStore(final Store store){
        playerStore = new Store();
        playerStore = store;
    }

    public Store getStore(){
        return playerStore;
    }

    public int getStoreCount(){
        return playerStore.getTotalStones();
    }

    @Override
    public String toString(){
        return this.getName();
    }
}