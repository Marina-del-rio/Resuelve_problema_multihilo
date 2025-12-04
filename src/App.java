import Modelos.Buffer;
import Modelos.Escritor;
import Modelos.Lector;

public class App {

    public static void main(String[] args) {

        System.out.println("Iniciando simulación de Lectores-Escritores...");

        Buffer buffer = new Buffer(); // El recurso compartido

        // Creamos los hilos
        Thread escritor1 = new Escritor("E1", buffer);

        Thread lector1 = new Lector("L1", buffer);
        Thread lector2 = new Lector("L2", buffer);

        // Iniciamos todos los hilos
        escritor1.start();
        lector1.start();
        lector2.start();
    }
}
