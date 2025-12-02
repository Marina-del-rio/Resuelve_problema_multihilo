package Modelos;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Buffer {

    // Cambiamos a String porque tu Escritor envía texto ("Dato de...")
    private List<String> buffer = new CopyOnWriteArrayList<>();

    private int capacidad = 5;

    public synchronized void iniciarLectura(String id) {
        try {
            while (buffer.isEmpty()) {
                System.out.println(id + " esperando datos...");
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public void finalizarLectura(String id) {

    }

    public String obtenerDatos() {
        return buffer.toString();
    }

    public synchronized void iniciarEscritura(String id) {
        try {
            while (buffer.size() >= capacidad) {
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void finalizarEscritura(String id) {

    }

    public synchronized void anadirDato(String nuevoValor) {
        buffer.add(nuevoValor);

        notifyAll();

    }

    // TODO: Hacer que leer y escribir puedan suceder a la vez
}