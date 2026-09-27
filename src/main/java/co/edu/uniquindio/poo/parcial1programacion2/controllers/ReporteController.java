package co.edu.uniquindio.poo.parcial1programacion2.controllers;

import co.edu.uniquindio.poo.parcial1programacion2.App;
import co.edu.uniquindio.poo.parcial1programacion2.model.Matricula;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ReporteController {

    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private Label lblResultadoRango;
    @FXML private Label lblTotalGeneralAcademia;

    @FXML private TableView<Matricula> tablaMatriculasRango;
    @FXML private TableColumn<Matricula, String> colCodigo;
    @FXML private TableColumn<Matricula, String> colFecha;
    @FXML private TableColumn<Matricula, String> colEstudiante;
    @FXML private TableColumn<Matricula, String> colCurso;
    @FXML private TableColumn<Matricula, Double> colTotalPagado;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final ObservableList<Matricula> matriculasFiltradas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        dpFechaInicio.setValue(LocalDate.now().minusMonths(1));
        dpFechaFin.setValue(LocalDate.now().plusDays(1));

        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigoMatricula()));
        colFecha.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getFechaMatricula() != null ? sdf.format(c.getValue().getFechaMatricula()) : ""));
        colEstudiante.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getEstudiante() != null ? c.getValue().getEstudiante().getNombreEstudiante() : ""));
        colCurso.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getCurso() != null ? c.getValue().getCurso().getNombreCurso() : ""));
        colTotalPagado.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().calcularValorTotalMatricula()).asObject());

        tablaMatriculasRango.setItems(matriculasFiltradas);

        mostrarIngresosTotalesAcademia();
        generarReportePorFechas();
    }

    @FXML
    public void generarReportePorFechas() {
        if (dpFechaInicio.getValue() == null || dpFechaFin.getValue() == null) {
            lblResultadoRango.setText("Seleccione fecha de inicio y fecha de fin.");
            return;
        }
        Date fechaInicio = Date.from(dpFechaInicio.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaFin = Date.from(dpFechaFin.getValue().atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());

        double ingresos = App.getReporte().generarReporteIngresos(fechaInicio, fechaFin, App.getListaMatriculas());
        lblResultadoRango.setText("Ingresos en el rango seleccionado: $" + ingresos);

        matriculasFiltradas.clear();
        for (Matricula m : App.getListaMatriculas()) {
            Date fm = m.getFechaMatricula();
            if ((fm.equals(fechaInicio) || fm.after(fechaInicio)) &&
                (fm.equals(fechaFin) || fm.before(fechaFin))) {
                matriculasFiltradas.add(m);
            }
        }
    }

    @FXML
    public void mostrarIngresosTotalesAcademia() {
        lblTotalGeneralAcademia.setText(App.getAcademia().mostrarIngresosGenerados());
    }

    @FXML
    public void volverAlInicio() {
        App.cambiarVista("inicio.fxml", "Academia de Idiomas - Panel Principal");
    }
}
