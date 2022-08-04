package co.edu.utp.misiontic.cesardiaz.modelo;

public class Adicional {

    private String nombre;
    private Integer precio;
    private Integer id;

    public Adicional(String nombre, Integer precio) {
        this.nombre = nombre;
        this.precio = precio;
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

    public Integer getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return String.format("%s $ %,d", nombre, precio);
    }
}
