package co.edu.utp.misiontic.cesardiaz.modelo;

public class Principio {
    private String nombre;
    // Solo por manejo de base de datos
    private Integer id;

    public Principio(String nombre) {
        this.nombre = nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
