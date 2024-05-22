import java.util.Scanner;
import java.util.Random;

public class Game {
    private Board hiddenBoard;
    private Board visibleBoard;
    private int remainingAttempts;
    private List<Ship> ships;

    public Game() {
        hiddenBoard = new Board(10);
        visibleBoard = new Board(10);
        remainingAttempts = 50;
        ships = new ArrayList<>();
        placeShips();
    }

    private void placeShips() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Ship ship = new Ship();
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            Coordinate coord = new Coordinate(row, col);
            ship.addPosition(coord);
            while (!hiddenBoard.isPlacementValid(ship)) {
                row = random.nextInt(10);
                col = random.nextInt(10);
                coord = new Coordinate(row, col);
                ship.addPosition(coord);
            }
            hiddenBoard.placeShip(ship);
            ships.add(ship);
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (remainingAttempts > 0) {
            visibleBoard.display();
            System.out.println("Remaining attempts: " + remainingAttempts);
            System.out.print("Enter your shot (e.g., A5): ");
            String input = scanner.nextLine();
            Coordinate coord = parseInput(input);
            if (coord == null) {
                System.out.println("Invalid input. Try again.");
                continue;
            }
            char result = hiddenBoard.getPosition(coord);
            if (result == 'L') {
                System.out.println("Tocado!");
                visibleBoard.updateBoard(coord, 'X');
                hiddenBoard.updateBoard(coord, 'X');
            } else {
                System.out.println("Agua!");
                visibleBoard.updateBoard(coord, 'A');
            }
            remainingAttempts--;
            if (allShipsSunk()) {
                System.out.println("¡Has ganado!");
                break;
            }
        }
        if (remainingAttempts == 0) {
            System.out.println("¡Has perdido!");
        }
        hiddenBoard.display();
    }

    private boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    private Coordinate parseInput(String input) {
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

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
