package Modelos;

/**
 * Representa un hilo escritor que agrega nuevos datos al buffer.
 * <p>
 * Aunque los lectores pueden leer libremente, los escritores mantienen
 * exclusividad entre sí para evitar inconsistencias.
 * </p>
 */
public class Escritor extends Thread {

    /** Identificador del escritor. */
    private final String id;

    /** Buffer compartido donde se escriben los datos. */
    private final Buffer buffer;

    /**
     * Crea un nuevo escritor.
     *
     * @param id     Identificador del escritor.
     * @param buffer Buffer compartido donde escribirá.
     */
    public Escritor(String id, Buffer buffer) {
        super(id);
        this.id = id;
        this.buffer = buffer;
    }

    /**
     * Realiza varias escrituras controladas sobre el buffer.
     * Los escritores se sincronizan entre sí, pero no bloquean lectores.
     */
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 3; i++) {

                buffer.iniciarEscritura();

                try {
                    String valor = "Dato" + i;
                    buffer.anadirDato(valor);
                    System.out.println("Escritor " + id + " escribe: " + valor);
                    Thread.sleep(1500); // Simula tiempo de escritura
                } finally {
                    buffer.finalizarEscritura();
                }

                Thread.sleep(2000); // Pausa entre escrituras
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo escritor " + id + " interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}
