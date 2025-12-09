import Modelos.Buffer;
import Modelos.Escritor;
import Modelos.Lector;

/**
 * Clase principal que inicia una simulación del problema Lectores-Escritores.
 */
public class App {

    /**
     * Punto de entrada de la aplicación.
     * Crea un buffer, un escritor y múltiples lectores que interactúan
     * concurrentemente.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        System.out.println("Iniciando simulación de Lectores-Escritores...");

        Buffer buffer = new Buffer();

        Thread escritor1 = new Escritor("E1", buffer);

        Thread lector1 = new Lector("L1", buffer);
        Thread lector2 = new Lector("L2", buffer);

        escritor1.start();
        lector1.start();
        lector2.start();
    }
}
