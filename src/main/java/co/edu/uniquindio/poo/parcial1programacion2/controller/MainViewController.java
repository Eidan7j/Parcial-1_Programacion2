package co.edu.uniquindio.poo.parcial1programacion2.controller;

import co.edu.uniquindio.poo.parcial1programacion2.model.*;
import co.edu.uniquindio.poo.parcial1programacion2.model.enums.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

public class MainViewController {

    private LenguajeCafetero academia;

    // Listas observables
    private final ObservableList<Estudiante> estudiantesObservable = FXCollections.observableArrayList();
    private final ObservableList<Profesor> profesoresObservable = FXCollections.observableArrayList();
    private final ObservableList<Curso> cursosObservable = FXCollections.observableArrayList();
    private final ObservableList<ServicioAdicional> serviciosObservable = FXCollections.observableArrayList();
    private final ObservableList<Matricula> matriculasObservable = FXCollections.observableArrayList();
    private final ObservableList<Matricula> reporteMatriculasObservable = FXCollections.observableArrayList();
    private final ObservableList<Matricula> estudianteMatriculasObservable = FXCollections.observableArrayList();

    private final NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    // --- DASHBOARD ---
    @FXML private Label lblDashEstudiantes;
    @FXML private Label lblDashCursos;
    @FXML private Label lblDashProfesores;
    @FXML private Label lblDashMatriculas;
    @FXML private Label lblDashTotalIngresos;
    @FXML private Label lblAcademiaInfo;
    @FXML private TableView<Matricula> tblDashUltimasMatriculas;
    @FXML private TableColumn<Matricula, String> colDashCodigo;
    @FXML private TableColumn<Matricula, String> colDashEstudiante;
    @FXML private TableColumn<Matricula, String> colDashCurso;
    @FXML private TableColumn<Matricula, String> colDashFecha;
    @FXML private TableColumn<Matricula, String> colDashTotal;

    // --- ESTUDIANTES ---
    @FXML private TextField txtEstDocumento;
    @FXML private TextField txtEstNombre;
    @FXML private TextField txtEstTelefono;
    @FXML private TextField txtEstCorreo;
    @FXML private Spinner<Integer> spnEstEdad;
    @FXML private DatePicker dpEstFechaRegistro;
    @FXML private TextField txtEstBuscar;
    @FXML private TableView<Estudiante> tblEstudiantes;
    @FXML private TableColumn<Estudiante, String> colEstDocumento;
    @FXML private TableColumn<Estudiante, String> colEstNombre;
    @FXML private TableColumn<Estudiante, String> colEstTelefono;
    @FXML private TableColumn<Estudiante, String> colEstCorreo;
    @FXML private TableColumn<Estudiante, Integer> colEstEdad;
    @FXML private TableColumn<Estudiante, String> colEstFecha;

    // --- PROFESORES ---
    @FXML private TextField txtProfId;
    @FXML private TextField txtProfNombre;
    @FXML private ComboBox<String> cmbProfIdioma;
    @FXML private TextField txtProfTelefono;
    @FXML private TextField txtProfTarifa;
    @FXML private TableView<Profesor> tblProfesores;
    @FXML private TableColumn<Profesor, String> colProfId;
    @FXML private TableColumn<Profesor, String> colProfNombre;
    @FXML private TableColumn<Profesor, String> colProfIdioma;
    @FXML private TableColumn<Profesor, String> colProfTelefono;
    @FXML private TableColumn<Profesor, String> colProfTarifa;

    // --- CURSOS ---
    @FXML private TextField txtCursoCodigo;
    @FXML private TextField txtCursoNombre;
    @FXML private ComboBox<String> cmbCursoIdioma;
    @FXML private TextField txtCursoDescripcion;
    @FXML private Spinner<Integer> spnCursoDuracion;
    @FXML private TextField txtCursoValorMensual;
    @FXML private ComboBox<EstadoCurso> cmbCursoEstado;
    @FXML private ComboBox<TipoCurso> cmbCursoTipo;
    @FXML private VBox boxCursoPersonalizado;
    @FXML private Spinner<Integer> spnCursoSesiones;
    @FXML private ComboBox<Nivel> cmbCursoNivel;
    @FXML private TextField txtCursoObjetivos;
    @FXML private TableView<Curso> tblCursos;
    @FXML private TableColumn<Curso, String> colCursoCodigo;
    @FXML private TableColumn<Curso, String> colCursoNombre;
    @FXML private TableColumn<Curso, String> colCursoIdioma;
    @FXML private TableColumn<Curso, String> colCursoTipo;
    @FXML private TableColumn<Curso, Integer> colCursoDuracion;
    @FXML private TableColumn<Curso, String> colCursoValorMensual;
    @FXML private TableColumn<Curso, String> colCursoEstado;
    @FXML private TableColumn<Curso, String> colCursoBeneficios;

    // --- SERVICIOS ADICIONALES ---
    @FXML private TextField txtServCodigo;
    @FXML private TextField txtServNombre;
    @FXML private TextField txtServDescripcion;
    @FXML private TextField txtServPrecio;
    @FXML private ComboBox<Disponibilidad> cmbServDisponibilidad;
    @FXML private TableView<ServicioAdicional> tblServicios;
    @FXML private TableColumn<ServicioAdicional, String> colServCodigo;
    @FXML private TableColumn<ServicioAdicional, String> colServNombre;
    @FXML private TableColumn<ServicioAdicional, String> colServDescripcion;
    @FXML private TableColumn<ServicioAdicional, String> colServPrecio;
    @FXML private TableColumn<ServicioAdicional, String> colServDisponibilidad;

