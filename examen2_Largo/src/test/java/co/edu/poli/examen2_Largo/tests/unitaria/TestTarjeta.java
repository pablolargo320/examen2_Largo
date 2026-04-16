package co.edu.poli.examen2_Largo.tests.unitaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Largo.modelo.*;

public class TestTarjeta {

    @Test
    void bloquear() {
        Tarjeta t = new Debito("1", "2025-12-25", true, new Titular("1","A"), 100);
        t.bloquear();
        assertFalse(t.isEstado());
    }

    @Test
    void activar() {
        Tarjeta t = new Debito("1", "2025-12-25", false, new Titular("1","A"), 100);
        t.activar();
        assertTrue(t.isEstado());
    }
}