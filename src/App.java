import java.util.concurrent.locks.ReentrantLock;
/**
 * Clase principal donde meteremos 
 */
public class App {

    int recurso = 0; 
    ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        
        
        



    }

    public void leer(String id) {
        lock.lock(); 
        try {
            System.out.println(id + " leyendo valor: " + recurso);
            Thread.sleep(500); // tiempo de lectura
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // desbloquear
        }
        
    }
    public void esribir() {

    }
}