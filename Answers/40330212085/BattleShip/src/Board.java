public class Board {
    private char[][] grid;
    private int size;

    public Board(int size) {
        this.size = size;
        this.grid = new char[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '~';
            }
        }
    }

    public boolean placeShip(Ship ship, Coordinate coordinate, boolean horizontal) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        if (canPlace(ship, row, col, horizontal)) {
            for (int i = 0; i < ship.getSize(); i++) {
                if (horizontal) {
                    grid[row][col + i] = 'S';
                } else {
                    grid[row + i][col] = 'S';
                }
            }
            return true;
        }
        return false;
    }

    private boolean canPlace(Ship ship, int row, int col, boolean horizontal) {
        if (horizontal) {
            if (col + ship.getSize() > size)
                return false;
            for (int i = 0; i < ship.getSize(); i++) {
                if (grid[row][col + i] != '~')
                    return false;
            }
        } else {
            if (row + ship.getSize() > size)
                return false;
            for (int i = 0; i < ship.getSize(); i++) {
                if (grid[row + i][col] != '~')
                    return false;
            }
        }
        return true;
    }

    public boolean hit(Coordinate coordinate) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        if (grid[row][col] == 'S') {
            grid[row][col] = 'H';
            return true; // برخورد
        } else {
            grid[row][col] = 'M';
            return false;
        }
    }

    public void printBoard(boolean revealShips) {
        System.out.print("  ");
        for (char c = 'A'; c < 'A' + size; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < size; j++) {
                if (revealShips) {
                    System.out.print(grid[i][j] + " ");
                } else {
                    if (grid[i][j] == 'S') {
                        System.out.print("~ ");
                    } else {
                        System.out.print(grid[i][j] + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }
}