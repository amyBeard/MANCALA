package ui;
import mancala.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import javax.swing.*;
import java.nio.file.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MancalaUI extends JFrame {

    private MancalaGame game;
    private GameRules board;
    private UserProfile userProfile1;
    private UserProfile userProfile2;
    private Player player1;
    private Player player2;
    private Scanner userIn;

    private JPanel mainPanel;
    private JPanel gameBoardPanel;
    private JLabel titleLabel;
    private JButton newGameButton;
    private JButton mancalaButton;
    private JButton exitToMainButton;

    private JButton[] player1Pits;
    private JButton[] player2Pits;
    private JButton player1Store;
    private JButton player2Store;
    private JPanel winnerPanel;
    private JButton loadButton;
    private Path assetsFolderPath;
    private String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    public MancalaUI(String title) {
        super(title);
        setupUI();
    }


    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createMenuBar();

        titleLabel = new JLabel("MANCALA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(createButtonPanel(), BorderLayout.CENTER);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        exitToMainButton = new JButton("Exit to Main Page");
        exitToMainButton.addActionListener((ActionEvent e) -> exitToMainPage());
        menuBar.add(exitToMainButton);

        setJMenuBar(menuBar);
    }
    private void loadFromFile() {
        getAssetsPath();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(assetsFolderPath.toFile());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized files", "ser");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                Object loadedObject = Saver.loadObject(selectedFile.getAbsolutePath());
                
                if (loadedObject instanceof MancalaGame) {
                    game = (MancalaGame) loadedObject;
                    /*board = game.getBoard();
                    game.setBoard(board);
                    userProfile1 = new UserProfile("Player1");
                    userProfile2 = new UserProfile("Player2");
                    player1 = new Player(userProfile1);
                    player2 = new Player(userProfile2);
                    game.setPlayers(player1, player2);
                    updatePitButtons();
                    updateStoreDisplay();
                    playMancala();*/
                    JOptionPane.showMessageDialog(this, "Game Loaded, However Functionality is Incomplete.", "Load Game", JOptionPane.INFORMATION_MESSAGE);

                } else if (loadedObject instanceof UserProfile) {
                    UserProfile loadedProfile = (UserProfile) loadedObject;
                    // Handle loading player profile
                    // For example, you can update the UI to display the loaded user profile
                    JOptionPane.showMessageDialog(this, "Player Profile Loaded, However Functionality is Incomplete.", "Load Player", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid file type.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

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
        
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        newGameButton = new JButton("New Game");
        loadButton = new JButton("Load Game/Player");

        newGameButton.addActionListener((ActionEvent e) -> startNewGame());
        loadButton.addActionListener((ActionEvent e) -> loadFromFile());

        buttonPanel.add(newGameButton);
        buttonPanel.add(loadButton);

        return buttonPanel;
    }

    private void startNewGame() {

        String[] options = {"AyoAyo", "Kalah"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Choose the game type:",
                "New Game",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                board = new AyoRules();
                break;
            case 1:
                board = new KalahRules();
                break;
            default:
                // Default to Kalah if the user closes the dialog
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
        playMancala();
    }

    private void playMancala() {
        if (game == null) {
            JOptionPane.showMessageDialog(this, "Please start a new game first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        gameBoardPanel = createGameBoardPanel();
        mainPanel.remove(1);
        mainPanel.add(gameBoardPanel, BorderLayout.CENTER);
        updateTitleLabel();
        revalidate();
        repaint();
    }

    private void updateTitleLabel() {
        titleLabel.setText("Current Turn: " + game.getCurrentPlayer().toString());
    }

    private JPanel createGameBoardPanel() {
        JPanel boardPanel = new JPanel(new GridLayout(2, 7));

        player1Pits = new JButton[6];
        player2Pits = new JButton[6];

        player1Store = new JButton("Store 1\n" + "(" + board.getStoreCount(1) + ")");
        player1Store.setEnabled(false);
        boardPanel.add(player1Store);
    
        for (int i = 0; i < 6; i++) {
            final int pitIndex1 = 6 - i;
            player1Pits[i] = new JButton("Pit " + pitIndex1 + "\n(" + board.getNumStones(pitIndex1) + ")");
            player1Pits[i].addActionListener((ActionEvent e) -> {
                handlePitButtonClick(pitIndex1);
            });
            boardPanel.add(player1Pits[i]);
        }
        boardPanel.add(new JLabel());
        boardPanel.add(new JLabel());

        for (int i = 0; i < 6; i++) {
            final int pitIndex2 = i + 7;
            player2Pits[i] = new JButton("Pit " + pitIndex2 + "\n(" + board.getNumStones(pitIndex2) + ")");
            player2Pits[i].addActionListener((ActionEvent e) -> {
                handlePitButtonClick(pitIndex2);
            });
            boardPanel.add(player2Pits[i]);
        }

        player2Store = new JButton("Store 2\n" + "(" + board.getStoreCount(2) + ")");
        player2Store.setEnabled(false);
        boardPanel.add(player2Store);

        return boardPanel;
    }



    private void handlePitButtonClick(int pitNumber) {
        try {
            
            getAssetsPath();
            String gameFileName = assetsFolderPath + "/saved_game_" + timestamp + ".ser";
            String userProfile1FileName = assetsFolderPath + "/user_profile1_" + timestamp + ".ser";
            String userProfile2FileName = assetsFolderPath + "/user_profile2_" + timestamp + ".ser";

            game.move(pitNumber);

            Saver.saveObject(game, gameFileName);
            Saver.saveObject(userProfile1, userProfile1FileName);
            Saver.saveObject(userProfile2, userProfile2FileName);
        } catch (InvalidMoveException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Error saving the game/players: " + e.getMessage());
        }
        updateTitleLabel();

        if (game.isGameOver()) {
            showWinnerPage();
        } else {
            updatePitButtons();
            updateStoreDisplay();
        }
    }

    private void showWinnerPage() {
        winnerPanel = new JPanel();
        winnerPanel.setLayout(new BorderLayout());

        String winnerText;
        try {
            if (game.getWinner() != null) {
                winnerText = "Winner: " + game.getWinner();
            } else {
                winnerText = "Tie";
            }
        } catch (GameNotOverException e) {
            winnerText = "Error determining winner";
        }

        JLabel winnerLabel = new JLabel(winnerText, SwingConstants.CENTER);
        winnerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        JButton returnHomeButton = new JButton("Return to Home Page");
        JButton resetBoardButton = new JButton("Reset Board");

        returnHomeButton.addActionListener((ActionEvent e) -> returnToHomePage());
        resetBoardButton.addActionListener((ActionEvent e) -> resetBoard());

        winnerPanel.add(winnerLabel, BorderLayout.NORTH);
        winnerPanel.add(returnHomeButton, BorderLayout.CENTER);
        winnerPanel.add(resetBoardButton, BorderLayout.SOUTH);

        mainPanel.remove(1);
        mainPanel.add(winnerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void returnToHomePage() {
        game = null;
        mainPanel.remove(1);
        mainPanel.add(createButtonPanel(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void resetBoard() {
        
        board.resetBoard();
        game.setPlayers(player1, player2);
        game.setCurrentPlayer(player1);
        mainPanel.remove(1);
        mainPanel.add(createGameBoardPanel(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void updatePitButtons() {
        for (int i = 0; i < 6; i++) {
            player1Pits[i].setText("Pit " + (6 - i) + "\n(" + board.getNumStones(6 - i) + ")");
            player2Pits[i].setText("Pit " + (i + 7) + "\n(" + board.getNumStones(i + 7) + ")");
        }

    }

    private void updateStoreDisplay(){
        player1Store.setText("Store 1\n" +  "(" + board.getStoreCount(1) +")");
        player2Store.setText("Store 2\n" +  "(" + board.getStoreCount(2) +")");
    }

    private void exitToMainPage() {
        mainPanel.remove(1);
        mainPanel.add(createButtonPanel(), BorderLayout.CENTER);
        titleLabel.setText("MANCALA");
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MancalaUI gameUI = new MancalaUI("Mancala Game");
            gameUI.setVisible(true);
        });
    }
}
