
import java.util.Scanner;

public class juegoDificult {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        System.out.println("Bienvenido al juego");
        System.out.println("Selecciona la dificultad:");
        System.out.println("1. Facil");
        System.out.println("2. Medio");
        System.out.println("3. Dificil");

        System.out.print("Elige una opcion: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Has seleccionado la dificultad: Facil");
                iniciarJuegoFacil();
                break;
            case 2:
                System.out.println("Has seleccionado la dificultad: Medio");
                iniciarJuegoMedio();
                break;
            case 3:
                System.out.println("Has seleccionado la dificultad: Dificil");
                iniciarJuegoDificil();
                break;
            default:
                System.out.println("Opcion no valida. Por favor, elige una opción entre 1 y 3.");
        }

        scanner.close();
    }

    public static void iniciarJuegoFacil() {
        System.out.println("Iniciando juego en modo facil...");
    }

    public static void iniciarJuegoMedio() {
        System.out.println("Iniciando juego en modo medio...");
    }

    public static void iniciarJuegoDificil() {
        System.out.println("Iniciando juego en modo dificil...");
    }
}
