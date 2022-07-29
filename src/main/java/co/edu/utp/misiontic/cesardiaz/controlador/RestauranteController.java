package co.edu.utp.misiontic.cesardiaz.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import co.edu.utp.misiontic.cesardiaz.excepcion.ObjetoNoExistenteException;
import co.edu.utp.misiontic.cesardiaz.excepcion.PagoInsuficienteException;
import co.edu.utp.misiontic.cesardiaz.modelo.Carne;
import co.edu.utp.misiontic.cesardiaz.modelo.Ensalada;
import co.edu.utp.misiontic.cesardiaz.modelo.EstadoPedido;
import co.edu.utp.misiontic.cesardiaz.modelo.Jugo;
import co.edu.utp.misiontic.cesardiaz.modelo.Mesa;
import co.edu.utp.misiontic.cesardiaz.modelo.Principio;
import co.edu.utp.misiontic.cesardiaz.modelo.Sopa;
import co.edu.utp.misiontic.cesardiaz.vista.MesaView;
import co.edu.utp.misiontic.cesardiaz.vista.PedidoView;
import co.edu.utp.misiontic.cesardiaz.vista.PrincipalView;

public class RestauranteController {

    private MesaView mesaView;
    private PedidoView pedidoView;
    private PrincipalView principalView;

    private List<Mesa> mesas;
    private List<Sopa> sopas;
    private List<Principio> principios;
    private List<Carne> carnes;
    private List<Ensalada> ensaladas;
    private List<Jugo> jugos;

    public RestauranteController(Scanner entrada) {
        mesaView = new MesaView(entrada);
        pedidoView = new PedidoView(entrada, this);
        principalView = new PrincipalView(entrada, this);

        mesas = new ArrayList<>();
        sopas = new ArrayList<>();
        principios = new ArrayList<>();
        carnes = new ArrayList<>();
        ensaladas = new ArrayList<>();
        jugos = new ArrayList<>();
    }

    // TODO: Es un método temporal
    public void iniciarBaseDatos() {
        mesas.add(new Mesa("01"));
        mesas.add(new Mesa("02"));
        mesas.add(new Mesa("03"));
        mesas.add(new Mesa("04"));
        mesas.add(new Mesa("05"));
        mesas.add(new Mesa("06"));
        mesas.add(new Mesa("07"));

        sopas.add(new Sopa("Pastas"));
        sopas.add(new Sopa("Sancocho"));
        sopas.add(new Sopa("Crema Ahuyama"));
        sopas.add(new Sopa("Consome"));

        principios.add(new Principio("Frijoles"));
        principios.add(new Principio("Lentejas"));
        principios.add(new Principio("Espaguetis"));
        principios.add(new Principio("Papa guisada"));

        carnes.add(new Carne("Res a la plancha"));
        carnes.add(new Carne("Cerdo a la plancha"));
        carnes.add(new Carne("Pechuga a la plancha"));
        carnes.add(new Carne("Albondigas"));

        ensaladas.add(new Ensalada("Solo tomate"));
        ensaladas.add(new Ensalada("Jardinera"));
        ensaladas.add(new Ensalada("Remolacha y zanahoria"));

        jugos.add(new Jugo("Limonada"));
        jugos.add(new Jugo("Guayaba"));
        jugos.add(new Jugo("Mora"));
        jugos.add(new Jugo("Maracuyá"));

    }

    public void iniciarAplicacion() {
        principalView.iniciarAplicacion();
    }

    public void crearMesa() {
        // Pedir al usuario el número de la mesa a crear
        var numeroMesa = mesaView.leerNumeroMesa();

        // Crear una nueva mesa en el sistema
        var mesa = new Mesa(numeroMesa);

        // Agregarlo a la lista de mesas en el sistema
        mesas.add(mesa);

        // Mostrar al usuario las mesas actualizadas
        mesaView.mostrarMesas(mesas);
    }

    public Mesa consultarMesa(String numero) throws ObjetoNoExistenteException {
        return mesas.stream()
                .filter(mesa -> mesa.getNumero().equals(numero))
                .findFirst()
                .orElseThrow(() -> new ObjetoNoExistenteException("No existe una mesa con el número " + numero));
    }

    public void agregarPedido(Mesa mesa) {
        var pedido = pedidoView.cargarPedido();
        System.out.println("Pedido: " + pedido);

        mesa.agregarPedido(pedido);
    }

    public List<Sopa> listarSopas() {
        return sopas;
    }

    public List<Principio> listarPrincipios() {
        return principios;
    }

    public List<Carne> listarCarnes() {
        return carnes;
    }

    public List<Ensalada> listarEnsaladas() {
        return ensaladas;
    }

    public List<Jugo> listarJugos() {
        return jugos;
    }

    public Integer pagarCuenta(Mesa mesa) throws PagoInsuficienteException {
        // Solicitar el valor del efectivo
        var efectivo = mesaView.leerValorEfectivo();

        var total = mesa.calcularTotal();
        if (efectivo < total) {
            throw new PagoInsuficienteException("El efectivo no es suficiente para pagar el total de la mesa");
        }

        // Eliminar pedidos
        mesa.limpiarPedidos();

        return efectivo - total;

    }

    public Mesa seleccionarMesa() {
        while (true) {
            principalView.mostrarMensaje("Listado de mesas existentes");
            for (int i = 0; i < mesas.size(); i++) {
                principalView.mostrarMensaje(
                        String.format("%d -> %s", (i + 1), mesas.get(i)));
            }
            var opcion = principalView.leerEntero("Cual es su elección: ");
            if (opcion >= 1 && opcion <= mesas.size()) {
                return mesas.get(opcion - 1);
            } else {
                principalView.mostrarError("Opcion inválida");
            }
        }
    }

    public void mostrarEstadoMesa(Mesa mesa) {
        principalView.mostrarMensaje("Mesa: " + mesa);
        mesa.getPedidos().stream()
                .map(p -> p.toString())
                .forEach(principalView::mostrarMensaje);
    }

    public void entregarPedido(Mesa mesa) {
        var pedidos = mesa.getPedidos().stream()
                .filter(p -> p.getEstado() == EstadoPedido.PENDIENTE_ENTREGA)
                .collect(Collectors.toList());

        if (pedidos.isEmpty()) {
            principalView.mostrarError("La mesa no tiene pedidos a entregar");
            return;
        }

        while (true) {
            principalView.mostrarMensaje("Listado de pedidos pendientes");
            for (int i = 0; i < pedidos.size(); i++) {
                var pedido = pedidos.get(i);
                if (pedido.getEstado() != EstadoPedido.PENDIENTE_ENTREGA) {
                    continue;
                }
                principalView.mostrarMensaje(
                        String.format("%d -> %s", (i + 1), pedidos.get(i)));
            }
            var opcion = principalView.leerEntero("Cual es su elección: ");
            if (opcion >= 1 && opcion <= mesa.getPedidos().size()) {
                pedidos.get(opcion - 1).entregar();

                break;
            } else {
                principalView.mostrarError("Opcion inválida");
            }
        }
    }
}
