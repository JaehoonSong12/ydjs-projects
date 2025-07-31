/**
INSTRUCTIONS: 
    This is a Java Swing application that 

COMPILE & EXECUTE & CLEANUP (Java):

     javac  -d out              SwingApp*.java
     java           -cp out     SwingApp
     rm -rf out/
     












 */


import java.util.Random;

import javax.swing.*;
import java.awt.*;
// import java.util.*;

 



// -------------------------------
// App: holds a single standalone-instance like 
// 1. "window" in OS, 
// 2. "browser" in Web, 
// 3. "stage" in Other Frameworks like "JavaFX"
// -------------------------------
public class SwingApp {
    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
        // SwingUtilities.invokeLater(() -> {
        //     frame = new JFrame("Swing Template App");
            


        //     // Create initial MenuView and attach controller.
        //     setView(new MenuController(new MenuView()).getView());
            



        //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //     frame.setSize(400, 300);
        //     frame.setLocationRelativeTo(null);
        //     frame.setVisible(true);
        // });
    }
    
    // Method to transition views.
    public static void setView(JPanel view) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(view);
        frame.revalidate();
        frame.repaint();
    }
}










////////////////////// Backend

// -------------------------------
// Model: Represents User Data
// -------------------------------
class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }
}













////////////////////// Frontend

// -------------------------------
// View: Menu Panel
// -------------------------------
class MenuView extends JPanel {
    private JTextField usernameField;
    private JButton game1Button, game2Button, game3Button, randomGameButton ; // jayden addition

    public MenuView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Enter Username:"));
        usernameField = new JTextField(20);
        add(usernameField);
        add(Box.createRigidArea(new Dimension(0, 10)));

        game1Button = new JButton("TicTacToe");
        game2Button = new JButton("Game 2");
        game3Button = new JButton("Game 3");
        randomGameButton = new JButton("Random"); // jayden addition

        add(game1Button);
        add(game2Button);
        add(game3Button);
        add(randomGameButton); // jayden addition
    }

    // Getters for the components
    public String getUsername() {
        return usernameField.getText().trim();
    }

    public JButton getGame1Button() {
        return game1Button;
    }

    public JButton getGame2Button() {
        return game2Button;
    }

    public JButton getGame3Button() {
        return game3Button;
    }

    public JButton getRandomGameButton() { // jayden addition
        return randomGameButton;
    }
}

// -------------------------------
// Controller: Menu
// -------------------------------
class MenuController {
    private MenuView view;

    public MenuController(MenuView view) {
        this.view = view;
        view.getGame1Button().addActionListener(e -> launchGame("TicTacTeo"));
        view.getGame2Button().addActionListener(e -> launchGame("Game 2"));
        view.getGame3Button().addActionListener(e -> launchGame("Game 3"));
        view.getRandomGameButton().addActionListener(e -> RandomPick());
    }

    private void RandomPick() {
        String[] games = { "TicTacTeo", "Game 2", "Game 3" };
        Random random = new Random();
        String randomGame = games[random.nextInt(games.length)]; // Pick random game
        launchGame(randomGame);
    }


    private void launchGame(String gameName) {
        String username = view.getUsername();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a username.");
            return;
        }
        User user = new User(username);

        switch (gameName) {
            case "TicTacTeo": SwingApp.setView(new Game1Controller(user).getView()); break;
            case "Game 2": SwingApp.setView(new Game2Controller(user).getView()); break;
            case "Game 3": SwingApp.setView(new Game3Controller(user).getView()); break;
        }
    }

    

    public MenuView getView() {
        return view;
    }
}

























// -------------------------------
// View: Game 1 - TicTacToe Panel
// -------------------------------
class Game1View extends JPanel {
    private JLabel welcomeLabel, gameName;
    private JButton backButton, easyBtn, mediumBtn, hardBtn;

