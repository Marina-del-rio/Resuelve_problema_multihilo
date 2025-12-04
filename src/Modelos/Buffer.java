package Modelos;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Buffer {

    private List<String> buffer = new CopyOnWriteArrayList<>();
    private int capacidad = 5;

    // --- Variables de control para Lectores-Escritores ---
    private int lectoresActivos = 0; // Cuenta cuántos lectores están leyendo ahora mismo
    private boolean escribiendo = false; // Indica si hay un escritor activo

    public synchronized void iniciarLectura(String id) {
        try {
            while (escribiendo || buffer.isEmpty()) {
                if (buffer.isEmpty()) {
                    System.out.println(id + " esperando datos (buffer vacío)...");
                } else {
                    System.out.println(id + " esperando a que termine el escritor...");
                }
                wait();
            }
            lectoresActivos++;
            // --- AÑADE ESTO PARA VER LA CONCURRENCIA ---
            System.out.println(">> " + id + " entró. Lectores actuales leyendo simultáneamente: " + lectoresActivos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void finalizarLectura(String id) {
        lectoresActivos--; // Un lector ha terminado

        // Si ya no quedan lectores (es 0), notificamos a todos.
        // Esto es crucial para despertar a un Escritor que esté esperando.
        if (lectoresActivos == 0) {
            notifyAll();
        }
    }

    public String obtenerDatos() {
        return buffer.toString();
    }

    public synchronized void iniciarEscritura(String id) {
        try {
            // El escritor debe esperar si:
            // 1. Hay lectores leyendo (lectoresActivos > 0)
            // 2. O hay otro escritor escribiendo (escribiendo == true)
            // 3. O el buffer está lleno (capacidad)
            while (lectoresActivos > 0 || escribiendo || buffer.size() >= capacidad) {
                wait();
            }
            // Si pasa, bloquea el acceso exclusivo
            escribiendo = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void finalizarEscritura(String id) {
        escribiendo = false; // Libera el bloqueo
        notifyAll(); // Despierta a los lectores que esperaban datos y a otros escritores
    }

    public void anadirDato(String nuevoValor) {
        // Ya no necesita 'synchronized' explícito aquí porque solo se llama
        // cuando el hilo ya pasó por 'iniciarEscritura' (que garantiza exclusividad).
        buffer.add(nuevoValor);
    }
}