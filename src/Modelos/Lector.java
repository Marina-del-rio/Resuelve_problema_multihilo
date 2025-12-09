package Modelos;

/**
 * Representa un hilo lector que accede al buffer sin bloquearse nunca.
 * <p>
 * Los lectores pueden ejecutar lecturas concurrentes incluso cuando
 * un escritor está agregando datos al buffer.
 * </p>
 */
public class Lector extends Thread {

    /** Identificador del lector. */
    private final String id;

    /** Buffer compartido desde el cual se leen los datos. */
    private final Buffer buffer;

    /**
     * Crea un nuevo lector.
     *
     * @param id     Identificador del lector.
     * @param buffer Buffer compartido a leer.
     */
    public Lector(String id, Buffer buffer) {
        super(id);
        this.id = id;
        this.buffer = buffer;
    }

    /**
     * Realiza varias lecturas del buffer con pausas simuladas.
     * Los lectores no necesitan sincronización y nunca se bloquean.
     */
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {

                System.out.println("Lector " + id + " lee: " + buffer.obtenerDatos());
                Thread.sleep(1000); // Simula tiempo de lectura

                Thread.sleep(1000); // Pausa antes de próxima lectura
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo lector " + id + " interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}
