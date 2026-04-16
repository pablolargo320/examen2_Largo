package co.edu.poli.examen2_Largo.modelo;

public class Debito extends Tarjeta {

    private double saldo;

    public Debito(String n, String f, boolean e, Titular t, double saldo) {
        super(n, f, e, t);
        this.saldo = saldo;
    }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}