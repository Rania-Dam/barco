package prueba;

import java.util.Scanner;

public class Game {
    private Board hiddenBoard;
    private Board visibleBoard;
    private int remainingAttempts;

    public Game() {
        hiddenBoard = new Board(10, 10);
        visibleBoard = new Board(10, 10);
        remainingAttempts = 50;
        hiddenBoard.placeShips();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (remainingAttempts > 0) {
            visibleBoard.display();
            System.out.println("Remaining attempts: " + remainingAttempts);
            System.out.print("Enter your shot (e.g., A5): ");
            String input = scanner.nextLine();
            Coordinate coord = Coordinate.parseInput(input);
            if (coord == null) {
                System.out.println("Invalid input. Try again.");
                continue;
            }
            char result = hiddenBoard.shootAt(coord);
            if (result == 'L') {
                System.out.println("Hit!");
                visibleBoard.markHit(coord);
            } else {
                System.out.println("Miss!");
                visibleBoard.markMiss(coord);
            }
            remainingAttempts--;
            if (hiddenBoard.allShipsSunk()) {
                System.out.println("You won!");
                break;
            }
        }
        if (remainingAttempts == 0) {
            System.out.println("You lost!");
        }
        hiddenBoard.display();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
