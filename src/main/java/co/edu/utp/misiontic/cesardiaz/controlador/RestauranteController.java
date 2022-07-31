package co.edu.utp.misiontic.cesardiaz.controlador;

import java.sql.SQLException;
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
import co.edu.utp.misiontic.cesardiaz.modelo.dao.MesaDao;
import co.edu.utp.misiontic.cesardiaz.modelo.dao.OpcionAlimentoDao;
import co.edu.utp.misiontic.cesardiaz.modelo.dao.PedidoDao;
import co.edu.utp.misiontic.cesardiaz.vista.MesaView;
import co.edu.utp.misiontic.cesardiaz.vista.PedidoView;
import co.edu.utp.misiontic.cesardiaz.vista.PrincipalView;

public class RestauranteController {

    private MesaView mesaView;
    private PedidoView pedidoView;
    private PrincipalView principalView;

    private MesaDao mesaDao;
    private PedidoDao pedidoDao;
    private OpcionAlimentoDao alimentoDao;

    public RestauranteController(Scanner entrada) {
        mesaView = new MesaView(entrada);
        pedidoView = new PedidoView(entrada, this);
        principalView = new PrincipalView(entrada, this);

        mesaDao = new MesaDao();
        pedidoDao = new PedidoDao();
        alimentoDao = new OpcionAlimentoDao();
    }

    public void iniciarAplicacion() {
        principalView.iniciarAplicacion();
    }

    public void crearMesa() throws SQLException {
        // Pedir al usuario el número de la mesa a crear
        var numeroMesa = mesaView.leerNumeroMesa();

        // Crear una nueva mesa en el sistema
        var mesa = new Mesa(numeroMesa);

        // Agregarlo a la lista de mesas en el sistema
        mesaDao.crear(mesa);

        // Mostrar al usuario las mesas actualizadas
        mesaView.mostrarMesas(mesaDao.listar());
    }

    public Mesa consultarMesa(String numero) throws ObjetoNoExistenteException, SQLException {
        return mesaDao.consultar(numero);
    }

    public void agregarPedido(Mesa mesa) throws SQLException {
        // Pedir al usuario los datos del pedido
        var pedido = pedidoView.cargarPedido();
        System.out.println("Pedido: " + pedido);

        // Agregar el pedido a la mesa
        mesa.agregarPedido(pedido);

        // Guardar pedido en base de datos
        pedido.setMesa(mesa);
        pedidoDao.crear(pedido);
    }

    public List<Sopa> listarSopas() throws SQLException {
        return alimentoDao.listarSopas();
    }

    public List<Principio> listarPrincipios() throws SQLException {
        return alimentoDao.listarPrincipios();
    }

    public List<Carne> listarCarnes() throws SQLException {
        return alimentoDao.listarCarnes();
    }

    public List<Ensalada> listarEnsaladas() throws SQLException {
        return alimentoDao.listarEnsaladas();
    }

    public List<Jugo> listarJugos() throws SQLException {
        return alimentoDao.listarJugos();
    }

    private Integer calcularTotalMesa(Mesa mesa) throws SQLException, ObjetoNoExistenteException {
        var pedidos = pedidoDao.listarPedidos(mesa);
        return pedidos.stream()
                .filter(pedido -> pedido.getEstado() == EstadoPedido.PENDIENTE_COBRAR)
                .map(pedido -> pedido.calcularValor())
                .reduce((a, b) -> a + b)
                .orElse(0);
    }

    public Integer pagarCuenta(Mesa mesa) throws PagoInsuficienteException, SQLException, ObjetoNoExistenteException {
        // Solicitar el valor del efectivo
        var total = calcularTotalMesa(mesa);
        principalView.mostrarMensaje(String.format("La cuenta es de $ %,d.", total));

        var efectivo = mesaView.leerValorEfectivo();

        if (efectivo < total) {
            throw new PagoInsuficienteException("El efectivo no es suficiente para pagar el total de la mesa");
        }

        // Eliminar pedidos
        pedidoDao.borrarPedidosMesa(mesa);

        return efectivo - total;

    }

    public Mesa seleccionarMesa() throws SQLException {
        while (true) {
            principalView.mostrarMensaje("Listado de mesas existentes");
            var mesas = mesaDao.listar();
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

    public void mostrarEstadoMesa(Mesa mesa) throws SQLException {
        principalView.mostrarMensaje("Mesa: " + mesa);
        try {
            pedidoDao.listarPedidos(mesa).stream()
                    .map(p -> p.toString())
                    .forEach(principalView::mostrarMensaje);

            var total = calcularTotalMesa(mesa);
            principalView.mostrarMensaje(String.format("Estan debiendo $ %,d.", total));
        } catch (ObjetoNoExistenteException ex) {
            principalView.mostrarMensaje("No hay pedidos para esta mesa");
        }
    }

    public void entregarPedido(Mesa mesa) throws SQLException {
        try {
            var pedidosMesa = pedidoDao.listarPedidos(mesa);
            var pedidos = pedidosMesa.stream()
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
                if (opcion >= 1 && opcion <= pedidosMesa.size()) {
                    var pedido = pedidos.get(opcion - 1);
                    pedido.entregar();
                    pedidoDao.actualizarEstadoPedido(pedido);

                    break;
                } else {
                    principalView.mostrarError("Opcion inválida");
                }
            }
        } catch (ObjetoNoExistenteException ex) {
            principalView.mostrarError("No hay pedidos pendientes por entregar");
        }
    }
}
