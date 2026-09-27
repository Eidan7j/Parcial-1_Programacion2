package co.edu.uniquindio.poo.parcial1programacion2.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.poo.parcial1programacion2.controllers.ReporteController;
public class ReporteViewController {
    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private Label lblResultadoRango;
    @FXML private Label lblTotalGeneralAcademia;

    @FXML private TableView<?> tablaMatriculasRango;
    @FXML private TableColumn<?, ?> colCodigo;
    @FXML private TableColumn<?, ?> colFecha;
    @FXML private TableColumn<?, ?> colEstudiante;
    @FXML private TableColumn<?, ?> colCurso;
    @FXML private TableColumn<?, ?> colTotalPagado;

    // Instancia del controlador lógico
    private final ReporteController reporteController = new ReporteController();

    // Métodos que responden a eventos del FXML y delegan en ReporteController
    @FXML
    private void generarReportePorFechas() {
        reporteController.generarReportePorFechas();
    }

    @FXML
    private void mostrarIngresosTotalesAcademia() {
        reporteController.mostrarIngresosTotalesAcademia();
    }

    @FXML
    private void volverAlInicio() {
        reporteController.volverAlInicio();
    }
}