    public Game1View() {
        setLayout(new GridLayout(6, 1, 10, 10));
        setBackground(Color.DARK_GRAY);
        // Layout (HTML like) Operation
        welcomeLabel = new JLabel("", SwingConstants.CENTER);
        gameName = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
        gameName.setForeground(Color.WHITE);
        gameName.setFont(new Font("Arial", Font.BOLD, 36));
        easyBtn = new JButton("Easy AI");
        mediumBtn = new JButton("Medium AI");
        hardBtn = new JButton("Hard AI");
        backButton = new JButton("Back to Menu");
        add(welcomeLabel);
        add(gameName);
        add(easyBtn);
        add(mediumBtn);
        add(hardBtn);
        add(backButton);

        // Styling (CSS like) Operation
        styleButton(easyBtn);
        styleButton(mediumBtn);
        styleButton(hardBtn);
    }

    public void setWelcomeLabel(String message) {
        welcomeLabel.setText(message);
        welcomeLabel.setForeground(Color.WHITE);

    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getEasyBtn() {
        return easyBtn;
    }
    public JButton getMediumBtn() {
        return mediumBtn;
    }
    public JButton getHardBtn() {
        return hardBtn;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
    }
}

// -------------------------------
// Controller: Game 1
// -------------------------------
class Game1Controller {
    private Game1View view;
    private User user;
    private MainFrame frame;

    public Game1Controller(User user) {
        this.user = user;
        view = new Game1View();
        view.setWelcomeLabel("Welcome to Game 1, " + user.getUsername() + "!");
        initController();
    }

    private void initController() {
        view.getBackButton().addActionListener(e -> SwingApp.setView(new MenuController(new MenuView()).getView()));
        view.getEasyBtn().addActionListener(e -> {
            // System.out.println("Easy mode start");
            frame.startGame("Easy");
        });
        view.getMediumBtn().addActionListener(e -> {
            // System.out.println("Medium mode start");
            frame.startGame("Medium");
        });
        view.getHardBtn().addActionListener(e -> {
            // System.out.println("Hard mode start");
            frame.startGame("Hard");
        });
    }


    public Game1View getView() {
        return view;
    }
}










// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// Jayden - TicTacToe
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
class AIPlayer {
    private String difficulty;
    private Random random = new Random();

    public AIPlayer(String difficulty) {
        this.difficulty = difficulty;
    }

    // For simplicity, all difficulties select a random available move.
    // (You can expand this to add smarter logic for "Medium" or "Hard".)
    public int[] getMove(TicTacToeModel model) {
        java.util.List<int[]> moves = new java.util.ArrayList<>();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (!model.isOccupied(r, c)) {
                    moves.add(new int[]{r, c});
                }
            }
        }
        if (moves.isEmpty()) return null;
        return moves.get(random.nextInt(moves.size()));
    }
}


class TicTacToeController {
    private TicTacToeModel model;
    private TicTacToeView view;
    private MainFrame frame;
    private AIPlayer ai;

