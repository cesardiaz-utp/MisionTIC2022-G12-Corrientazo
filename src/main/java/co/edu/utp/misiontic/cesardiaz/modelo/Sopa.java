package co.edu.utp.misiontic.cesardiaz.modelo;

import java.util.Objects;

public class Sopa {

    private String nombre;
    // Solo por manejo de base de datos
    private Integer id;
    
    public Sopa(String nombre) {
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
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Sopa)) {
            return false;
        }
        var sopa = (Sopa) obj;
        return getId().equals(sopa.getId());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
}
