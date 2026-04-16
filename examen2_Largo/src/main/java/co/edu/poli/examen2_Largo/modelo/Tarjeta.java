package co.edu.poli.examen2_Largo.modelo;

public class Tarjeta {

    private String numero;
    private String fechaExp;
    private boolean estado;
    private Titular titular;

    public Tarjeta() {}

    public Tarjeta(String numero, String fechaExp, boolean estado, Titular titular) {
        this.numero = numero;
        this.fechaExp = fechaExp;
        this.estado = estado;
        this.titular = titular;
    }

    // GETTERS
    public String getNumero() { return numero; }
    public String getFechaExp() { return fechaExp; }
    public boolean isEstado() { return estado; }
    public Titular getTitular() { return titular; }

    // SETTERS
    public void setNumero(String numero) { this.numero = numero; }
    public void setFechaExp(String fechaExp) { this.fechaExp = fechaExp; }
    public void setEstado(boolean estado) { this.estado = estado; }
    public void setTitular(Titular titular) { this.titular = titular; }

    // MÉTODOS
    public void bloquear() { this.estado = false; }
    public void activar() { this.estado = true; }

    @Override
    public String toString() {
        return numero + " - " + fechaExp + " - " + estado;
    }
}