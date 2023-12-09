package mancala;
import java.io.Serializable;

public class Pit implements Countable, Serializable{

    private int stoneCount;
    private static final long serialVersionUID = 1000008;

    public Pit(){
        this.stoneCount = 0;
    }
    
    @Override
    public int getStoneCount(){
        return stoneCount;
    }

    @Override
    public void addStone(){
        stoneCount++;
    }

    @Override
    public int removeStones(){
        stoneCount = 0;
        return stoneCount;
    }

    @Override
    public void addStones(final int numToAdd){
        stoneCount+=numToAdd;
    }

    @Override
    public String toString(){

        return "Pit has " + stoneCount + " stones";
    }
    
    
}