package co.edu.uniquindio.poo.parcial1programacion2.controllers;

import co.edu.uniquindio.poo.parcial1programacion2.App;
import co.edu.uniquindio.poo.parcial1programacion2.model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

public class MatriculaController {

    @FXML private TextField txtCodigoMatricula;
    @FXML private DatePicker dpFechaMatricula;
    @FXML private ComboBox<Estudiante> cbEstudiante;
    @FXML private ComboBox<Curso> cbCurso;
    @FXML private ComboBox<ServicioAdicional> cbServicioAdicional;

    @FXML private TableView<Matricula> tablaMatriculas;
    @FXML private TableColumn<Matricula, String> colCodigo;
    @FXML private TableColumn<Matricula, String> colFecha;
    @FXML private TableColumn<Matricula, String> colEstudiante;
    @FXML private TableColumn<Matricula, String> colCurso;
    @FXML private TableColumn<Matricula, String> colServicios;
    @FXML private TableColumn<Matricula, Double> colTotalPagado;

    @FXML private Label lblMensaje;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    public void initialize() {
        dpFechaMatricula.setValue(LocalDate.now());

        cbEstudiante.setItems(App.getListaEstudiantes());
        cbEstudiante.setConverter(new StringConverter<>() {
            @Override
            public String toString(Estudiante e) {
                return e == null ? "" : e.getDocumentoEstudiante() + " - " + e.getNombreEstudiante();
            }
            @Override
            public Estudiante fromString(String s) { return null; }
        });

        cbCurso.setItems(App.getListaCursos());
        cbCurso.setConverter(new StringConverter<>() {
            @Override
            public String toString(Curso c) {
                return c == null ? "" : c.getCodigoCurso() + " - " + c.getNombreCurso() + " ($" + c.getValorMensualCurso() + ")";
            }
            @Override
            public Curso fromString(String s) { return null; }
        });

        cbServicioAdicional.setItems(App.getListaServicios());
        cbServicioAdicional.setConverter(new StringConverter<>() {
            @Override
            public String toString(ServicioAdicional s) {
                return s == null ? "" : s.getCodigoServicioAdicional() + " - " + s.getNombreServicioAdicional() + " (" + s.getDisponibilidadServicioAdicional() + ")";
            }
            @Override
            public ServicioAdicional fromString(String s) { return null; }
        });

        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigoMatricula()));
        colFecha.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getFechaMatricula() != null ? sdf.format(c.getValue().getFechaMatricula()) : ""));
        colEstudiante.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getEstudiante() != null ? c.getValue().getEstudiante().getNombreEstudiante() : ""));
        colCurso.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getCurso() != null ? c.getValue().getCurso().getNombreCurso() : ""));
        colServicios.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getListaServiciosAdicionales().stream()
                        .map(ServicioAdicional::getNombreServicioAdicional)
                        .collect(Collectors.joining(", "))
        ));
        colTotalPagado.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().calcularValorTotalMatricula()).asObject());

        tablaMatriculas.setItems(App.getListaMatriculas());
    }

    @FXML
    public void registrarMatricula() {
        if (txtCodigoMatricula.getText().isBlank() || cbEstudiante.getValue() == null || cbCurso.getValue() == null) {
            mostrarMensaje("Complete el código, seleccione un estudiante y un curso.", true);
            return;
        }
        String codigo = txtCodigoMatricula.getText().trim();
        boolean existe = App.getListaMatriculas().stream().anyMatch(m -> m.getCodigoMatricula().equalsIgnoreCase(codigo));
        if (existe) {
            mostrarMensaje("Ya existe una matrícula con el código " + codigo, true);
            return;
        }

        LocalDate localDate = dpFechaMatricula.getValue() != null ? dpFechaMatricula.getValue() : LocalDate.now();
        Date fecha = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Matricula nueva = new Matricula(codigo, fecha, cbEstudiante.getValue(), cbCurso.getValue());
        App.registrarMatricula(nueva);
        tablaMatriculas.refresh();
        tablaMatriculas.getSelectionModel().select(nueva);
        limpiarCampos();
        mostrarMensaje("Matrícula " + codigo + " creada exitosamente.", false);
    }

    @FXML
    public void agregarServicioAMatricula() {
        Matricula seleccionada = tablaMatriculas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarMensaje("Seleccione primero una matrícula en la tabla.", true);
            return;
        }
        ServicioAdicional servicio = cbServicioAdicional.getValue();
        if (servicio == null) {
            mostrarMensaje("Seleccione un servicio adicional del listado.", true);
            return;
        }

        int antes = seleccionada.getListaServiciosAdicionales().size();
        seleccionada.agregarServicioAdicional(servicio);
        int despues = seleccionada.getListaServiciosAdicionales().size();
        tablaMatriculas.refresh();

        if (despues > antes) {
            mostrarMensaje("Servicio '" + servicio.getNombreServicioAdicional() + "' agregado a la matrícula " + seleccionada.getCodigoMatricula(), false);
        } else {
            mostrarMensaje("El servicio '" + servicio.getNombreServicioAdicional() + "' NO está disponible.", true);
        }
    }

    @FXML
    public void calcularTotalMatricula() {
        Matricula seleccionada = tablaMatriculas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarMensaje("Seleccione una matrícula de la tabla para calcular su total.", true);
            return;
        }
        double total = seleccionada.calcularValorTotalMatricula();
        mostrarMensaje("Total pagado en matrícula " + seleccionada.getCodigoMatricula() + ": $" + total, false);
    }

    @FXML
    public void irAVistaPagos() {
        App.cambiarVista("pago.fxml", "Academia - Creación y Gestión de Pagos");
    }

    @FXML
    public void limpiarCampos() {
        txtCodigoMatricula.clear();
        dpFechaMatricula.setValue(LocalDate.now());
        cbEstudiante.setValue(null);
        cbCurso.setValue(null);
        cbServicioAdicional.setValue(null);
    }

    @FXML
    public void volverAlInicio() {
        App.cambiarVista("inicio.fxml", "Academia de Idiomas - Panel Principal");
    }

    private void mostrarMensaje(String msg, boolean error) {
        lblMensaje.setText(msg);
        lblMensaje.setStyle(error
                ? "-fx-text-fill: #dc2626; -fx-font-weight: bold;"
                : "-fx-text-fill: #059669; -fx-font-weight: bold;");
    }
}