    // --- MATRÍCULAS ---
    @FXML private TextField txtMatCodigo;
    @FXML private DatePicker dpMatFecha;
    @FXML private ComboBox<Estudiante> cmbMatEstudiante;
    @FXML private ComboBox<Curso> cmbMatCurso;
    @FXML private ComboBox<Profesor> cmbMatProfesor;
    @FXML private ListView<ServicioAdicional> lvMatServicios;
    @FXML private TextField txtMatDescuento;
    @FXML private Label lblMatCostoCurso;
    @FXML private Label lblMatCostoServicios;
    @FXML private Label lblMatMontoDescuento;
    @FXML private Label lblMatValorTotal;
    @FXML private TableView<Matricula> tblMatriculas;
    @FXML private TableColumn<Matricula, String> colMatCodigo;
    @FXML private TableColumn<Matricula, String> colMatFecha;
    @FXML private TableColumn<Matricula, String> colMatEstudiante;
    @FXML private TableColumn<Matricula, String> colMatCurso;
    @FXML private TableColumn<Matricula, String> colMatProfesor;
    @FXML private TableColumn<Matricula, String> colMatServicios;
    @FXML private TableColumn<Matricula, String> colMatDescuento;
    @FXML private TableColumn<Matricula, String> colMatTotal;
    @FXML private TableColumn<Matricula, String> colMatPagado;
    @FXML private TableColumn<Matricula, String> colMatSaldo;

    // --- CONSULTAS Y REPORTES ---
    // Consulta 1: Estudiante
    @FXML private TextField txtConsultaDocEstudiante;
    @FXML private Label lblConsEstNombre;
    @FXML private Label lblConsEstDocumento;
    @FXML private Label lblConsEstEdad;
    @FXML private Label lblConsEstTelefono;
    @FXML private Label lblConsEstCorreo;
    @FXML private Label lblConsEstFechaReg;
    @FXML private Label lblConsEstTotalMatriculas;
    @FXML private TableView<Matricula> tblConsultaEstMatriculas;
    @FXML private TableColumn<Matricula, String> colConsMatCodigo;
    @FXML private TableColumn<Matricula, String> colConsMatFecha;
    @FXML private TableColumn<Matricula, String> colConsMatCurso;
    @FXML private TableColumn<Matricula, String> colConsMatProfesor;
    @FXML private TableColumn<Matricula, String> colConsMatTotal;
    @FXML private TableColumn<Matricula, String> colConsMatSaldo;

    // Consulta 2: Ingresos por Período
    @FXML private DatePicker dpReporteFechaInicio;
    @FXML private DatePicker dpReporteFechaFin;
    @FXML private Label lblReporteTotalIngresos;
    @FXML private Label lblReporteCantidadMatriculas;
    @FXML private TableView<Matricula> tblReporteMatriculas;
    @FXML private TableColumn<Matricula, String> colRepCodigo;
    @FXML private TableColumn<Matricula, String> colRepFecha;
    @FXML private TableColumn<Matricula, String> colRepEstudiante;
    @FXML private TableColumn<Matricula, String> colRepCurso;
    @FXML private TableColumn<Matricula, String> colRepTotal;

    @FXML
    public void initialize() {
        // Inicializar modelo de negocio
        academia = new LenguajeCafetero(
                "Academia de Idiomas LenguajeCafetero",
                "NIT 901.847.231-5",
                "Carrera 14 # 21-35, Armenia, Quindío",
                "+57 (606) 745-9820",
                "contacto@lenguajecafetero.edu.co",
                "www.lenguajecafetero.edu.co"
        );
        academia.cargarDatosPrueba();

        // Configurar spinners y combos
        configurarSpinnersYCombos();

        // Configurar tablas
        configurarTablas();

        // Cargar listas observables
        sincronizarDatos();

        // Configurar listeners de cálculo en tiempo real para Matrículas
        configurarCalculoMatriculaEnVivo();

        // Configurar selección en tablas para cargar en formularios
        configurarSeleccionTablas();

        // Inicializar fechas por defecto en reportes
        dpReporteFechaInicio.setValue(LocalDate.now().withDayOfMonth(1).minusMonths(2));
        dpReporteFechaFin.setValue(LocalDate.now().plusMonths(1));
        ejecutarReporteIngresos();
    }

