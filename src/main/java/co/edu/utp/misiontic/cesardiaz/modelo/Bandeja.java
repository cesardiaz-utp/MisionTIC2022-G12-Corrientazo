package co.edu.utp.misiontic.cesardiaz.modelo;

public class Bandeja extends OpcionPedido {

    public Bandeja(Integer precio) {
        super(precio);
        setId(2);
    }

    public Bandeja(Integer precio, Principio principio, Carne carne, Jugo jugo) {
        super(precio, principio, carne, jugo);
        setId(2);
    }

    public Bandeja(Integer precio, Principio principio, Carne carne, Ensalada ensalada, Jugo jugo) {
        super(precio, principio, carne, ensalada, jugo);
        setId(2);
    }

    @Override
    public String toString() {
        return "Bandeja [precio=" + getPrecio() + ", principio=" + getPrincipio() + ", carne=" + getCarne()
                + ", ensalada=" + getEnsalada() + ", jugo=" + getJugo();
    }

}
