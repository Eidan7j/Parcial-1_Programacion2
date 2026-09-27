package co.edu.uniquindio.poo.parcial1programacion2.viewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.poo.parcial1programacion2.controllers.EstudianteController;

public class EstudianteViewController {

        // Campos del formulario
        @FXML private TextField txtNombre;
        @FXML private TextField txtDocumento;
        @FXML private TextField txtTelefono;
        @FXML private TextField txtCorreo;
        @FXML private TextField txtEdad;
        @FXML private DatePicker dpFechaRegistro;
        @FXML private ComboBox<?> cbNivelReferencia;
        @FXML private ComboBox<?> cbTipoEstudiante;

        @FXML private ComboBox<String> cbCampoActualizar;
        @FXML private TextField txtNuevoValor;

        // Tabla
        @FXML private TableView<?> tablaEstudiantes;
        @FXML private TableColumn<?, ?> colDocumento;
        @FXML private TableColumn<?, ?> colNombre;
        @FXML private TableColumn<?, ?> colTelefono;
        @FXML private TableColumn<?, ?> colCorreo;
        @FXML private TableColumn<?, ?> colEdad;
        @FXML private TableColumn<?, ?> colNivel;
        @FXML private TableColumn<?, ?> colTipo;
        @FXML private TableColumn<?, ?> colFecha;

        @FXML private Label lblMensaje;

        // Instancia del controlador lógico
        private final EstudianteController estudianteController = new EstudianteController();

        // Métodos que responden a eventos del FXML
        @FXML
        private void registrarEstudiante() {
            estudianteController.registrarEstudiante();
        }

        @FXML
        private void buscarEstudiante() {
            estudianteController.buscarEstudiante();
        }

        @FXML
        private void actualizarDatoEstudiante() {
            estudianteController.actualizarDatoEstudiante();
        }

        @FXML
        private void clonarEstudiantePrototype() {
            estudianteController.clonarEstudiantePrototype();
        }

        @FXML
        private void eliminarEstudiante() {
            estudianteController.eliminarEstudiante();
        }

        @FXML
        private void limpiarCampos() {
            estudianteController.limpiarCampos();
        }

        @FXML
        private void volverAlInicio() {
            estudianteController.volverAlInicio();
        }
    }


