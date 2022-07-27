package co.edu.utp.misiontic.cesardiaz.modelo;

public class Sopa {
    private String nombre;

    public Sopa(String nombre) {
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
