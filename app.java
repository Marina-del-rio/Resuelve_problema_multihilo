import java.util.concurrent.locks.ReentrantLock;
/**
 * Clase principal donde meteremos 
 */
public class app {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread hiloEscritor = new Thread(() -> {
            lock.lock();
            try {
            System.out.println("El Escritor está escribiendo...");
            


            }
            catch
        }
        
        );

    }
}
