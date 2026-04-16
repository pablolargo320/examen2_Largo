package co.edu.poli.examen2_Largo.tests.integracion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Largo.modelo.*;
import co.edu.poli.examen2_Largo.servicios.DAOTarjeta;

public class TESTDAOTarjeta {

    @Test
    void create_credito() throws Exception {

        DAOTarjeta dao = new DAOTarjeta();
        Titular t = new Titular("T001", "Test");

        String numero = "999" + System.currentTimeMillis(); // 🔥 evita duplicados

        Credito c = new Credito(numero, "2025-12-25", true, t, 10000);

        String r = dao.create(c);

        assertTrue(r.contains("guardada"));
        assertNotNull(dao.readone(numero));
    }
}