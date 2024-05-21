package barcos;

public class Board {
    private char[][] grid;

    public Board(int size) {
        grid = new char[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public void placeShip(Ship ship) {
        for (Coordinate coord : ship.getPositions()) {
            grid[coord.getRow()][coord.getCol()] = 'L';
        }
    }

    public boolean isPlacementValid(Ship ship) {
        for (Coordinate coord : ship.getPositions()) {
            if (grid[coord.getRow()][coord.getCol()] != '-') {
                return false;
            }
        }
        return true;
    }

    public void updateBoard(Coordinate coord, char symbol) {
        grid[coord.getRow()][coord.getCol()] = symbol;
    }

    public char getPosition(Coordinate coord) {
        return grid[coord.getRow()][coord.getCol()];
    }

    public void display() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}


}