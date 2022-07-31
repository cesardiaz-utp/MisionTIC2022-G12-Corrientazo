package co.edu.utp.misiontic.cesardiaz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
