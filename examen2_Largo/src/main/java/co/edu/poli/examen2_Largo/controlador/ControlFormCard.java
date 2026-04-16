package co.edu.poli.examen2_Largo.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

import co.edu.poli.examen2_Largo.modelo.*;
import co.edu.poli.examen2_Largo.servicios.*;

public class ControlFormCard {

    @FXML private TextField txtNumero;
    @FXML private DatePicker dpFecha;
    @FXML private ComboBox<Titular> cbTitular;

    @FXML private RadioButton rbDebito;
    @FXML private RadioButton rbCredito;

    @FXML private TextField txtBuscarNumero;

    @FXML private Label lblResultadoCrear;
    @FXML private Label lblResultadoConsulta;

    private DAOTarjeta daoTarjeta = new DAOTarjeta();
    private DAOTitular daoTitular = new DAOTitular();

    @FXML
    public void initialize() {
        ToggleGroup grupo = new ToggleGroup();
        rbDebito.setToggleGroup(grupo);
        rbCredito.setToggleGroup(grupo);

        cargarTitulares();
    }

    // 🔥 CARGAR TITULARES DESDE MYSQL
    private void cargarTitulares() {
        try {
            List<Titular> lista = daoTitular.readall();
            cbTitular.getItems().addAll(lista);
        } catch (Exception e) {
            System.out.println("Error cargando titulares: " + e.getMessage());
        }
    }

    // ================= CREAR =================
    @FXML
    public void crear() {
        try {
            String numero = txtNumero.getText();
            LocalDate fecha = dpFecha.getValue();
            Titular titular = cbTitular.getValue();

            if (fecha == null || titular == null) {
                lblResultadoCrear.setText("Complete todos los campos");
                return;
            }

            Tarjeta t;

            if (rbDebito.isSelected()) {
                t = new Debito(numero, fecha.toString(), true, titular, 0);
            } else {
                t = new Credito(numero, fecha.toString(), true, titular, 0);
            }

            String res = daoTarjeta.create(t);
            lblResultadoCrear.setText(res);

        } catch (Exception e) {
            lblResultadoCrear.setText("Error: " + e.getMessage());
        }
    }

    // ================= CONSULTAR =================
    @FXML
    public void consultar() {
        try {
            String numero = txtBuscarNumero.getText();

            Tarjeta t = daoTarjeta.readone(numero);

            if (t == null) {
                lblResultadoConsulta.setText("No existe");
                return;
            }

            String info = "Número: " + t.getNumero()
                    + "\nFecha: " + t.getFechaExp()
                    + "\nTitular: " + t.getTitular().getNombre()
                    + "\nEstado: " + (t.isEstado() ? "Activa" : "Bloqueada");

            if (t instanceof Debito) {
                info += "\nTipo: Débito\nSaldo: " + ((Debito) t).getSaldo();
            } else {
                info += "\nTipo: Crédito\nLímite: " + ((Credito) t).getLimite();
            }

            lblResultadoConsulta.setText(info);

        } catch (Exception e) {
            lblResultadoConsulta.setText("Error: " + e.getMessage());
        }
    }
}