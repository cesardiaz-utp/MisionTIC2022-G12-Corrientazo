package co.edu.utp.misiontic.cesardiaz.modelo;

public class Ensalada {
    private String nombre;

    public Ensalada(String nombre) {
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
