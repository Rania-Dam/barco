package barcos;

import java.util.Random;

public class barco {
	
	// Comprueba si queda algún barco en el tablero
	public static boolean quedan_barcos(char[][] tab) {
	    for (int i = 0; i < tab.length; i++) {
	        for (int j = 0; j < tab[i].length; j++) {
	            if (tab[i][j] != '-') {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	public static void insertar_barcos(char[][] tab, int lanchas, int buques, int acorazados, int portaaviones) {
	    int totalBarcos = lanchas + buques + acorazados + portaaviones;
	    for (int i = 0; i < totalBarcos; i++) {
	        char barco;
	        if (i < lanchas) {
	            barco = 'L';
	        } else if (i < lanchas + buques) {
	            barco = 'B';
	            i++;
	        } else if (i < lanchas + buques + acorazados) {
	            barco = 'A';
	            i += 2;
	        } else {
	            barco = 'P';
	            i += 3;
	        }
	        inserta_barco(tab, barco);
	    }
	}



	private static void inserta_barco(char[][] tab, char barco) {
		// TODO Auto-generated method stub
	    Random random = new Random();
	    int row, columna;
	    do {
	        row = random.nextInt(tab.length);
	        columna = random.nextInt(tab[0].length);
	    } while (tab[row][columna] != '.' || tab[row][columna + 1] != '.');
	    tab[row][columna] = 'L';
	    tab[row][columna + 1] = 'L';
	}


	public static void inserta_lancha(char[][] tab) {
	    int row, col;
	    boolean insertado = false;
	    while (!insertado) {
	        row = (int) (Math.random() * tab.length);
	        col = (int) (Math.random() * tab[0].length);
	        // Verificar si la pos esta vacia
	        if (tab[row][col] == '_') {
	            tab[row][col] = 'L';
	            insertado = true;
	        }
	    }
	}

	// Inserta un buque (un barco de longitud 2) en una posición aleatoria del tablero (si cabe)
	public static void inserta_buque(char[][] tab) {
	    int row, col;
	    boolean insert = false;
	    while (!insert) {
	        row = (int) (Math.random() * tab.length);
	        col = (int) (Math.random() * tab[0].length);
	        // Verificar si el buque cabe en la pos actual y si esta vacia
	        if (row + 1 < tab.length && tab[row][col] == '_' && tab[row + 1][col] == '_') {
	            // Verificar si las pos no estan ocupadas por otros barcos
	            if (posicionesValidasParaBuque(tab, row, col)) {
	                tab[row][col] = 'B';
	                tab[row + 1][col] = 'B';
	                insert = true;
	            }
	        }
	    }
	}
	private static boolean posicionesValidasParaBuque(char[][] tab, int row, int col) {
	    return tab[row][col] != 'A' && tab[row + 1][col] != 'A'; // 
	}


	public static void inserta_acorazado(char[][] tab) {
	    int row, col;
	    do {
	        row = (int) (Math.random() * tab.length);
	        col = (int) (Math.random() * tab[0].length);
	    } while (!verif_inserta_acorazado(tab, row, col));
	    for (int i = 0; i < 3; i++) {
	        tab[row][col + i] = 'A';
	    }
	}



	private static boolean verif_inserta_acorazado(char[][] tab, int row, int col) {
	    // Verificar si la pos esta dentro
	    if (row < 0 || row >= tab.length || col < 0 || col + 2 >= tablero[0].length) {
	        return false;
	    }
	    // Verificar si las posiciones donde 
	    for (int i = 0; i < 3; i++) {
	        if (tab[row][col + i] != '\u0000') {
	            return false;
	        }
	    }
	    // Si todas las condiciones son correctas, hay espacio para el acorazado
	    return true;
	}

	
	// Inserta un portaaviones (un barco de longitud 5) en una posición aleatoria del tablero (si cabe)
	public static void inserta_portaaviones(char[][] tab) {
	    int row, col;
	    do {
	        row = (int) (Math.random() * tab.length);
	        col = (int) (Math.random() * tab[0].length);
	    } while (!verif_inserta_portaaviones(tab, row, col));
	    for (int i = 0; i < 5; i++) {
	        tab[row][col + i] = 'P';
	    }
	}


	private static boolean verif_inserta_portaaviones(char[][] tab, int row, int col) {
	    // Verificar si la posicion esta dentro de los lim
	    if (row < 0 || row >= tab.length || col < 0 || col >= tab[0].length) {
	        return false;
	    }
	    // Verificar si la pos y las pos estan ocupadas
	    for (int i = 0; i < 5; i++) {
	        if (tab[row][col + i] != '\u0000') {
	            return false;
	        }
	    }
	    if (col + 5 > tab[0].length) {
	        return false;
	    }
	    // Si todas las condiciones son validas
	    return true;
	}


	public static boolean comprueba_inserta_lancha(char[][] tablero, int f, int c) {
	    // Verificar si las coordenadas estan dentro de la limite
	    if (f < 0 || f >= tablero.length || c < 0 || c >= tablero[0].length) {
	        return false;
	    }
	    // Verificar si la pos está vacia 
	    return tablero[f][c] == '_';
	}
	

	public static boolean comprueba_inserta_buque(char[][] tablero, int f, int c) {
	    // si esta dentro del lim
	    if (f < 0 || f >= tablero.length || c < 0 || c >= tablero[0].length) {
	        return false; 
	    }
	    // si esta ocupada
	    if (tablero[f][c] != '\u0000') { 
	        return false; 
	    }
	    return true;
	}


	public static boolean comprueba_inserta_acorazado(char[][] tablero, int f, int c) {
	    int masd4 = 4;
	    // Comprobamos si la coordenada está dentro de los límites del tablero
	    if (f < 0 || f >= tablero.length || c < 0 || c >= tablero[0].length) {
	        return false; 
	    }
	    // si puedo hacer lo horizontal
	    if (c + masd4 > tablero[0].length) {
	        return false; 
	    }
	    // Verif si es vacias
	    for (int i = 0; i < masd4; i++) {
	        if (tablero[f][c + i] != '\u0000') {
	            return false; 
	        }
	    }
	    return true;
	}

	
	public static boolean comprueba_inserta_portaaviones(char[][] tablero, int f, int c) {
	    int tamad5 = 5;

	    // si esta dentro del lim
	    if (f < 0 || f >= tablero.length || c < 0 || c >= tablero[0].length) {
	        return false;
	    }
	    // si puedo hacerlo horizontal
	    if (c + tamad5 > tablero[0].length) {
	        return false; 
	    }
	    // Verificamos que las celdas estén vacías
	    for (int i = 0; i < tamad5; i++) {
	        if (tablero[f][c + i] != '\u0000') {
	            return false; 
	        }
	    }
	    return true;
	}