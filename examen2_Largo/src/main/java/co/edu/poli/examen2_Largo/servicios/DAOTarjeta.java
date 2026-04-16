package co.edu.poli.examen2_Largo.servicios;

import java.sql.*;
import java.util.List;

import co.edu.poli.examen2_Largo.modelo.*;

public class DAOTarjeta implements CRUD<Tarjeta> {

    @Override
    public String create(Tarjeta t) throws Exception {

        Connection con = ConexionBD.getConexion();
        con.setAutoCommit(false);

        try {

            String sqlTarjeta = "INSERT INTO tarjeta (numero, fecha_exp, estado, titular_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sqlTarjeta);

            ps.setString(1, t.getNumero());
            ps.setString(2, t.getFechaExp());
            ps.setBoolean(3, t.isEstado());
            ps.setString(4, t.getTitular().getId());
            ps.executeUpdate();

            if (t instanceof Debito) {
                String sql = "INSERT INTO tarjeta_debito (numero, saldo) VALUES (?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, t.getNumero());
                ps.setDouble(2, ((Debito) t).getSaldo());
                ps.executeUpdate();
            } else if (t instanceof Credito) {
                String sql = "INSERT INTO tarjeta_credito (numero, limite) VALUES (?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, t.getNumero());
                ps.setDouble(2, ((Credito) t).getLimite());
                ps.executeUpdate();
            }

            con.commit();
            return "guardada";

        } catch (Exception e) {
            con.rollback();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public <K> Tarjeta readone(K num) throws Exception {

        Connection con = ConexionBD.getConexion();

        // DEBITO
        String sqlD = "SELECT t.*, ti.nombre, d.saldo FROM tarjeta t " +
                "JOIN titular ti ON t.titular_id = ti.id " +
                "JOIN tarjeta_debito d ON t.numero = d.numero WHERE t.numero = ?";

        PreparedStatement ps = con.prepareStatement(sqlD);
        ps.setString(1, (String) num);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Debito(
                    rs.getString("numero"),
                    rs.getString("fecha_exp"),
                    rs.getBoolean("estado"),
                    new Titular(rs.getString("titular_id"), rs.getString("nombre")),
                    rs.getDouble("saldo")
            );
        }

        // CREDITO
        String sqlC = "SELECT t.*, ti.nombre, c.limite FROM tarjeta t " +
                "JOIN titular ti ON t.titular_id = ti.id " +
                "JOIN tarjeta_credito c ON t.numero = c.numero WHERE t.numero = ?";

        ps = con.prepareStatement(sqlC);
        ps.setString(1, (String) num);
        rs = ps.executeQuery();

        if (rs.next()) {
            return new Credito(
                    rs.getString("numero"),
                    rs.getString("fecha_exp"),
                    rs.getBoolean("estado"),
                    new Titular(rs.getString("titular_id"), rs.getString("nombre")),
                    rs.getDouble("limite")
            );
        }

        return null;
    }

    @Override
    public List<Tarjeta> readall() {
        return null;
    }
}