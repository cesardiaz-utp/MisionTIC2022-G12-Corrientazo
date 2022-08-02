package co.edu.utp.misiontic.cesardiaz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// DAO -> Data Access Object

public class JDBCUtilities {

    private static final String BASE_DE_DATOS = "C:\\Users\\ROG\\OneDrive\\Escritorio\\ProyectosCiclo2\\Grupo12\\corrientazo\\src\\main\\resources\\corrientazo.db";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            var url = "jdbc:sqlite:" + BASE_DE_DATOS;
            connection = DriverManager.getConnection(url);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    public static <T> List<T> listar(String sql, Function<ResultSet, T> function)
            throws SQLException {
        List<T> response;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            stmt = getConnection().prepareStatement(sql);
            rset = stmt.executeQuery();

            response = new ArrayList<>();
            while (rset.next()) {
                response.add(function.apply(rset));
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return response;
    }
}
