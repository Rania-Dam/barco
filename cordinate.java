package prueba;

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

    public static Coordinate parseInput(String input) {
        if (input.length() != 2) return null;
        char rowChar = input.charAt(0);
        char colChar = input.charAt(1);
        int row = rowChar - 'A';
        int col = colChar - '0';
        if (row < 0 || row >= 10 || col < 0 || col >= 10) {
            return null;
        }
        return new Coordinate(row, col);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinate that = (Coordinate) obj;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }
}
