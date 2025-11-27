package Modelos;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

    public Queue<Integer> buffer = new LinkedList<>();
    int capacidad = 5;

    public void leer(String id, int recurso) {
        
        while (!buffer.isEmpty()) {
            System.out.println(id + " leyendo valor: " + recurso);
            
        }  
       
        notifyAll();
    }

    public synchronized void escribir(String id,int nuevoValor) {
        
        try {
            while (buffer.size() <= capacidad) {
                System.out.println(id + " escribiendo valor: " + nuevoValor);
                wait();
            }
            buffer.add(nuevoValor);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void iniciarLectura(String id) {
       
    }

    public String obtenerDatos() {
        return String.join(", ", buffer.stream().map(Object::toString).toArray(String[]::new));
    }

    public void anadirDato(String nuevoValor) {
      
    }

    public void finalizarEscritura(String id) {
       
    }

    public void finalizarLectura(String id) {
       
    }
    
}
