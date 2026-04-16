package co.edu.poli.examen2_Largo.servicios;

import java.sql.*;
import java.util.*;

import co.edu.poli.examen2_Largo.modelo.*;

public class DAOTitular {

    public List<Titular> readall() throws Exception {

        List<Titular> lista = new ArrayList<>();

        Connection con = ConexionBD.getConexion();

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM titular");

        while (rs.next()) {
            lista.add(new Titular(
                    rs.getString("id"),
                    rs.getString("nombre")
            ));
        }

        return lista;
    }
}