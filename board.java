package prueba;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private char[][] board;
    private List<Ship> shipsList;

    public Board(int rows, int cols) {
        board = new char[rows][cols];
        shipsList = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void placeShips() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Ship ship;
            do {
                ship = new Ship();
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                ship.addCoordinate(new Coordinate(row, col));
            } while (!isPlacementValid(ship));
            placeShip(ship);
            shipsList.add(ship);
        }
    }

    private void placeShip(Ship ship) {
        for (Coordinate coord : ship.getCoordinates()) {
            board[coord.getRow()][coord.getCol()] = 'L';
        }
    }

    private boolean isPlacementValid(Ship ship) {
        for (Coordinate coord : ship.getCoordinates()) {
            if (board[coord.getRow()][coord.getCol()] != '-') {
                return false;
            }
        }
        return true;
    }

    public void display() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char shootAt(Coordinate coord) {
        return board[coord.getRow()][coord.getCol()];
    }

    public void markHit(Coordinate coord) {
        board[coord.getRow()][coord.getCol()] = 'X';
    }

    public void markMiss(Coordinate coord) {
        board[coord.getRow()][coord.getCol()] = 'A';
    }

    public boolean allShipsSunk() {
        for (Ship ship : shipsList) {
            if (!ship.isSunk(board)) {
                return false;
            }
        }
        return true;
    }
}
