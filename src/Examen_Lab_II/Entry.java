package Examen_Lab_II;

public class Entry {

    String user;
    long posicion;
    Entry siguiente;

    public Entry(String user, long posicion) {
        this.user = user;
        this.posicion = posicion;
        siguiente = null;

    }

}
