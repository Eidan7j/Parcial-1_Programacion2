package co.edu.uniquindio.poo.parcial1programacion2.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.poo.parcial1programacion2.controllers.PagoController;
public class PagoViewController {

        @FXML private ComboBox<?> cbMatricula;
        @FXML private TextField txtIdPago;
        @FXML private TextField txtMontoPago;
        @FXML private DatePicker dpFechaPago;

        @FXML private TableView<?> tablaPagos;
        @FXML private TableColumn<?, ?> colIdPago;
        @FXML private TableColumn<?, ?> colMontoPago;
        @FXML private TableColumn<?, ?> colFechaPago;

        @FXML private Label lblTotalMatricula;
        @FXML private Label lblMensaje;

        // Instancia del controlador lógico
        private final PagoController pagoController = new PagoController();

        // Métodos que responden a eventos del FXML y delegan en PagoController
        @FXML
        private void registrarPago() {
        pagoController.registrarPago();
    }

        @FXML
        private void modificarPagoExistente() {
        pagoController.modificarPagoExistente();
    }

        @FXML
        private void limpiarCampos() {
        pagoController.limpiarCampos();
    }

        @FXML
        private void volverAlInicio() {
        pagoController.volverAlInicio();
    }

}
