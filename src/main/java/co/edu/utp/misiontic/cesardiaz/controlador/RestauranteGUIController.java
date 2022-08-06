package co.edu.utp.misiontic.cesardiaz.controlador;

import co.edu.utp.misiontic.cesardiaz.excepcion.ObjetoNoExistenteException;
import co.edu.utp.misiontic.cesardiaz.excepcion.PagoInsuficienteException;
import co.edu.utp.misiontic.cesardiaz.modelo.Adicional;
import co.edu.utp.misiontic.cesardiaz.modelo.Carne;
import co.edu.utp.misiontic.cesardiaz.modelo.Ensalada;
import co.edu.utp.misiontic.cesardiaz.modelo.EstadoPedido;
import co.edu.utp.misiontic.cesardiaz.modelo.Jugo;
import co.edu.utp.misiontic.cesardiaz.modelo.Mesa;
import co.edu.utp.misiontic.cesardiaz.modelo.Pedido;
import co.edu.utp.misiontic.cesardiaz.modelo.Principio;
import co.edu.utp.misiontic.cesardiaz.modelo.Sopa;
import co.edu.utp.misiontic.cesardiaz.modelo.dao.MesaDao;
import co.edu.utp.misiontic.cesardiaz.modelo.dao.OpcionAlimentoDao;
import co.edu.utp.misiontic.cesardiaz.modelo.dao.PedidoDao;
import java.sql.SQLException;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ROG
 */
public class RestauranteGUIController {

    private MesaDao mesaDao;
    private PedidoDao pedidoDao;
    private OpcionAlimentoDao alimentoDao;

    public RestauranteGUIController() {
        mesaDao = new MesaDao();
        pedidoDao = new PedidoDao();
        alimentoDao = new OpcionAlimentoDao();
    }

    public List<Mesa> listarMesas() throws SQLException {
        return mesaDao.listar();
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

    public List<Adicional> listarAdicionales() throws SQLException {
        return alimentoDao.listarAdicionales();
    }

    public List<Pedido> listarPedidosDeMesa(Mesa mesa) throws SQLException, ObjetoNoExistenteException {
        return pedidoDao.listarPedidos(mesa);
    }

    public void guardarPedido(Pedido pedido) throws SQLException {
        pedidoDao.crear(pedido);
    }

    public void agregarAdicionalPedido(Pedido pedido, Adicional adicional) throws SQLException {
        pedidoDao.agregarAdicional(pedido, adicional);
    }

    public Integer calcularTotalMesa(Mesa mesa) throws SQLException, ObjetoNoExistenteException {
        var pedidos = pedidoDao.listarPedidos(mesa);
        return pedidos.stream()
                .filter(pedido -> pedido.getEstado() == EstadoPedido.PENDIENTE_COBRAR)
                .map(pedido -> pedido.calcularValor())
                .reduce((a, b) -> a + b)
                .orElse(0);
    }

    public Integer pagarCuenta(Mesa mesa, Integer efectivo) throws PagoInsuficienteException, SQLException, ObjetoNoExistenteException {
        // Solicitar el valor del efectivo
        var total = calcularTotalMesa(mesa);

        if (efectivo < total) {
            throw new PagoInsuficienteException("El efectivo no es suficiente para pagar el total de la mesa");
        }

        // Eliminar pedidos
        pedidoDao.borrarPedidosMesa(mesa);

        return efectivo - total;
    }

    public void entregarPedido(Pedido pedido) throws SQLException {
        pedido.entregar();
        pedidoDao.actualizarEstadoPedido(pedido);
    }
}
