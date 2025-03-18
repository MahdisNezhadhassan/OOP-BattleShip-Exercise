import java.util.Scanner;

public class Utils {
    private static Scanner scanner = new Scanner(System.in);

    public static Coordinate getCoordinateInput() {
        while (true) {
            System.out.print("Enter row and column to attack (e.g., A1): ");
            String input = scanner.next();
            if (Coordinate.isValidCoordinate(input)) {
                return Coordinate.fromString(input);
            } else {
                System.out.println("Invalid coordinate. Please try again.");
            }
        }
    }

    public static void displayBoard(Board board, boolean revealShips) {
        board.printBoard(revealShips);
    }

    public static void updateGameStatus(Player currentPlayer, boolean hit) {
        if (hit) {
            System.out.println("Hit!");
        } else {
            System.out.println("Miss!");
        }
    }

    public static void displayError(String message) {
        System.out.println("Error: " + message);
    }

    public static void displayPlayerStatus(Player player) {
        System.out.println(player.getName() + "'s Ships Status:");
        for (Ship ship : player.getShips()) {
            System.out.println(ship.getStatus());
        }
    }
}