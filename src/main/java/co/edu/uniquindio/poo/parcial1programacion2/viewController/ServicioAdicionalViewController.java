package co.edu.uniquindio.poo.parcial1programacion2.viewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.poo.parcial1programacion2.controllers.ServicioAdicionalController;
public class ServicioAdicionalViewController {
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtPrecio;
    @FXML private ComboBox<?> cbDisponibilidad;

    @FXML private TableView<?> tablaServicios;
    @FXML private TableColumn<?, ?> colCodigo;
    @FXML private TableColumn<?, ?> colNombre;
    @FXML private TableColumn<?, ?> colDescripcion;
    @FXML private TableColumn<?, ?> colDuracion;
    @FXML private TableColumn<?, ?> colPrecio;
    @FXML private TableColumn<?, ?> colDisponibilidad;

    @FXML private Label lblMensaje;

    // Instancia del controlador lógico
    private final ServicioAdicionalController servicioAdicionalController = new ServicioAdicionalController();

    // Métodos que responden a eventos del FXML y delegan en ServicioAdicionalController
    @FXML
    private void registrarServicio() {
        servicioAdicionalController.registrarServicio();
    }

    @FXML
    private void buscarServicio() {
        servicioAdicionalController.buscarServicio();
    }

    @FXML
    private void eliminarServicio() {
        servicioAdicionalController.eliminarServicio();
    }

    @FXML
    private void limpiarCampos() {
        servicioAdicionalController.limpiarCampos();
    }

    @FXML
    private void volverAlInicio() {
        servicioAdicionalController.volverAlInicio();
    }
}
