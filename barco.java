package prueba;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<Coordinate> coordinates;

    public Ship() {
        coordinates = new ArrayList<>();
    }

    public void addCoordinate(Coordinate coord) {
        coordinates.add(coord);
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public boolean isSunk(char[][] board) {
        for (Coordinate coord : coordinates) {
            if (board[coord.getRow()][coord.getCol()] != 'X') {
                return false;
            }
        }
        return true;
    }
}