    public TicTacToeController(TicTacToeModel model, TicTacToeView view, MainFrame frame, AIPlayer ai) {
        this.model = model;
        this.view = view;
        this.frame = frame;
        this.ai = ai;

        // Set up listeners on each board tile.
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final int row = r, col = c;
                view.getTile(r, c).addActionListener(e -> handleMove(row, col));
            }
        }
    }

    private void handleMove(int r, int c) {
        // Human move (assume human is always "X")
        if (!model.isOccupied(r, c) && model.getCurrentPlayer().equals(TicTacToeModel.PLAYER_X)) {
            if (model.makeMove(r, c)) {
                view.updateBoard(r, c, TicTacToeModel.PLAYER_X);
                if (model.checkWin()) {
                    view.setStatus("You win!");
                    view.showMessage("Congratulations! You win!");
                    frame.showGameOver("You Win!");
                    return;
                }
                if (model.isBoardFull()) {
                    view.setStatus("It's a tie!");
                    view.showMessage("It's a tie!");
                    frame.showGameOver("It's a Tie!");
                    return;
                }
                model.switchPlayer();
                view.setStatus("AI's Turn");
                doAIMove();
            }
        }
    }

    private void doAIMove() {
        SwingUtilities.invokeLater(() -> {
            int[] move = ai.getMove(model);
            if (move != null && !model.isOccupied(move[0], move[1])) {
                model.makeMove(move[0], move[1]);
                view.updateBoard(move[0], move[1], TicTacToeModel.PLAYER_O);
                if (model.checkWin()) {
                    view.setStatus("AI wins!");
                    view.showMessage("You lose!");
                    frame.showGameOver("You Lose!");
                    return;
                }
                if (model.isBoardFull()) {
                    view.setStatus("It's a tie!");
                    view.showMessage("It's a tie!");
                    frame.showGameOver("It's a Tie!");
                    return;
                }
                model.switchPlayer();
                view.setStatus("Your Turn");
            }
        });
    }
}
class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private TicTacToeMenuView menuView;
    private TicTacToeView gameView;
    private TicTacToeGameOverView gameOverView;
    private TicTacToeController controller;

    public MainFrame() {
        setTitle("Tic-Tac-Toe");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        menuView = new TicTacToeMenuView(this);
        gameOverView = new TicTacToeGameOverView(this);

        mainPanel.add(menuView, "menu");
        mainPanel.add(gameOverView, "gameOver");

        add(mainPanel);
        setVisible(true);

        showMenu();
    }

    // Start a new game with the selected difficulty ("Easy", "Medium", or "Hard")
    public void startGame(String difficulty) {
        TicTacToeModel model = new TicTacToeModel();
        AIPlayer ai = new AIPlayer(difficulty);
        gameView = new TicTacToeView();
        controller = new TicTacToeController(model, gameView, this, ai);
        mainPanel.add(gameView, "game");
        cardLayout.show(mainPanel, "game");
    }

    public void showMenu() {
        cardLayout.show(mainPanel, "menu");
    }

    // Show the game over screen with a custom message
    public void showGameOver(String message) {
        gameOverView.setMessage(message);
        cardLayout.show(mainPanel, "gameOver");
    }
}

class TicTacToeGameOverView extends JPanel {
    private JLabel messageLabel;
    private JButton restartButton;
    private JButton menuButton;
    private MainFrame frame;

    public TicTacToeGameOverView(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.BLACK);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 50));
        messageLabel.setForeground(Color.RED);
        add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBackground(Color.BLACK);

        restartButton = new JButton("Restart");
        menuButton = new JButton("Main Menu");

        styleButton(restartButton);
        styleButton(menuButton);

        // Restart with a default difficulty (e.g. "Medium")
        restartButton.addActionListener(e -> frame.startGame("Medium"));
        menuButton.addActionListener(e -> frame.showMenu());

        buttonPanel.add(restartButton);
        buttonPanel.add(menuButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.RED);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.DARK_GRAY);
            }
        });
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
        animateLossEffect();
    }


    private static int blinkingCount = 4;
    private static int blinkingSpeed = 500; // in milli-second

    private void animateLossEffect() {
        new Thread(() -> {
            try {
                for (int i = 0; i < blinkingCount; i++) {
                    messageLabel.setForeground(Color.BLACK);
                    Thread.sleep(blinkingSpeed);
                    messageLabel.setForeground(Color.RED);
                    Thread.sleep(blinkingSpeed);
                }
            } catch (InterruptedException ignored) {
            }
        }).start();
    }
}


class TicTacToeMenuView extends JPanel {
    public TicTacToeMenuView(MainFrame frame) {
        setLayout(new GridLayout(4, 1, 10, 10));
        setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);

        JButton easyBtn = new JButton("Easy AI");
        JButton mediumBtn = new JButton("Medium AI");
        JButton hardBtn = new JButton("Hard AI");

        styleButton(easyBtn);
        styleButton(mediumBtn);
        styleButton(hardBtn);

