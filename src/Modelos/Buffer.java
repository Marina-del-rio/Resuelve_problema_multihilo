package Modelos;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Representa un buffer compartido que permite lecturas concurrentes sin
 * bloqueo.
 * <p>
 * Los lectores pueden leer libremente incluso mientras un escritor está
 * escribiendo, ya que {@link CopyOnWriteArrayList} garantiza que cada lectura
 * ve una versión estable del buffer.
 * </p>
 *
 * <p>
 * Solo se garantiza exclusividad entre escritores para evitar inconsistencias
 * durante el proceso de escritura, pero los lectores jamás son bloqueados.
 * </p>
 */
public class Buffer {

    /** Lista de datos compartidos. CopyOnWrite permite lecturas sin bloqueo. */
    private final CopyOnWriteArrayList<String> buffer = new CopyOnWriteArrayList<>();

    /** Capacidad máxima del buffer. */
    private final int capacidad = 5;

    /** Indica si un escritor está escribiendo actualmente. */
    private boolean escribiendo = false;

    /**
     * Devuelve el contenido actual del buffer.
     * <p>
     * Esta operación no requiere sincronización, ya que cada lector obtiene
     * una vista consistente gracias a {@code CopyOnWriteArrayList}.
     * </p>
     *
     * @return El contenido del buffer como cadena.
     */
    public String obtenerDatos() {
        return buffer.toString();
    }

    /**
     * Solicita permiso para escribir un nuevo dato.
     * <p>
     * Un escritor debe esperar si:
     * <ul>
     * <li>Otro escritor está escribiendo.</li>
     * <li>El buffer está lleno.</li>
     * </ul>
     * Los lectores nunca son bloqueados por esta operación.
     * </p>
     *
     * @throws InterruptedException si el hilo escritor es interrumpido.
     */
    public synchronized void iniciarEscritura() throws InterruptedException {
        while (escribiendo || buffer.size() >= capacidad) {
            wait();
        }
        escribiendo = true;
    }

    /**
     * Finaliza una operación de escritura y permite que otros escritores
     * continúen cuando sea posible.
     */
    public synchronized void finalizarEscritura() {
        escribiendo = false;
        notifyAll();
    }

    /**
     * Añade un nuevo valor al buffer.
     * <p>
     * Este método no es sincronizado porque se garantiza que solo es invocado
     * mientras el hilo ya posee el permiso exclusivo otorgado por
     * {@link #iniciarEscritura()}.
     * </p>
     *
     * @param valor Nuevo dato a almacenar.
     */
    public void anadirDato(String valor) {
        buffer.add(valor);
    }
}
