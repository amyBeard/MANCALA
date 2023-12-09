package mancala;
import java.io.Serializable;

public class Store implements Countable, Serializable{

    private Player owner;
    private int totalStones;
    private static final long serialVersionUID = 1000007;

    public Store(){
        this.totalStones = 0;
    }
    public void setOwner(final Player player){
        owner = player;
    }

    public Player getOwner(){
        return owner;
    }

    @Override
    public void addStones(final int amount) {
        totalStones += amount;
    }

    public int getTotalStones(){
        return totalStones;
    }

    public int emptyStore(){
        final int previousCount = totalStones;
        totalStones = 0;
        return previousCount;
    }

    @Override
    public int removeStones() {
        return 0;
    }

    @Override
    public void addStone() {
        totalStones++;
    }

    @Override
    public int getStoneCount() {
        return totalStones;
    }

    @Override
    public String toString(){
        return owner.toString() + " Store: " + this.getTotalStones();
    }
}