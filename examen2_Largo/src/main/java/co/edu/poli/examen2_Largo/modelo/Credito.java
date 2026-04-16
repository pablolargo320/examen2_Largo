package co.edu.poli.examen2_Largo.modelo;

public class Credito extends Tarjeta {

    private double limite;

    public Credito(String n, String f, boolean e, Titular t, double limite) {
        super(n, f, e, t);
        this.limite = limite;
    }

    public double getLimite() { return limite; }
    public void setLimite(double limite) { this.limite = limite; }
}