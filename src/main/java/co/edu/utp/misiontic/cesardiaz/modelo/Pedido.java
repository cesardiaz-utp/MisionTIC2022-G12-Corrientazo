package co.edu.utp.misiontic.cesardiaz.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String cliente;
    private EstadoPedido estado;
    private OpcionPedido opcion;
    private List<Adicional> adicionales;

    public Pedido(String cliente, OpcionPedido opcion) {
        this.cliente = cliente;
        this.opcion = opcion;

        this.estado = EstadoPedido.PENDIENTE_ENTREGA;
        adicionales = new ArrayList<>();
    }

    public String getCliente() {
        return cliente;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void agregarAdicional(Adicional adicional) {
        this.adicionales.add(adicional);
    }

    public Integer calcularValor() {
        var total = opcion.getPrecio();
        total += adicionales.stream()
                .map(item -> item.getPrecio())
                .reduce((a, b) -> a + b)
                .orElse(0);

        return total;
    }

    public void entregar() {
        estado = EstadoPedido.PENDIENTE_COBRAR;
    }

    @Override
    public String toString() {
        return "Pedido [cliente=" + cliente + ", estado=" + estado + ", opcion=" + opcion + "]";
    }
    
}
