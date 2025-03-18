import java.util.Scanner;

public class Game {
    private Player player1;
    private AIPlayer aiPlayer;
    private Player currentPlayer;
    private Player opponent;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        System.out.print("Enter board size: ");
        int boardSize = scanner.nextInt();
        player1 = new Player("Player 1", boardSize);
        aiPlayer = new AIPlayer("AI", boardSize);
        currentPlayer = player1;
        opponent = aiPlayer;
    }

    public void start() {
        boolean playAgain;
        do {
            System.out.println("Welcome to Battleship!");
            setupRandomShips(player1);
            setupRandomShips(aiPlayer);
            playGame();
            playAgain = askReplay();
        } while (playAgain);
    }

    private boolean askReplay() {
        System.out.println("Play again? (yes/no)");
        return scanner.next().equalsIgnoreCase("yes");
    }

    private void setupRandomShips(Player player) {
        player.setupRandomShips();
    }

    private void playGame() {
        while (true) {
            System.out.println(currentPlayer.getName() + "'s turn.");
            Utils.displayBoard(currentPlayer.getBoard(), false);
            Utils.displayPlayerStatus(opponent);

            Coordinate coordinate;
            if (currentPlayer instanceof AIPlayer) {
                coordinate = ((AIPlayer) currentPlayer).makeMove();
                System.out.println("AI chooses: " + coordinate);
            } else {
                coordinate = Utils.getCoordinateInput();
            }

            if (!coordinate.isInBounds(currentPlayer.getBoard().getSize())) {
                System.out.println("Coordinate out of bounds. Please try again.");
                continue;
            }

            boolean hit = currentPlayer.attack(opponent, coordinate);

            if (hit) {
                if (checkWinCondition(opponent)) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    break;
                }
            }

            switchPlayers();
        }
    }

    private boolean checkWinCondition(Player opponent) {
        for (Ship ship : opponent.getShips()) {
            if (ship != null && !ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    private void switchPlayers() {
        Player temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}