package mancala;
import java.io.Serializable;

public class KalahRules extends GameRules implements Serializable {

    private static final long serialVersionUID = 1000006;
    private int stonesAdded;
    private int stonesToMove;
    private int playerNumber;
    private boolean repeatTurn;
    private boolean wrappedAround;
    
    public KalahRules(){
        this.repeatTurn = false;
        wrappedAround = false;
    }

    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        
        validateStartingPit(startPit);
        validatePlayerAndPitOwnership(playerNum ,startPit);
        playerNumber = playerNum;
        getDataStructure().setIterator(startPit,playerNum,false);
        stonesToMove = getDataStructure().getNumStones(startPit);
        
        if (stonesToMove == 0) {
            repeatTurn = true;
            throw new InvalidMoveException("Empty pit");
        }

        getDataStructure().removeStones(startPit);
        final int distributedStones = distributeStones(startPit);
        
        return stonesAdded;

    }

    private void validatePlayerAndPitOwnership(final int playerNum, final int startPit) throws InvalidMoveException {
        if (playerNum == 1 && startPit > 6 ||
            playerNum == 2 && startPit <= 6) {
            repeatTurn = true;
            throw new InvalidMoveException("Pit belongs to the opponent");
        }
    }

    private void validateStartingPit(final int startPit) throws InvalidMoveException {
        if (startPit < 0 || startPit > 12) {
            repeatTurn = true;
            throw new InvalidMoveException("Invalid starting pit index");
        }
    }

    @Override
    public int distributeStones(int startPit){

        int distributedStones = 0;
        int currentIndex = startPit;
        
        while (stonesToMove > 0) {
            
            currentIndex %= 13;
            if (currentIndex==0){
                currentIndex++;
                wrappedAround = true;
            }
            final Countable countable = gameBoard.next();
            if (countable instanceof Store){
                if (stonesToMove == 1){
                    countable.addStone();
                    stonesToMove--;
                    distributedStones++;
                    stonesAdded++;
                    repeatTurn = true;
                    return distributedStones;
                }
                countable.addStone();

            }else{
                if (countable.getStoneCount() == 0 && stonesToMove == 1){

                    if (startPit < 7 && currentIndex < 7){
                        final int stoppingPoint = wrappedAround ? currentIndex : currentIndex+1;
                        if (captureStones(stoppingPoint) == 0){
                            countable.addStone();
                        }
                    }else if (startPit >= 7 && currentIndex >= 7){
                        final int stoppingPoint = wrappedAround ? currentIndex : currentIndex+1;
                        if (captureStones(stoppingPoint) == 0){
                            countable.addStone();
                        }
                    }else{
                        countable.addStone();
                    }
                }else{
                    countable.addStone();
                }
            }

            currentIndex++;
            stonesToMove--;
            distributedStones++;
            
        }
        wrappedAround = false;
        repeatTurn = false;
        return distributedStones;
    }


    @Override
    public int captureStones(final int stoppingPoint) {

        int capturedStones = 0;
        final int oppositePit = (13 - stoppingPoint) % 14;
        capturedStones+=getDataStructure().getNumStones(oppositePit);
        if (capturedStones > 0){
            getDataStructure().removeStones(oppositePit);
            getDataStructure().removeStones(stoppingPoint);
            
            capturedStones++;
            getDataStructure().addToStore(playerNumber,capturedStones);
        }
        
        return capturedStones;
    }

    @Override
    public void registerPlayers(final Player one, final Player two) {
        // this method can be implemented in the abstract class.
        final Store store1 = new Store();
        final Store store2 = new Store();

        
        getDataStructure().setStore(store1, 1);
        getDataStructure().setStore(store2,2);

        store1.setOwner(one);
        store2.setOwner(two);

        one.setStore(store1);
        two.setStore(store2);
        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/
    }

    @Override
    boolean isSideEmpty(final int pitNum){
        
        if (pitNum < 7){
            return this.isLeftSideEmpty();
        }else if (pitNum >= 7){
            return this.isRightSideEmpty();
        }
        return false;
    }

    boolean isLeftSideEmpty(){
        
        boolean leftSide = true;
        for (int i=1;i<7;i++){
            if (getNumStones(i)!=0){
                leftSide = false;
            }
                
        }
        if (leftSide){
            for (int j=6;j<13;j++){
                getDataStructure().addToStore(2, getNumStones(j));
                getDataStructure().removeStones(j);
            }
        }
        return leftSide;
    }

    boolean isRightSideEmpty(){
        
        boolean rightSide = true;

        for (int i=7;i<13;i++){
    
            if (getNumStones(i)!=0){
                rightSide = false;
            }
        }
        if (rightSide){
            for (int j=1;j<7;j++){
                getDataStructure().addToStore(1, getNumStones(j));
                getDataStructure().removeStones(j);
            }
        }
        
        return rightSide;
    }

    @Override
    boolean getRepeatTurn(){
        return repeatTurn;
    }
}
