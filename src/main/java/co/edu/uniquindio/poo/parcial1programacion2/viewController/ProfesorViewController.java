package co.edu.uniquindio.poo.parcial1programacion2.viewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.poo.parcial1programacion2.controllers.ProfesorController;
public class ProfesorViewController {
    @FXML private TextField txtIdProfesor;
    @FXML private TextField txtNombreProfesor;
    @FXML private TextField txtIdiomaProfesor;
    @FXML private TextField txtTelefonoProfesor;
    @FXML private TextField txtTarifaSesion;

    @FXML private ComboBox<String> cbCampoActualizar;
    @FXML private TextField txtNuevoValor;

    @FXML private TableView<?> tablaProfesores;
    @FXML private TableColumn<?, ?> colId;
    @FXML private TableColumn<?, ?> colNombre;
    @FXML private TableColumn<?, ?> colIdioma;
    @FXML private TableColumn<?, ?> colTelefono;
    @FXML private TableColumn<?, ?> colTarifa;

    @FXML private Label lblMensaje;

    // Instancia del controlador lógico
    private final ProfesorController profesorController = new ProfesorController();

    // Métodos que responden a eventos del FXML y delegan en ProfesorController
    @FXML
    private void registrarProfesor() {
        profesorController.registrarProfesor();
    }

    @FXML
    private void buscarProfesor() {
        profesorController.buscarProfesor();
    }

    @FXML
    private void actualizarDatosProfesor() {
        profesorController.actualizarDatosProfesor();
    }

    @FXML
    private void eliminarProfesor() {
        profesorController.eliminarProfesor();
    }

    @FXML
    private void limpiarCampos() {
        profesorController.limpiarCampos();
    }

    @FXML
    private void volverAlInicio() {
        profesorController.volverAlInicio();
    }
}
