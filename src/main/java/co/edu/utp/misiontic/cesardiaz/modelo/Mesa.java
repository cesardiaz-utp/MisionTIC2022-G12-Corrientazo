package co.edu.utp.misiontic.cesardiaz.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private String numero;
    private List<Pedido> pedidos;

    public Mesa(String numero) {
        this.numero = numero;

        this.pedidos = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public void entregarPedido(Pedido pedido) {
        pedido.entregar();
    }

    public void agregarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public Integer calcularTotal() {
        return this.pedidos.stream()
            .map(pedido -> pedido.calcularValor())
            .reduce((a, b) -> a + b)
            .orElse(0);
    }

    @Override
    public String toString() {
        return numero;
    }

    public void limpiarPedidos() {
        pedidos.clear();
    }
}
