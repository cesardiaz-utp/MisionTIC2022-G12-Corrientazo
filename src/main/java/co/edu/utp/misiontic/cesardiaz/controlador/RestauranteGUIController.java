package co.edu.utp.misiontic.cesardiaz.controlador;

import co.edu.utp.misiontic.cesardiaz.modelo.Carne;
import co.edu.utp.misiontic.cesardiaz.modelo.Ensalada;
import co.edu.utp.misiontic.cesardiaz.modelo.Jugo;
import co.edu.utp.misiontic.cesardiaz.modelo.Mesa;
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
}
