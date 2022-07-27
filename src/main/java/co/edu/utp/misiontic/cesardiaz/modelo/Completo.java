package co.edu.utp.misiontic.cesardiaz.modelo;

public class Completo extends OpcionPedido {

    private Sopa sopa;

    public Completo(Integer precio) {
        super(precio);
    }

    public Completo(Integer precio, Sopa sopa, Principio principio, Carne carne, Jugo jugo) {
        super(precio, principio, carne, jugo);
        this.sopa = sopa;
    }

    public Completo(Integer precio, Sopa sopa, Principio principio, Carne carne, Ensalada ensalada, Jugo jugo) {
        super(precio, principio, carne, ensalada, jugo);
        this.sopa = sopa;
    }

    public void setSopa(Sopa sopa) {
        this.sopa = sopa;
    }

    public Sopa getSopa() {
        return sopa;
    }

    @Override
    public String toString() {
        return "Completo [precio=" + getPrecio() + ", sopa=" + getSopa() + ", principio=" + getPrincipio() + ", carne="
                + getCarne() + ", ensalada=" + getEnsalada() + ", jugo=" + getJugo();
    }
}
