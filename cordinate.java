public class Coordinate {
    private int row; // Almacena la fila de la coordenada.
    private int col; // Almacena la columna de la coordenada.

    // Constructor de la clase Coordinate que inicializa las variables de instancia.
    public Coordinate(int row, int col) {
        this.row = row; // Inicializa la fila con el valor proporcionado.
        this.col = col; // Inicializa la columna con el valor proporcionado.
    }

    // Método para obtener el valor de la fila.
    public int getRow() {
        return row; // Devuelve el valor de la fila.
    }

    // Método para obtener el valor de la columna.
    public int getCol() {
        return col; // Devuelve el valor de la columna.
    }
}
