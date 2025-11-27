package Modelos;

public class Lector extends Thread {

    String id;
    private Buffer buffer;

    public Lector(String id, Buffer buffer) {
        super(id);
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                buffer.iniciarLectura(this.id);

                try {
                    // --- Lectura concurrente ---
                    // Esta parte se ejecuta sin tener el lock del buffer.
                    // Varios lectores pueden estar aquí al mismo tiempo.
                    System.out.println("... Lector " + id + " está leyendo el contenido: [" + buffer.obtenerDatos() + "]");
                    Thread.sleep(1000); // Simula tiempo de lectura
                } finally {
                    // Nos aseguramos de liberar el "permiso" de lectura.
                    buffer.finalizarLectura(this.id);
                }

                // Pausa antes de volver a leer
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo lector " + id + " interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}