        easyBtn.addActionListener(e -> frame.startGame("Easy"));
        mediumBtn.addActionListener(e -> frame.startGame("Medium"));
        hardBtn.addActionListener(e -> frame.startGame("Hard"));

        add(title);
        add(easyBtn);
        add(mediumBtn);
        add(hardBtn);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
    }
}
class TicTacToeModel {
    private String[][] board;
    private String currentPlayer;

    public static final String PLAYER_X = "X";
    public static final String PLAYER_O = "O";

    public TicTacToeModel() {
        board = new String[3][3];
        currentPlayer = PLAYER_X;
    }

    public boolean makeMove(int r, int c) {
        if (board[r][c] == null) {
            board[r][c] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]))
                return true;
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]))
                return true;
        }
        // Check diagonals
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))
            return true;
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))
            return true;
        return false;
    }

    public boolean isBoardFull() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == null)
                    return false;
        return true;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer.equals(PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean isOccupied(int r, int c) {
        return board[r][c] != null;
    }
}

class TicTacToeView extends JPanel {
    private JLabel statusLabel;
    private JButton[][] board = new JButton[3][3];

    public TicTacToeView() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        statusLabel = new JLabel("Your Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 28));
        statusLabel.setForeground(Color.CYAN);
        add(statusLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardPanel.setBackground(Color.BLACK);
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = new JButton();
                styleButton(board[r][c]);
                boardPanel.add(board[r][c]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 80));
        button.setBackground(new Color(30, 30, 30));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 50, 50));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 30, 30));
            }
        });
    }

    public JButton getTile(int r, int c) {
        return board[r][c];
    }

    public void updateBoard(int r, int c, String text) {
        board[r][c].setText(text);
    }

    public void setStatus(String text) {
        statusLabel.setText(text);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}



// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------












































// -------------------------------
// View: Game 2 Panel
// -------------------------------
class Game2View extends JPanel {
    private JLabel welcomeLabel;
    private JButton backButton;

    public Game2View() {
        setLayout(new BorderLayout());
        welcomeLabel = new JLabel("", SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);
        
        backButton = new JButton("Back to Menu");
        add(backButton, BorderLayout.SOUTH);
    }

    public void setWelcomeLabel(String message) {
        welcomeLabel.setText(message);
    }

    public JButton getBackButton() {
        return backButton;
    }
}

// -------------------------------
// Controller: Game 2
// -------------------------------
class Game2Controller {
    private Game2View view;
    private User user;

    public Game2Controller(User user) {
        this.user = user;
        view = new Game2View();
        view.setWelcomeLabel("Welcome to Game 2, " + user.getUsername() + "!");
        initController();
    }

    private void initController() {
        view.getBackButton().addActionListener(e -> SwingApp.setView(new MenuController(new MenuView()).getView()));
    }

    public Game2View getView() {
        return view;
    }
}





















// -------------------------------
// View: Game 3 Panel
// -------------------------------
class Game3View extends JPanel {
    private JLabel welcomeLabel;
    private JButton backButton;

    public Game3View() {
        setLayout(new BorderLayout());
        welcomeLabel = new JLabel("", SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);
        
        backButton = new JButton("Back to Menu");
        add(backButton, BorderLayout.SOUTH);
    }

    public void setWelcomeLabel(String message) {
        welcomeLabel.setText(message);
    }

    public JButton getBackButton() {
        return backButton;
    }
}

// -------------------------------
// Controller: Game 3
// -------------------------------

class Game3Controller {
    private Game3View view;
    private User user;

    public Game3Controller(User user) {
        this.user = user;
        view = new Game3View();
        view.setWelcomeLabel("Welcome to Game 3, " + user.getUsername() + "!");
        initController();
    }

    private void initController() {
        view.getBackButton().addActionListener(e -> SwingApp.setView(new MenuController(new MenuView()).getView()));
    }

    public Game3View getView() {
        return view;
    }
}





















