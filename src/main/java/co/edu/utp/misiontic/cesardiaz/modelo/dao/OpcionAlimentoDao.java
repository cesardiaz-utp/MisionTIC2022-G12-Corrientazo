package co.edu.utp.misiontic.cesardiaz.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic.cesardiaz.excepcion.ObjetoNoExistenteException;
import co.edu.utp.misiontic.cesardiaz.modelo.Carne;
import co.edu.utp.misiontic.cesardiaz.modelo.Ensalada;
import co.edu.utp.misiontic.cesardiaz.modelo.Jugo;
import co.edu.utp.misiontic.cesardiaz.modelo.Principio;
import co.edu.utp.misiontic.cesardiaz.modelo.Sopa;
import co.edu.utp.misiontic.cesardiaz.util.JDBCUtilities;

public class OpcionAlimentoDao {

    public List<Sopa> listarSopas() throws SQLException {
        var sql = "SELECT * FROM Sopa";
        return JDBCUtilities.listar(sql, rset -> {
            Sopa opcion =  null;
            try {
                opcion = new Sopa(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));
            } catch (SQLException ex) {
                opcion = null;
            }
            
            return opcion;
        });
    }

    public List<Principio> listarPrincipios() throws SQLException {
        var sql = "SELECT * FROM Principio";
        return JDBCUtilities.listar(sql, rset -> {
            Principio opcion =  null;
            try {
                opcion = new Principio(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));
            } catch (SQLException ex) {
                opcion = null;
            }
            
            return opcion;
        });
    }

    public List<Carne> listarCarnes() throws SQLException {
        var respuesta = new ArrayList<Carne>();

        var statement = JDBCUtilities.getConnection().createStatement();
        var rset = statement.executeQuery("SELECT * FROM Carne");
        while (rset.next()) {
            var opcion = new Carne(rset.getString("nombre"));
            opcion.setId(rset.getInt("id"));

            respuesta.add(opcion);
        }
        rset.close();
        statement.close();

        return respuesta;

    }

    public List<Ensalada> listarEnsaladas() throws SQLException {
        var respuesta = new ArrayList<Ensalada>();

        var statement = JDBCUtilities.getConnection().createStatement();
        var rset = statement.executeQuery("SELECT * FROM Ensalada");
        while (rset.next()) {
            var opcion = new Ensalada(rset.getString("nombre"));
            opcion.setId(rset.getInt("id"));

            respuesta.add(opcion);
        }
        rset.close();
        statement.close();

        return respuesta;
    }

    public List<Jugo> listarJugos() throws SQLException {
        var respuesta = new ArrayList<Jugo>();

        var statement = JDBCUtilities.getConnection().createStatement();
        var rset = statement.executeQuery("SELECT * FROM Jugo");
        while (rset.next()) {
            var opcion = new Jugo(rset.getString("nombre"));
            opcion.setId(rset.getInt("id"));

            respuesta.add(opcion);
        }
        rset.close();
        statement.close();

        return respuesta;
    }

    public Sopa consultarSopa(Integer id) throws SQLException, ObjetoNoExistenteException {
        Sopa respuesta = null;

        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            statement = JDBCUtilities.getConnection()
                    .prepareStatement("SELECT * FROM Sopa WHERE id = ?;");
            statement.setInt(1, id);
            rset = statement.executeQuery();
            if (rset.next()) {
                respuesta = new Sopa(rset.getString("nombre"));
                respuesta.setId(rset.getInt("id"));
            } else {
                throw new ObjetoNoExistenteException("No existe la sopa con ID dado");
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return respuesta;
    }

    public Principio consultarPrincipio(Integer id) throws SQLException, ObjetoNoExistenteException {
        Principio respuesta = null;

        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            statement = JDBCUtilities.getConnection()
                    .prepareStatement("SELECT * FROM Principio WHERE id = ?;");
            statement.setInt(1, id);
            rset = statement.executeQuery();
            if (rset.next()) {
                respuesta = new Principio(rset.getString("nombre"));
                respuesta.setId(rset.getInt("id"));
            } else {
                throw new ObjetoNoExistenteException("No existe la sopa con ID dado");
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return respuesta;
    }

    public Carne consultarCarne(Integer id) throws SQLException, ObjetoNoExistenteException {
        Carne respuesta = null;

        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            statement = JDBCUtilities.getConnection()
                    .prepareStatement("SELECT * FROM Carne WHERE id = ?;");
            statement.setInt(1, id);
            rset = statement.executeQuery();
            if (rset.next()) {
                respuesta = new Carne(rset.getString("nombre"));
                respuesta.setId(rset.getInt("id"));
            } else {
                throw new ObjetoNoExistenteException("No existe la sopa con ID dado");
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return respuesta;
    }

    public Ensalada consultarEnsalada(Integer id) throws SQLException, ObjetoNoExistenteException {
        Ensalada respuesta = null;

        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            statement = JDBCUtilities.getConnection()
                    .prepareStatement("SELECT * FROM Ensalada WHERE id = ?;");
            statement.setInt(1, id);
            rset = statement.executeQuery();
            if (rset.next()) {
                respuesta = new Ensalada(rset.getString("nombre"));
                respuesta.setId(rset.getInt("id"));
            } else {
                throw new ObjetoNoExistenteException("No existe la sopa con ID dado");
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return respuesta;
    }

    public Jugo consultarJugo(Integer id) throws SQLException, ObjetoNoExistenteException {
        Jugo respuesta = null;

        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            statement = JDBCUtilities.getConnection()
                    .prepareStatement("SELECT * FROM Jugo WHERE id = ?;");
            statement.setInt(1, id);
            rset = statement.executeQuery();
            if (rset.next()) {
                respuesta = new Jugo(rset.getString("nombre"));
                respuesta.setId(rset.getInt("id"));
            } else {
                throw new ObjetoNoExistenteException("No existe la sopa con ID dado");
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return respuesta;
    }
    
}
