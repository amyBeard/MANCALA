package ui;

import mancala.*;
import java.util.Scanner;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.*;

public class TextUI {
    private MancalaGame game;
    private Scanner userIn;
    private GameRules board;
    private UserProfile userProfile1;
    private UserProfile userProfile2;
    private Player player1;
    private Player player2;
    private String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    private Path assetsFolderPath;

    public TextUI() {
        userIn = new Scanner(System.in);
        initializeGame();
        playGame();
    }

    private void initializeGame() {

        
        /*try {
            // Load saved users for Player 1
            userProfile1 = (UserProfile) Saver.loadObject("user_profile1.ser");
            System.out.print("Use " + userProfile1.getUserName() + " for Player 1? (Y/N): ");
            String response = userIn.next();
            if (!response.equalsIgnoreCase("Y")) {
                userProfile1 = new UserProfile("Player1");
            }

            // Load saved users for Player 2
            userProfile2 = (UserProfile) Saver.loadObject("user_profile2.ser");
            System.out.print("Use " + userProfile2.getUserName() + " for Player 2? (Y/N): ");
            response = userIn.next();
            if (!response.equalsIgnoreCase("Y")) {
                userProfile2 = new UserProfile("Player2");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved users found. Creating new users.");
            userProfile1 = new UserProfile("Player1");
            userProfile2 = new UserProfile("Player2");
        }

        try {
            game = (MancalaGame) Saver.loadObject("saved_game.ser");
            System.out.print("Saved game exists. Would you like to resume the game? (Y/N): ");
            String response = userIn.next();
            if (response.equalsIgnoreCase("Y")) {
                System.out.println("Loaded saved game:");
            } else {
                game = new MancalaGame();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved game found. Creating a new game.");
            game = new MancalaGame();
        }*/

        System.out.print("Would you like to play Ayo or Kalah (Ayo/Kalah): ");
        String response = userIn.next();
        switch (response) {
            case "Ayo":
                board = new AyoRules();
                break;
            case "Kalah":
                board = new KalahRules();
                break;
            default:
                // Default to Kalah if the user closes the dialog
                System.out.println("Invalid input, defaulting to Kalah");
                board = new KalahRules();
                break;
        }

        game = new MancalaGame();
        game.setBoard(board);
        userProfile1 = new UserProfile("Player1");
        userProfile2 = new UserProfile("Player2");
        player1 = new Player(userProfile1);
        player2 = new Player(userProfile2);
        game.setPlayers(player1, player2);
        game.setCurrentPlayer(player1);

    }


    private void playGame() {
        try {
            while (!game.isGameOver()) {
                System.out.println(game.toString());
                System.out.print("\n" + game.getCurrentPlayer() + " Turn. Enter Pit Number: ");
                try {
                    game.move(userIn.nextInt());
                    getAssetsPath();
                    String gameFileName = assetsFolderPath + "/saved_game_" + timestamp + ".ser";
                    String userProfile1FileName = assetsFolderPath + "/user_profile1_" + timestamp + ".ser";
                    String userProfile2FileName = assetsFolderPath + "/user_profile2_" + timestamp + ".ser";

                } catch (InvalidMoveException e) {
                    System.err.println(e.getMessage());
                }
            }

            System.out.println(game.toString());

            try {
                if (game.getWinner() != null) {
                    System.out.println("Winner: " + game.getWinner());
                } else {
                    System.out.println("Tie");
                }
            } catch (GameNotOverException e) {
                System.err.println(e.getMessage());
            }


        } finally {
            userIn.close();
        }
    }

    private void getAssetsPath(){
        Path currentDirectory = Paths.get(System.getProperty("user.dir"));
        String folderName = "assets";
        assetsFolderPath = currentDirectory.resolve(folderName);

        if (!Files.exists(assetsFolderPath)) {
            try {
                Files.createDirectory(assetsFolderPath);
                System.out.println("Created 'assets' folder.");
            } catch (IOException e) {
                System.err.println("Error creating 'assets' folder: " + e.getMessage());
                return;
            }
        }
    }


    public static void main(String[] args) {
        new TextUI();
    }
}
