package barcos;

public class Board {
    private char[][] grid; // Declaración de una matriz bidimensional para representar el tablero.

    // Constructor de la clase Board que inicializa el tablero con el tamaño dado.
    public Board(int size) {
        grid = new char[size][size]; // Inicializa la matriz con el tamaño especificado.
        initializeBoard(); // Llama al método para inicializar el tablero con valores predeterminados.
    }

    // Método para inicializar el tablero, rellenando cada celda con el carácter '-'.
    private void initializeBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '-'; // Cada celda se establece en '-' indicando que está vacía.
            }
        }
    }

    // Método para colocar un barco en el tablero.
    public void placeShip(Ship ship) {
        for (Coordinate coord : ship.getPositions()) {
            grid[coord.getRow()][coord.getCol()] = 'L'; // Marca la posición del barco en el tablero con 'L'.
        }
    }

    // Método para verificar si la colocación de un barco es válida.
    public boolean isPlacementValid(Ship ship) {
        for (Coordinate coord : ship.getPositions()) {
            // Si alguna de las posiciones del barco ya está ocupada, devuelve false.
            if (grid[coord.getRow()][coord.getCol()] != '-') {
                return false;
            }
        }
        return true; // Devuelve true si todas las posiciones del barco están libres.
    }

    // Método para actualizar una celda del tablero con un símbolo específico.
    public void updateBoard(Coordinate coord, char symbol) {
        grid[coord.getRow()][coord.getCol()] = symbol; // Actualiza la celda con el símbolo dado.
    }

    // Método para obtener el valor en una posición específica del tablero.
    public char getPosition(Coordinate coord) {
        return grid[coord.getRow()][coord.getCol()]; // Devuelve el carácter en la posición especificada.
    }

    // Método para mostrar el tablero en la consola.
    public void display() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " "); // Imprime cada celda seguida de un espacio.
            }
            System.out.println(); // Nueva línea después de cada fila.
        }
    }
}



