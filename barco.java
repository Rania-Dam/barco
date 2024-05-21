// Comprueba si queda algún barco en el tablero
public static boolean quedan_barcos(char[][] tablero) {
    for (char[] fila : tablero) {
        for (char celda : fila) {
            if (celda != '-') {
                return true;
            }
        }
    }
    return false;
}