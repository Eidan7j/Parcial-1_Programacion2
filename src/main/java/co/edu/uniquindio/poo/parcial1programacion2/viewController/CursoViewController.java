package co.edu.uniquindio.poo.parcial1programacion2.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import co.edu.uniquindio.poo.parcial1programacion2.controllers.CursoController;

public class CursoViewController {

    // Campos del formulario
    @FXML private ComboBox<String> cbTipoCurso;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtIdioma;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtValorMensual;
    @FXML private ComboBox<?> cbEstadoCurso;

    // Paneles
    @FXML private VBox boxCursoRegular;
    @FXML private TextField txtNivelRegular;
    @FXML private TextField txtHorarioRegular;

    @FXML private VBox boxCursoPersonalizado;
    @FXML private TextField txtCantidadSesiones;
    @FXML private TextField txtNivelPersonalizado;
    @FXML private TextField txtTipoNivelEstudiante;
    @FXML private ComboBox<?> cbProfesor;
    @FXML private ComboBox<?> cbEstudiante;

    @FXML private TextField txtModalidadEnsenanza;

    // Tabla
    @FXML private TableView<?> tablaCursos;
    @FXML private TableColumn<?, ?> colCodigo;
    @FXML private TableColumn<?, ?> colTipo;
    @FXML private TableColumn<?, ?> colNombre;
    @FXML private TableColumn<?, ?> colIdioma;
    @FXML private TableColumn<?, ?> colDuracion;
    @FXML private TableColumn<?, ?> colValor;
    @FXML private TableColumn<?, ?> colEstado;
    @FXML private TableColumn<?, ?> colDetalle;

    @FXML private Label lblMensaje;

    // Instancia del controlador lógico
    private final CursoController cursoController = new CursoController();

    // Métodos que responden a eventos del FXML
    @FXML
    private void registrarCurso() {
        cursoController.registrarCurso();
    }

    @FXML
    private void buscarCurso() {
        cursoController.buscarCurso();
    }

    @FXML
    private void eliminarCurso() {
        cursoController.eliminarCurso();
    }

    @FXML
    private void limpiarCampos() {
        cursoController.limpiarCampos();
    }

    @FXML
    private void asignarModalidadEnsenanza() {
        cursoController.asignarModalidadEnsenanza();
    }

    @FXML
    private void volverAlInicio() {
        cursoController.volverAlInicio();
    }
}

