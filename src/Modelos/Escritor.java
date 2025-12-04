package Modelos;

public class Escritor extends Thread {
    String id;
    private Buffer buffer;

    public Escritor(String id, Buffer buffer) {
        super(id);
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 3; i++) {
                buffer.iniciarEscritura(this.id);

                try {
                    // --- Escritura exclusiva ---
                    // Esta parte se ejecuta teniendo el "permiso" de escritura.
                    String nuevoValor = i + "";
                    buffer.anadirDato(nuevoValor);
                    System.out.println("==> Escritor " + id + " escribiendo: '" + nuevoValor + "'");
                    Thread.sleep(1500); // Simula tiempo de escritura
                } finally {
                    // Nos aseguramos de liberar el lock de escritura.
                    buffer.finalizarEscritura(this.id);
                }

                // Pausa antes de volver a escribir
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo escritor " + id + " interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}
