package co.edu.poli.examen2_Largo.servicios;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    public static Connection getConexion() {
        try {
            String url = "jdbc:mysql://localhost:3306/examen2_Largo";
            String user = "root";
            String password = "";

            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}