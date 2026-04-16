package co.edu.poli.examen2_Largo.tests.integracion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Largo.modelo.Titular;

public class DAOTitular {

    private final co.edu.poli.examen2_Largo.servicios.DAOTitular dao =
            new co.edu.poli.examen2_Largo.servicios.DAOTitular();

    @Test
    void readAll_noDebeRetornarNull() throws Exception {
        List<Titular> lista = dao.readall();
        assertNotNull(lista);
    }

    @Test
    void readAll_listaInicializada() throws Exception {
        List<Titular> lista = dao.readall();
        assertTrue(lista.size() >= 0);
    }

    @Test
    void readAll_objetosValidos() throws Exception {
        List<Titular> lista = dao.readall();

        if (!lista.isEmpty()) {
            Titular t = lista.get(0);
            assertNotNull(t.getId());
            assertNotNull(t.getNombre());
        }
    }
}