    private void configurarSpinnersYCombos() {
        // Estudiantes
        spnEstEdad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 20));
        dpEstFechaRegistro.setValue(LocalDate.now());

        // Profesores
        cmbProfIdioma.setItems(FXCollections.observableArrayList("Inglés", "Francés", "Portugués", "Alemán", "Italiano"));
        cmbProfIdioma.getSelectionModel().selectFirst();

        // Cursos
        cmbCursoIdioma.setItems(FXCollections.observableArrayList("Inglés", "Francés", "Portugués", "Alemán", "Italiano"));
        cmbCursoIdioma.getSelectionModel().selectFirst();
        cmbCursoEstado.setItems(FXCollections.observableArrayList(EstadoCurso.values()));
        cmbCursoEstado.getSelectionModel().select(EstadoCurso.ACTIVO);
        cmbCursoTipo.setItems(FXCollections.observableArrayList(TipoCurso.values()));
        cmbCursoTipo.getSelectionModel().select(TipoCurso.REGULAR);

        spnCursoDuracion.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 48, 6));
        spnCursoSesiones.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 10));
        cmbCursoNivel.setItems(FXCollections.observableArrayList(Nivel.values()));
        cmbCursoNivel.getSelectionModel().select(Nivel.B1);

        boxCursoPersonalizado.setVisible(false);
        boxCursoPersonalizado.setManaged(false);

        cmbCursoTipo.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean esPersonalizado = newVal == TipoCurso.PERSONALIZADO;
            boxCursoPersonalizado.setVisible(esPersonalizado);
            boxCursoPersonalizado.setManaged(esPersonalizado);
        });

        // Servicios Adicionales
        cmbServDisponibilidad.setItems(FXCollections.observableArrayList(Disponibilidad.values()));
        cmbServDisponibilidad.getSelectionModel().select(Disponibilidad.DISPONIBLE);

        // Matrículas
        dpMatFecha.setValue(LocalDate.now());
        generarSiguienteCodigoMatricula();

        lvMatServicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvMatServicios.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ServicioAdicional item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre() + " (" + formatoMoneda.format(item.getPrecio()) + ") - [" + item.getDisponibilidad() + "]");
                }
            }
        });
    }

    private void configurarTablas() {
        // Dashboard
        colDashCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colDashEstudiante.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEstudiante() != null ? cell.getValue().getEstudiante().getNombreCompleto() : ""));
        colDashCurso.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCurso() != null ? cell.getValue().getCurso().getNombre() : ""));
        colDashFecha.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFecha() != null ? cell.getValue().getFecha().toString() : ""));
        colDashTotal.setCellValueFactory(cell -> new SimpleStringProperty(formatoMoneda.format(cell.getValue().getValorTotal())));
        tblDashUltimasMatriculas.setItems(matriculasObservable);

        // Estudiantes
        colEstDocumento.setCellValueFactory(new PropertyValueFactory<>("documentoIdentidad"));
        colEstNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colEstTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEstCorreo.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        colEstEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colEstFecha.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFechaRegistro() != null ? cell.getValue().getFechaRegistro().toString() : ""));
        tblEstudiantes.setItems(estudiantesObservable);

        // Profesores
        colProfId.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        colProfNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colProfIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        colProfTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colProfTarifa.setCellValueFactory(cell -> new SimpleStringProperty(formatoMoneda.format(cell.getValue().getTarifaSesion())));
        tblProfesores.setItems(profesoresObservable);

        // Cursos
        colCursoCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colCursoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCursoIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        colCursoTipo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTipoCurso() != null ? cell.getValue().getTipoCurso().toString() : ""));
        colCursoDuracion.setCellValueFactory(new PropertyValueFactory<>("duracionMeses"));
        colCursoValorMensual.setCellValueFactory(cell -> new SimpleStringProperty(formatoMoneda.format(cell.getValue().getValorMensual())));
        colCursoEstado.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEstadoCurso() != null ? cell.getValue().getEstadoCurso().toString() : ""));
        colCursoBeneficios.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBeneficiosFormateados()));
        tblCursos.setItems(cursosObservable);

        // Servicios
        colServCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colServNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colServDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colServPrecio.setCellValueFactory(cell -> new SimpleStringProperty(formatoMoneda.format(cell.getValue().getPrecio())));
        colServDisponibilidad.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDisponibilidad() != null ? cell.getValue().getDisponibilidad().toString() : ""));
        tblServicios.setItems(serviciosObservable);

        // Matrículas
        colMatCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colMatFecha.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFecha() != null ? cell.getValue().getFecha().toString() : ""));
        colMatEstudiante.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEstudiante() != null ? cell.getValue().getEstudiante().getNombreCompleto() : ""));
        colMatCurso.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCurso() != null ? cell.getValue().getCurso().getNombre() : ""));
        colMatProfesor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getProfesor() != null ? cell.getValue().getProfesor().getNombre() : "Sin asignar"));
        colMatServicios.setCellValueFactory(cell -> {
            List<ServicioAdicional> srvs = cell.getValue().getServiciosAdicionales();
            return new SimpleStringProperty(srvs.isEmpty() ? "Ninguno" : String.valueOf(srvs.size()) + " servicio(s)");
        });
        colMatDescuento.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescuentoPorcentaje() + "%"));
        colMatTotal.setCellValueFactory(cell -> new SimpleStringProperty(formatoMoneda.format(cell.getValue().getValorTotal())));
        colMatPagado.setCellValueFactory(cell -> new SimpleStringProperty(formatoMoneda.format(cell.getValue().getTotalPagado())));
        colMatSaldo.setCellValueFactory(cell -> new SimpleStringProperty(formatoMoneda.format(cell.getValue().getSaldoPendiente())));
        tblMatriculas.setItems(matriculasObservable);

        // Reporte de Ingresos por Período
        colRepCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colRepFecha.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFecha() != null ? cell.getValue().getFecha().toString() : ""));
        colRepEstudiante.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEstudiante() != null ? cell.getValue().getEstudiante().getNombreCompleto() : ""));
        colRepCurso.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCurso() != null ? cell.getValue().getCurso().getNombre() : ""));
        colRepTotal.setCellValueFactory(cell -> new SimpleStringProperty(formatoMoneda.format(cell.getValue().getValorTotal())));
        tblReporteMatriculas.setItems(reporteMatriculasObservable);

        // Consulta de Estudiante
        colConsMatCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colConsMatFecha.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFecha() != null ? cell.getValue().getFecha().toString() : ""));
        colConsMatCurso.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCurso() != null ? cell.getValue().getCurso().getNombre() : ""));
        colConsMatProfesor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getProfesor() != null ? cell.getValue().getProfesor().getNombre() : "Sin asignar"));
        colConsMatTotal.setCellValueFactory(cell -> new SimpleStringProperty(formatoMoneda.format(cell.getValue().getValorTotal())));
        colConsMatSaldo.setCellValueFactory(cell -> new SimpleStringProperty(formatoMoneda.format(cell.getValue().getSaldoPendiente())));
        tblConsultaEstMatriculas.setItems(estudianteMatriculasObservable);
    }

    private void configurarSeleccionTablas() {
        tblEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs, oldV, est) -> {
            if (est != null) {
                txtEstDocumento.setText(est.getDocumentoIdentidad());
                txtEstNombre.setText(est.getNombreCompleto());
                txtEstTelefono.setText(est.getTelefono());
                txtEstCorreo.setText(est.getCorreoElectronico());
                spnEstEdad.getValueFactory().setValue(est.getEdad());
                dpEstFechaRegistro.setValue(est.getFechaRegistro());
            }
        });

        tblProfesores.getSelectionModel().selectedItemProperty().addListener((obs, oldV, prof) -> {
            if (prof != null) {
                txtProfId.setText(prof.getIdentificacion());
                txtProfNombre.setText(prof.getNombre());
                cmbProfIdioma.setValue(prof.getIdioma());
                txtProfTelefono.setText(prof.getTelefono());
                txtProfTarifa.setText(String.valueOf(prof.getTarifaSesion()));
            }
        });

        tblServicios.getSelectionModel().selectedItemProperty().addListener((obs, oldV, srv) -> {
            if (srv != null) {
                txtServCodigo.setText(srv.getCodigo());
                txtServNombre.setText(srv.getNombre());
                txtServDescripcion.setText(srv.getDescripcion());
                txtServPrecio.setText(String.valueOf(srv.getPrecio()));
                cmbServDisponibilidad.setValue(srv.getDisponibilidad());
            }
        });
    }

    private void configurarCalculoMatriculaEnVivo() {
        cmbMatCurso.valueProperty().addListener((obs, oldV, newCurso) -> {
            boolean esPersonalizado = (newCurso instanceof CursoPersonalizado);
            cmbMatProfesor.setPromptText(esPersonalizado ? "Seleccione profesor responsable *" : "Profesor (opcional)");
            actualizarCalculoMatricula();
        });

        cmbMatProfesor.valueProperty().addListener((obs, oldV, newV) -> actualizarCalculoMatricula());
        lvMatServicios.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> actualizarCalculoMatricula());
        txtMatDescuento.textProperty().addListener((obs, oldV, newV) -> actualizarCalculoMatricula());
    }

    private void actualizarCalculoMatricula() {
        Curso curso = cmbMatCurso.getValue();
        Profesor profesor = cmbMatProfesor.getValue();
        List<ServicioAdicional> seleccionados = lvMatServicios.getSelectionModel().getSelectedItems();

        double costoCurso = 0.0;
        if (curso != null) {
            costoCurso = curso.calcularCostoBase(profesor);
        }

        double costoServicios = 0.0;
        if (seleccionados != null) {
            for (ServicioAdicional s : seleccionados) {
                costoServicios += s.getPrecio();
            }
        }

        double subtotal = costoCurso + costoServicios;
        double descuentoPorcentaje = 0.0;
        try {
            if (txtMatDescuento.getText() != null && !txtMatDescuento.getText().trim().isEmpty()) {
                descuentoPorcentaje = Double.parseDouble(txtMatDescuento.getText().trim());
            }
        } catch (NumberFormatException ignored) {}

        double montoDescuento = subtotal * (descuentoPorcentaje / 100.0);
        double total = Math.max(0.0, subtotal - montoDescuento);

        lblMatCostoCurso.setText(formatoMoneda.format(costoCurso));
        lblMatCostoServicios.setText(formatoMoneda.format(costoServicios));
        lblMatMontoDescuento.setText(formatoMoneda.format(montoDescuento) + " (" + descuentoPorcentaje + "%)");
        lblMatValorTotal.setText(formatoMoneda.format(total));
    }

    private void sincronizarDatos() {
        estudiantesObservable.setAll(academia.getEstudiantes());
        profesoresObservable.setAll(academia.getProfesores());
        cursosObservable.setAll(academia.getCursos());
        serviciosObservable.setAll(academia.getServiciosAdicionales());
        matriculasObservable.setAll(academia.getMatriculas());

        // Actualizar ComboBoxes de matrícula
        cmbMatEstudiante.setItems(estudiantesObservable);
        cmbMatCurso.setItems(cursosObservable);
        cmbMatProfesor.setItems(profesoresObservable);
        lvMatServicios.setItems(serviciosObservable);

        // Actualizar Dashboard
        actualizarDashboard();
    }

    private void actualizarDashboard() {
        lblDashEstudiantes.setText(String.valueOf(academia.getEstudiantes().size()));
        lblDashCursos.setText(String.valueOf(academia.getCursos().stream().filter(c -> c.getEstadoCurso() == EstadoCurso.ACTIVO).count()));
        lblDashProfesores.setText(String.valueOf(academia.getProfesores().size()));
        lblDashMatriculas.setText(String.valueOf(academia.getMatriculas().size()));

        double totalRecaudado = academia.getMatriculas().stream().mapToDouble(Matricula::getTotalPagado).sum();
        lblDashTotalIngresos.setText(formatoMoneda.format(totalRecaudado));

        lblAcademiaInfo.setText(academia.getNombreComercial() + " | " + academia.getNit() + " | " + academia.getDireccion()
                + " | Tel: " + academia.getTelefono() + " | " + academia.getCorreoElectronico());
    }

    private void generarSiguienteCodigoMatricula() {
        int siguiente = academia.getMatriculas().size() + 1;
        txtMatCodigo.setText(String.format("MAT-2026-%03d", siguiente));
    }

    // =========================================================================
    // ACCIONES DE ESTUDIANTES
    // =========================================================================
    @FXML
    public void registrarEstudiante() {
        String doc = txtEstDocumento.getText();
        String nom = txtEstNombre.getText();
        String tel = txtEstTelefono.getText();
        String cor = txtEstCorreo.getText();
        int edad = spnEstEdad.getValue();
        LocalDate fec = dpEstFechaRegistro.getValue();

        if (doc == null || doc.trim().isEmpty() || nom == null || nom.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor ingrese el documento y nombre completo del estudiante.");
            return;
        }

        Estudiante nuevo = new Estudiante(doc.trim(), nom.trim(), tel != null ? tel.trim() : "", cor != null ? cor.trim() : "", edad, fec);
        boolean registrado = academia.registrarEstudiante(nuevo);
        if (registrado) {
            sincronizarDatos();
            limpiarFormEstudiante();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Estudiante registrado correctamente.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ya existe un estudiante con el documento " + doc);
        }
    }

    @FXML
    public void actualizarEstudiante() {
        String doc = txtEstDocumento.getText();
        if (doc == null || doc.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Seleccione o ingrese el documento del estudiante a actualizar.");
            return;
        }

        Estudiante est = new Estudiante(doc.trim(), txtEstNombre.getText().trim(), txtEstTelefono.getText().trim(),
                txtEstCorreo.getText().trim(), spnEstEdad.getValue(), dpEstFechaRegistro.getValue());

        if (academia.actualizarEstudiante(est)) {
            sincronizarDatos();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Estudiante actualizado correctamente.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se encontró el estudiante con documento " + doc);
        }
    }

    @FXML
    public void eliminarEstudiante() {
        Estudiante seleccionado = tblEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Seleccione un estudiante de la tabla para eliminar.");
            return;
        }
        academia.eliminarEstudiante(seleccionado.getDocumentoIdentidad());
        sincronizarDatos();
        limpiarFormEstudiante();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminado", "Estudiante eliminado exitosamente.");
    }

    @FXML
    public void limpiarFormEstudiante() {
        txtEstDocumento.clear();
        txtEstNombre.clear();
        txtEstTelefono.clear();
        txtEstCorreo.clear();
        spnEstEdad.getValueFactory().setValue(20);
        dpEstFechaRegistro.setValue(LocalDate.now());
        tblEstudiantes.getSelectionModel().clearSelection();
    }

    @FXML
    public void buscarEstudianteFiltro() {
        String query = txtEstBuscar.getText();
        if (query == null || query.trim().isEmpty()) {
            estudiantesObservable.setAll(academia.getEstudiantes());
        } else {
            String lower = query.trim().toLowerCase();
            List<Estudiante> filtrados = academia.getEstudiantes().stream()
                    .filter(e -> e.getDocumentoIdentidad().toLowerCase().contains(lower) || e.getNombreCompleto().toLowerCase().contains(lower))
                    .toList();
            estudiantesObservable.setAll(filtrados);
        }
    }

    // =========================================================================
    // ACCIONES DE PROFESORES
    // =========================================================================
    @FXML
    public void registrarProfesor() {
        String id = txtProfId.getText();
        String nom = txtProfNombre.getText();
        String idi = cmbProfIdioma.getValue();
        String tel = txtProfTelefono.getText();
        String tarifaStr = txtProfTarifa.getText();

        if (id == null || id.trim().isEmpty() || nom == null || nom.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Ingrese al menos la identificación y el nombre del profesor.");
            return;
        }

        double tarifa = 0.0;
        try {
            if (tarifaStr != null && !tarifaStr.trim().isEmpty()) {
                tarifa = Double.parseDouble(tarifaStr.trim());
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Formato Incorrecto", "La tarifa por sesión debe ser un valor numérico válido.");
            return;
        }

        Profesor prof = new Profesor(id.trim(), nom.trim(), idi != null ? idi : "Inglés", tel != null ? tel.trim() : "", tarifa);
        if (academia.registrarProfesor(prof)) {
            sincronizarDatos();
            limpiarFormProfesor();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Profesor registrado correctamente.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ya existe un profesor con identificación " + id);
        }
    }

    @FXML
    public void actualizarProfesor() {
        String id = txtProfId.getText();
        if (id == null || id.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Indique la identificación del profesor a actualizar.");
            return;
        }
        double tarifa = 0.0;
        try {
            tarifa = Double.parseDouble(txtProfTarifa.getText().trim());
        } catch (Exception ignored) {}

        Profesor prof = new Profesor(id.trim(), txtProfNombre.getText().trim(), cmbProfIdioma.getValue(), txtProfTelefono.getText().trim(), tarifa);
        if (academia.actualizarProfesor(prof)) {
            sincronizarDatos();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Profesor actualizado correctamente.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se encontró el profesor.");
        }
    }

    @FXML
    public void eliminarProfesor() {
        Profesor prof = tblProfesores.getSelectionModel().getSelectedItem();
        if (prof == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Seleccione un profesor de la tabla.");
            return;
        }
        academia.eliminarProfesor(prof.getIdentificacion());
        sincronizarDatos();
        limpiarFormProfesor();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminado", "Profesor eliminado exitosamente.");
    }

    @FXML
    public void limpiarFormProfesor() {
        txtProfId.clear();
        txtProfNombre.clear();
        txtProfTelefono.clear();
        txtProfTarifa.clear();
        tblProfesores.getSelectionModel().clearSelection();
    }

    // =========================================================================
    // ACCIONES DE CURSOS
    // =========================================================================
    @FXML
    public void registrarCurso() {
        String cod = txtCursoCodigo.getText();
        String nom = txtCursoNombre.getText();
        String idi = cmbCursoIdioma.getValue();
        String des = txtCursoDescripcion.getText();
        int dur = spnCursoDuracion.getValue();
        EstadoCurso est = cmbCursoEstado.getValue();
        TipoCurso tipo = cmbCursoTipo.getValue();

        if (cod == null || cod.trim().isEmpty() || nom == null || nom.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Ingrese código y nombre del curso.");
            return;
        }

        double valMensual = 0.0;
        try {
            valMensual = Double.parseDouble(txtCursoValorMensual.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Formato Incorrecto", "El valor mensual debe ser un número válido.");
            return;
        }

        Curso nuevoCurso;
        if (tipo == TipoCurso.REGULAR) {
            nuevoCurso = new CursoRegular(cod.trim(), nom.trim(), idi, des, dur, valMensual, est);
        } else if (tipo == TipoCurso.INTENSIVO) {
            nuevoCurso = new CursoIntensivo(cod.trim(), nom.trim(), idi, des, dur, valMensual, est);
        } else {
            int sesiones = spnCursoSesiones.getValue();
            Nivel nivel = cmbCursoNivel.getValue();
            String obj = txtCursoObjetivos.getText();
            nuevoCurso = new CursoPersonalizado(cod.trim(), nom.trim(), idi, des, dur, valMensual, est, sesiones, nivel, obj != null ? obj.trim() : "");
        }

        if (academia.registrarCurso(nuevoCurso)) {
            sincronizarDatos();
            limpiarFormCurso();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Curso registrado exitosamente.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ya existe un curso con el código " + cod);
        }
    }

    @FXML
    public void eliminarCurso() {
        Curso curso = tblCursos.getSelectionModel().getSelectedItem();
        if (curso == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Seleccione un curso de la tabla.");
            return;
        }
        academia.eliminarCurso(curso.getCodigo());
        sincronizarDatos();
        limpiarFormCurso();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminado", "Curso eliminado exitosamente.");
    }

    @FXML
    public void limpiarFormCurso() {
        txtCursoCodigo.clear();
        txtCursoNombre.clear();
        txtCursoDescripcion.clear();
        txtCursoValorMensual.clear();
        txtCursoObjetivos.clear();
        spnCursoDuracion.getValueFactory().setValue(6);
        spnCursoSesiones.getValueFactory().setValue(10);
        cmbCursoTipo.setValue(TipoCurso.REGULAR);
        tblCursos.getSelectionModel().clearSelection();
    }

    // =========================================================================
    // ACCIONES DE SERVICIOS ADICIONALES
    // =========================================================================
    @FXML
    public void registrarServicio() {
        String cod = txtServCodigo.getText();
        String nom = txtServNombre.getText();
        String des = txtServDescripcion.getText();
        Disponibilidad disp = cmbServDisponibilidad.getValue();

        if (cod == null || cod.trim().isEmpty() || nom == null || nom.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Ingrese código y nombre del servicio adicional.");
            return;
        }

        double precio = 0.0;
        try {
            precio = Double.parseDouble(txtServPrecio.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Formato Incorrecto", "El precio del servicio debe ser numérico.");
            return;
        }

        ServicioAdicional srv = new ServicioAdicional(cod.trim(), nom.trim(), des != null ? des.trim() : "", precio, disp);
        if (academia.registrarServicioAdicional(srv)) {
            sincronizarDatos();
            limpiarFormServicio();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Servicio adicional registrado.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ya existe un servicio con código " + cod);
        }
    }

    @FXML
    public void actualizarServicio() {
        String cod = txtServCodigo.getText();
        if (cod == null || cod.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Indique el código del servicio a actualizar.");
            return;
        }
        double precio = 0.0;
        try {
            precio = Double.parseDouble(txtServPrecio.getText().trim());
        } catch (Exception ignored) {}

        ServicioAdicional srv = new ServicioAdicional(cod.trim(), txtServNombre.getText().trim(), txtServDescripcion.getText().trim(),
                precio, cmbServDisponibilidad.getValue());

        if (academia.actualizarServicioAdicional(srv)) {
            sincronizarDatos();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Servicio actualizado.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se encontró el servicio.");
        }
    }

    @FXML
    public void eliminarServicio() {
        ServicioAdicional srv = tblServicios.getSelectionModel().getSelectedItem();
        if (srv == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Seleccione un servicio de la tabla.");
            return;
        }
        academia.eliminarServicioAdicional(srv.getCodigo());
        sincronizarDatos();
        limpiarFormServicio();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminado", "Servicio eliminado.");
    }

    @FXML
    public void limpiarFormServicio() {
        txtServCodigo.clear();
        txtServNombre.clear();
        txtServDescripcion.clear();
        txtServPrecio.clear();
        cmbServDisponibilidad.setValue(Disponibilidad.DISPONIBLE);
        tblServicios.getSelectionModel().clearSelection();
    }

    // =========================================================================
    // ACCIONES DE MATRÍCULAS
    // =========================================================================
    @FXML
    public void registrarMatricula() {
        String cod = txtMatCodigo.getText();
        LocalDate fecha = dpMatFecha.getValue();
        Estudiante est = cmbMatEstudiante.getValue();
        Curso cur = cmbMatCurso.getValue();
        Profesor prof = cmbMatProfesor.getValue();

        if (cod == null || cod.trim().isEmpty() || est == null || cur == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Debe ingresar código, fecha, estudiante y curso a matricular.");
            return;
        }

        if (cur instanceof CursoPersonalizado && prof == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Profesor Requerido", "Para los cursos personalizados es obligatorio asignar un profesor responsable.");
            return;
        }

        double descuento = 0.0;
        try {
            if (txtMatDescuento.getText() != null && !txtMatDescuento.getText().trim().isEmpty()) {
                descuento = Double.parseDouble(txtMatDescuento.getText().trim());
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Formato Inválido", "El descuento debe ser un porcentaje numérico (0 - 100).");
            return;
        }

        Matricula mat = new Matricula(cod.trim(), fecha, est, cur, prof, descuento);
        List<ServicioAdicional> seleccionados = lvMatServicios.getSelectionModel().getSelectedItems();
        if (seleccionados != null) {
            for (ServicioAdicional s : seleccionados) {
                mat.agregarServicioAdicional(s);
            }
        }

        if (academia.registrarMatricula(mat)) {
            sincronizarDatos();
            limpiarFormMatricula();
            generarSiguienteCodigoMatricula();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Matrícula Exitosa",
                    "Matrícula " + cod + " registrada satisfactoriamente.\nValor total a pagar: " + formatoMoneda.format(mat.getValorTotal()));
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ya existe una matrícula con el código " + cod);
        }
    }

    @FXML
    public void eliminarMatricula() {
        Matricula mat = tblMatriculas.getSelectionModel().getSelectedItem();
        if (mat == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Seleccione una matrícula de la tabla.");
            return;
        }
        academia.eliminarMatricula(mat.getCodigo());
        sincronizarDatos();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminada", "Matrícula eliminada exitosamente.");
    }

    @FXML
    public void registrarPagoMatricula() {
        Matricula seleccionada = tblMatriculas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Seleccione una matrícula para registrar un pago.");
            return;
        }

        if (seleccionada.getSaldoPendiente() <= 0) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Matrícula al día", "Esta matrícula ya se encuentra pagada en su totalidad.");
            return;
        }

        Dialog<Pago> dialog = new Dialog<>();
        dialog.setTitle("Registrar Pago");
        dialog.setHeaderText("Matrícula: " + seleccionada.getCodigo() + " - Saldo pendiente: " + formatoMoneda.format(seleccionada.getSaldoPendiente()));

        ButtonType btnPagar = new ButtonType("Confirmar Pago", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnPagar, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField txtMonto = new TextField(String.valueOf(seleccionada.getSaldoPendiente()));
        ComboBox<String> cmbMetodo = new ComboBox<>(FXCollections.observableArrayList("Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Transferencia Bancaria"));
        cmbMetodo.getSelectionModel().selectFirst();
        DatePicker dpPago = new DatePicker(LocalDate.now());

        grid.add(new Label("Monto a pagar:"), 0, 0);
        grid.add(txtMonto, 1, 0);
        grid.add(new Label("Método de pago:"), 0, 1);
        grid.add(cmbMetodo, 1, 1);
        grid.add(new Label("Fecha de pago:"), 0, 2);
        grid.add(dpPago, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btnPagar) {
                try {
                    double monto = Double.parseDouble(txtMonto.getText().trim());
                    String idPago = "PAG-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
                    return new Pago(idPago, monto, dpPago.getValue(), cmbMetodo.getValue());
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        });

        Optional<Pago> result = dialog.showAndWait();
        result.ifPresent(pago -> {
            seleccionada.agregarPago(pago);
            sincronizarDatos();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Pago Registrado",
                    "Pago por " + formatoMoneda.format(pago.getMonto()) + " registrado con éxito.\nSaldo restante: " + formatoMoneda.format(seleccionada.getSaldoPendiente()));
        });
    }

    @FXML
    public void verDetalleMatricula() {
        Matricula mat = tblMatriculas.getSelectionModel().getSelectedItem();
        if (mat == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Seleccione una matrícula para ver su detalle.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("DATOS GENERALES\n");
        sb.append("• Código: ").append(mat.getCodigo()).append("\n");
        sb.append("• Fecha: ").append(mat.getFecha()).append("\n\n");

        sb.append("ESTUDIANTE\n");
        sb.append("• Nombre: ").append(mat.getEstudiante().getNombreCompleto()).append("\n");
        sb.append("• Documento: ").append(mat.getEstudiante().getDocumentoIdentidad()).append("\n");
        sb.append("• Teléfono: ").append(mat.getEstudiante().getTelefono()).append("\n\n");

        sb.append("CURSO\n");
        sb.append("• Nombre: ").append(mat.getCurso().getNombre()).append(" (").append(mat.getCurso().getIdioma()).append(")\n");
        sb.append("• Tipo: ").append(mat.getCurso().getTipoCurso()).append("\n");
        sb.append("• Duración: ").append(mat.getCurso().getDuracionMeses()).append(" meses\n");
        sb.append("• Costo Curso: ").append(formatoMoneda.format(mat.calcularCostoCurso())).append("\n");

        if (mat.getCurso() instanceof CursoPersonalizado cp) {
            sb.append("• Sesiones con profesor: ").append(cp.getCantidadSesiones()).append("\n");
            sb.append("• Nivel referencia: ").append(cp.getNivelReferencia()).append("\n");
            sb.append("• Objetivos: ").append(cp.getObjetivosEstudiante()).append("\n");
        }

        sb.append("• Profesor Asignado: ").append(mat.getProfesor() != null ? mat.getProfesor().getNombre() : "No requerido / Sin asignar").append("\n\n");

        sb.append("SERVICIOS ADICIONALES\n");
        if (mat.getServiciosAdicionales().isEmpty()) {
            sb.append("• Ninguno\n");
        } else {
            for (ServicioAdicional s : mat.getServiciosAdicionales()) {
                sb.append("• ").append(s.getNombre()).append(" (").append(formatoMoneda.format(s.getPrecio())).append(")\n");
            }
        }
        sb.append("• Total Servicios: ").append(formatoMoneda.format(mat.calcularCostoServicios())).append("\n\n");

        sb.append("TOTALES Y PAGOS\n");
        sb.append("• Subtotal: ").append(formatoMoneda.format(mat.calcularSubtotal())).append("\n");
        sb.append("• Descuento (").append(mat.getDescuentoPorcentaje()).append("%): -").append(formatoMoneda.format(mat.calcularMontoDescuento())).append("\n");
        sb.append("• VALOR TOTAL: ").append(formatoMoneda.format(mat.getValorTotal())).append("\n");
        sb.append("• Total Pagado: ").append(formatoMoneda.format(mat.getTotalPagado())).append("\n");
        sb.append("• Saldo Pendiente: ").append(formatoMoneda.format(mat.getSaldoPendiente())).append("\n");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalle de Matrícula " + mat.getCodigo());
        alert.setHeaderText("Resumen Detallado de la Matrícula");
        TextArea textArea = new TextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefWidth(500);
        textArea.setPrefHeight(380);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    @FXML
    public void limpiarFormMatricula() {
        generarSiguienteCodigoMatricula();
        dpMatFecha.setValue(LocalDate.now());
        cmbMatEstudiante.getSelectionModel().clearSelection();
        cmbMatCurso.getSelectionModel().clearSelection();
        cmbMatProfesor.getSelectionModel().clearSelection();
        lvMatServicios.getSelectionModel().clearSelection();
        txtMatDescuento.setText("0");
        actualizarCalculoMatricula();
    }

    // =========================================================================
    // CONSULTA 1: BUSCAR ESTUDIANTE MEDIANTE SU DOCUMENTO DE IDENTIDAD
    // =========================================================================
    @FXML
    public void ejecutarConsultaEstudiante() {
        String doc = txtConsultaDocEstudiante.getText();
        if (doc == null || doc.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Documento Requerido", "Por favor ingrese el documento de identidad del estudiante a consultar.");
            return;
        }

        Estudiante encontrado = academia.buscarEstudiante(doc.trim());
        if (encontrado == null) {
            lblConsEstNombre.setText("No encontrado");
            lblConsEstDocumento.setText("-");
            lblConsEstEdad.setText("-");
            lblConsEstTelefono.setText("-");
            lblConsEstCorreo.setText("-");
            lblConsEstFechaReg.setText("-");
            lblConsEstTotalMatriculas.setText("0");
            estudianteMatriculasObservable.clear();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Búsqueda de Estudiante", "No se encontró ningún estudiante con el documento: " + doc);
            return;
        }

        lblConsEstNombre.setText(encontrado.getNombreCompleto());
        lblConsEstDocumento.setText(encontrado.getDocumentoIdentidad());
        lblConsEstEdad.setText(encontrado.getEdad() + " años");
        lblConsEstTelefono.setText(encontrado.getTelefono());
        lblConsEstCorreo.setText(encontrado.getCorreoElectronico());
        lblConsEstFechaReg.setText(encontrado.getFechaRegistro() != null ? encontrado.getFechaRegistro().toString() : "N/A");

        List<Matricula> matriculasEst = academia.buscarMatriculasPorEstudiante(encontrado.getDocumentoIdentidad());
        estudianteMatriculasObservable.setAll(matriculasEst);
        lblConsEstTotalMatriculas.setText(String.valueOf(matriculasEst.size()));
    }

    // =========================================================================
    // CONSULTA 2: INGRESOS POR MATRÍCULAS EN UN PERÍODO DETERMINADO
    // =========================================================================
    @FXML
    public void ejecutarReporteIngresos() {
        LocalDate fInicio = dpReporteFechaInicio.getValue();
        LocalDate fFin = dpReporteFechaFin.getValue();

        if (fInicio == null || fFin == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Fechas Requeridas", "Seleccione fecha inicial y fecha final para el reporte.");
            return;
        }

        if (fInicio.isAfter(fFin)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Rango Inválido", "La fecha inicial no puede ser posterior a la fecha final.");
            return;
        }

        // Ejecutar lógica de negocio
        Reporte reporte = academia.generarReporteIngresos(fInicio, fFin);

        reporteMatriculasObservable.setAll(reporte.getMatriculas());
        lblReporteTotalIngresos.setText(formatoMoneda.format(reporte.getTotalIngresos()));
        lblReporteCantidadMatriculas.setText(String.valueOf(reporte.getCantidadMatriculas()));
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
