public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        char colChar = (char) ('A' + col);
        return colChar + String.valueOf(row);
    }

    public static boolean isValidCoordinate(String input) {
        if (input.length() != 2)
            return false;
        char colChar = input.charAt(0);
        int row = Character.getNumericValue(input.charAt(1));

        return (colChar >= 'A' && colChar <= 'J') && (row >= 0 && row <= 9);
    }

    public static Coordinate fromString(String input) {
        if (!isValidCoordinate(input)) {
            throw new IllegalArgumentException("Invalid coordinate format. Use format like 'A1'.");
        }

        char colChar = input.charAt(0);
        int row = Character.getNumericValue(input.charAt(1));
        int col = colChar - 'A';

        return new Coordinate(row, col);
    }

    public boolean isInBounds(int boardSize) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize;
    }
}
