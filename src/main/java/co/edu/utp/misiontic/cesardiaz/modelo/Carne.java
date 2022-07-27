package co.edu.utp.misiontic.cesardiaz.modelo;

public class Carne {
    private String nombre;

    public Carne(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
