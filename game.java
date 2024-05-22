import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Game {
    private Board hiddenBoard; // Tablero oculto donde se colocan los barcos.
    private Board visibleBoard; // Tablero visible para el jugador.
    private int remainingAttempts; // Número de intentos restantes para el jugador.
    private List<Ship> ships; // Lista de barcos en el juego.

    // Constructor de la clase Game que inicializa los tableros y coloca los barcos.
    public Game() {
        hiddenBoard = new Board(10); // Inicializa el tablero oculto con tamaño 10x10.
        visibleBoard = new Board(10); // Inicializa el tablero visible con tamaño 10x10.
        remainingAttempts = 50; // Establece el número inicial de intentos.
        ships = new ArrayList<>(); // Inicializa la lista de barcos.
        placeShips(); // Coloca los barcos en el tablero oculto.
    }

    // Método para colocar los barcos aleatoriamente en el tablero oculto.
    private void placeShips() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Ship ship = new Ship(); // Crea un nuevo barco.
            int row = random.nextInt(10); // Genera una fila aleatoria.
            int col = random.nextInt(10); // Genera una columna aleatoria.
            Coordinate coord = new Coordinate(row, col); // Crea una coordenada con los valores generados.
            ship.addPosition(coord); // Añade la coordenada al barco.
            // Mientras la colocación del barco no sea válida, genera nuevas coordenadas.
            while (!hiddenBoard.isPlacementValid(ship)) {
                row = random.nextInt(10);
                col = random.nextInt(10);
                coord = new Coordinate(row, col);
                ship.addPosition(coord);
            }
            hiddenBoard.placeShip(ship); // Coloca el barco en el tablero oculto.
            ships.add(ship); // Añade el barco a la lista de barcos.
        }
    }

    // Método para iniciar el juego.
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        // Bucle principal del juego que se ejecuta mientras queden intentos.
        while (remainingAttempts > 0) {
            visibleBoard.display(); // Muestra el tablero visible.
            System.out.println("Remaining attempts: " + remainingAttempts);
            System.out.print("Enter your shot (e.g., A5): ");
            String input = scanner.nextLine();
            Coordinate coord = parseInput(input); // Parsea la entrada del usuario.
            if (coord == null) {
                System.out.println("Invalid input. Try again.");
                continue; // Si la entrada es inválida, solicita otra entrada.
            }
            char result = hiddenBoard.getPosition(coord); // Obtiene el valor en la posición del tablero oculto.
            if (result == 'L') {
                System.out.println("Tocado!"); // Si hay un barco en la posición, es un golpe.
                visibleBoard.updateBoard(coord, 'X'); // Actualiza el tablero visible con 'X'.
                hiddenBoard.updateBoard(coord, 'X'); // Actualiza el tablero oculto con 'X'.
            } else {
                System.out.println("Agua!"); // Si no hay barco, es agua.
                visibleBoard.updateBoard(coord, 'A'); // Actualiza el tablero visible con 'A'.
            }
            remainingAttempts--; // Decrementa el número de intentos restantes.
            if (allShipsSunk()) {
                System.out.println("¡Has ganado!"); // Si todos los barcos están hundidos, el jugador gana.
                break;
            }
        }
        if (remainingAttempts == 0) {
            System.out.println("¡Has perdido!"); // Si se acaban los intentos, el jugador pierde.
        }
        hiddenBoard.display(); // Muestra el tablero oculto al final del juego.
    }

    // Método para verificar si todos los barcos están hundidos.
    private boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false; // Si algún barco no está hundido, devuelve false.
            }
        }
        return true; // Si todos los barcos están hundidos, devuelve true.
    }

    // Método para parsear la entrada del usuario en formato (e.g., A5).
    private Coordinate parseInput(String input) {
        if (input.length() != 2) return null; // La entrada debe tener una longitud de 2 caracteres.
        char rowChar = input.charAt(0); // Primer carácter representa la fila.
        char colChar = input.charAt(1); // Segundo carácter representa la columna.
        int row = rowChar - 'A'; // Convierte el carácter de la fila a un índice (0-9).
        int col = colChar - '0'; // Convierte el carácter de la columna a un índice (0-9).
        // Si la fila o la columna están fuera del rango válido, devuelve null.
        if (row < 0 || row >= 10 || col < 0 || col >= 10) {
            return null;
        }
        return new Coordinate(row, col); // Devuelve una nueva coordenada con los valores parseados.
    }

    // Método principal para iniciar el juego.
    public static void main(String[] args) {
        Game game = new Game(); // Crea una nueva instancia del juego.
        game.startGame(); // Inicia el juego.
    }
}
