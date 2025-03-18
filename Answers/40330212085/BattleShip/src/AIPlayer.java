import java.util.Random;

public class AIPlayer extends Player {
    public AIPlayer(String name, int boardSize) {
        super(name, boardSize);
    }

    public Coordinate makeMove() {
        Random rand = new Random();
        int row = rand.nextInt(getBoard().getSize());
        int col = rand.nextInt(getBoard().getSize());
        return new Coordinate(row, col);
    }
}