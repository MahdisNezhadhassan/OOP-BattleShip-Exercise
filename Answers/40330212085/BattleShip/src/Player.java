import java.util.Random;

public class Player {
    private String name;
    private Board board;
    private Ship[] ships;
    private int shipCount;

    public Player(String name, int boardSize) {
        this.name = name;
        this.board = new Board(boardSize);
        this.ships = new Ship[4];
        this.shipCount = 0;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public boolean addShip(Ship ship, Coordinate coordinate, boolean horizontal) {
        if (board.placeShip(ship, coordinate, horizontal)) {
            if (shipCount < ships.length) {
                ships[shipCount] = ship;
                shipCount++;
            }
            return true;
        }
        return false;
    }

    public void setupRandomShips() {
        Random random = new Random();
        addShipWithRetry(new Ship("Aircraft Carrier", 5), random);
        addShipWithRetry(new Ship("Battleship", 4), random);
        addShipWithRetry(new Ship("Submarine", 3), random);
        addShipWithRetry(new Ship("Patrol Boat", 2), random);
    }

    private void addShipWithRetry(Ship ship, Random random) {
        boolean placed = false;
        while (!placed) {
            int row = random.nextInt(board.getSize());
            int col = random.nextInt(board.getSize());
            boolean horizontal = random.nextBoolean();
            Coordinate coordinate = new Coordinate(row, col);
            placed = addShip(ship, coordinate, horizontal);
        }
    }

    public boolean attack(Player opponent, Coordinate coordinate) {
        boolean hit = opponent.getBoard().hit(coordinate);
        if (hit) {
            System.out.println("Hit!");
            for (int i = 0; i < shipCount; i++) {
                Ship ship = ships[i];
                if (ship != null && ship.isHit(coordinate)) {
                    ship.hit();
                    if (ship.isSunk()) {
                        System.out.println(ship.getName() + " has been sunk!");
                    }
                }
            }
        } else {
            System.out.println("Miss!");
        }
        return hit;
    }

    public Ship[] getShips() {
        return ships;
    }
}