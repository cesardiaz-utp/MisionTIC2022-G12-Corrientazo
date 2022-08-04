package co.edu.utp.misiontic.cesardiaz.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String cliente;
    private EstadoPedido estado;
    private OpcionPedido opcion;
    private List<Adicional> adicionales;
    private Mesa mesa;

    // Solo por manejo de base de datos
    private Integer id;

    public Pedido(String cliente, OpcionPedido opcion) {
        this.cliente = cliente;
        this.opcion = opcion;

        this.estado = EstadoPedido.PENDIENTE_ENTREGA;
        adicionales = new ArrayList<>();
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public OpcionPedido getOpcion() {
        return opcion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public void setAdicionales(List<Adicional> adicionales) {
        this.adicionales = adicionales;
    }

    public List<Adicional> getAdicionales() {
        return adicionales;
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
        return cliente;
    }

}
