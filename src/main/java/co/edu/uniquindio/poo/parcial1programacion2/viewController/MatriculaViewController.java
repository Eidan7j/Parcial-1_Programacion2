package co.edu.uniquindio.poo.parcial1programacion2.viewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.poo.parcial1programacion2.controllers.MatriculaController;
public class MatriculaViewController {


        @FXML private TextField txtCodigoMatricula;
        @FXML private DatePicker dpFechaMatricula;
        @FXML private ComboBox<?> cbEstudiante;
        @FXML private ComboBox<?> cbCurso;
        @FXML private ComboBox<?> cbServicioAdicional;

        @FXML private TableView<?> tablaMatriculas;
        @FXML private TableColumn<?, ?> colCodigo;
        @FXML private TableColumn<?, ?> colFecha;
        @FXML private TableColumn<?, ?> colEstudiante;
        @FXML private TableColumn<?, ?> colCurso;
        @FXML private TableColumn<?, ?> colServicios;
        @FXML private TableColumn<?, ?> colTotalPagado;

        @FXML private Label lblMensaje;

        // Instancia del controlador lógico
        private final MatriculaController matriculaController = new MatriculaController();

        // Métodos que responden a eventos del FXML y delegan en MatriculaController
        @FXML
        private void registrarMatricula() {
        matriculaController.registrarMatricula();
    }

        @FXML
        private void agregarServicioAMatricula() {
        matriculaController.agregarServicioAMatricula();
    }

        @FXML
        private void calcularTotalMatricula() {
        matriculaController.calcularTotalMatricula();
    }

        @FXML
        private void irAVistaPagos() {
        matriculaController.irAVistaPagos();
    }

        @FXML
        private void limpiarCampos() {
        matriculaController.limpiarCampos();
    }

        @FXML
        private void volverAlInicio() {
        matriculaController.volverAlInicio();
    }

}
