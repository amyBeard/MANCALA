package mancala;
import java.util.ArrayList;
import java.io.Serializable;

public class MancalaGame implements Serializable{

    private static final long serialVersionUID = 1000004;
    final private ArrayList<Player> playerList = new ArrayList<>();
    private Player currPlayer;
    private int playerNum;
    private GameRules gameBoard;
    private int numStones;
    private boolean repeatTurn;
    
    public MancalaGame(){
        numStones = 0;
        repeatTurn = false;
    }

    //set players of game
    public void setPlayers(final Player onePlayer, final Player twoPlayer){
        
        playerList.add(onePlayer);
        playerList.add(twoPlayer);
        gameBoard.registerPlayers(onePlayer,twoPlayer);
    }

    //get players of game
    public ArrayList<Player> getPlayers(){
        return playerList;
    }

    public void setCurrentPlayer(final Player player){
        currPlayer = player;
        if (player == playerList.get(0)){
            playerNum = 1;
        }else{
            playerNum = 2;
        }
    }

    //get current player
    public Player getCurrentPlayer(){
        return currPlayer;
    }

    //set board for game -- change to use gameRules as board no longer exists
    public void setBoard(final GameRules theBoard){
        this.gameBoard = theBoard;
        gameBoard.setGame(this);
    }

    //get board for game -- change to use gameRules as board no longer exists
    public GameRules getBoard(){
        return gameBoard;
    }

    //return num of stones in a pit
    public int getNumStones(final int pitNum)
                 throws PitNotFoundException{
        
        if (pitNum-1 < 0 || pitNum-1 > 12) {

            throw new PitNotFoundException("Invalid pit number");
        }

        return gameBoard.getNumStones(pitNum-1);
    }

    public int move(final int startPit) throws InvalidMoveException {
        final int moveReturn = performMove(startPit);
        updateNumStones(startPit);
        updateCurrentPlayer();
        return numStones;
    }

    private int performMove(final int startPit) {
        int moveReturn = 0;
        try {
            moveReturn = gameBoard.moveStones(startPit, playerNum);
        } catch (InvalidMoveException e) {
            System.err.println("Invalid move: " + e.getMessage());
        }
        repeatTurn = gameBoard.getRepeatTurn();
        return moveReturn;
    }

    private void updateNumStones(final int startPit) {
        numStones = 0;
        final int startRange = startPit < 7 ? 1 : 7;
        final int endRange = startPit < 7 ? 7 : 13;

        for (int i = startRange; i < endRange; i++) {
            numStones += gameBoard.getNumStones(i);
        }
    }

    private void updateCurrentPlayer() {
        if (!repeatTurn) {
            final Player otherPlayer = getCurrentPlayer().equals(playerList.get(0)) ? playerList.get(1) : playerList.get(0);
            setCurrentPlayer(otherPlayer);
        }

    }


    //return num of stones in a player's store
    public int getStoreCount(final Player player)
                  throws NoSuchPlayerException{

        if (!playerList.contains(player)){
            throw new NoSuchPlayerException("Invalid player input");
        }
        return player.getStoreCount();
    }

    //checks if game is over by examining sides
    public boolean isGameOver(){
        boolean gameOverVal = false;
        if (gameBoard.isSideEmpty(1) || gameBoard.isSideEmpty(7)){
            gameOverVal = true;
        }
        return gameOverVal;
        
    }
    
    //returns winner
    public Player getWinner() throws GameNotOverException {
        if (!isGameOver()) {
            throw new GameNotOverException("Game is not over");
        }

        return determineWinner();
    }

    private Player determineWinner() {
        final Player player1 = playerList.get(0);
        final Player player2 = playerList.get(1);

        final int playerOneStore = player1.getStoreCount();
        final int playerTwoStore = player2.getStoreCount();

        return playerOneStore != playerTwoStore ? playerOneStore > playerTwoStore ? player1 : player2 : new Player();
    }



    //resets board to start a new game
    public void startNewGame(){
        gameBoard.resetBoard();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Current Board State:\n");

        appendPlayerInfo(builder, "Player 1", 0, 5, 0);
        appendPlayerInfo(builder, "Player 2", 6, 11, 1);

        return builder.toString();
    }

    private void appendPlayerInfo(final StringBuilder builder, final String playerLabel, final int startIndex, final int endIndex, final int playerIndex) {
        builder.append("\n").append(playerLabel).append(":\n");
        
        for (int i = startIndex; i <= endIndex; i++) {
            builder.append(String.format("Pit %2d: ", i + 1)).append(gameBoard.getNumStones(i+1)).append("\n");
        }
        
        builder.append("(Store: ").append(getPlayerStoreCount(playerIndex)).append(" stones)\n");
    }

    private int getPlayerStoreCount(final int playerIndex) {
        return playerList.get(playerIndex).getStoreCount();
    }

}
