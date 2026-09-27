package co.edu.uniquindio.poo.parcial1programacion2.controllers;

import co.edu.uniquindio.poo.parcial1programacion2.App;
import co.edu.uniquindio.poo.parcial1programacion2.model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class CursoController {

    @FXML private ComboBox<String> cbTipoCurso;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtIdioma;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtValorMensual;
    @FXML private ComboBox<EstadoCurso> cbEstadoCurso;

    // Panel para CursoIngles, CursoFrances, CursoPortugues
    @FXML private VBox boxCursoRegular;
    @FXML private TextField txtNivelRegular;
    @FXML private TextField txtHorarioRegular;

    // Panel para CursoPersonalizado
    @FXML private VBox boxCursoPersonalizado;
    @FXML private TextField txtCantidadSesiones;
    @FXML private TextField txtNivelPersonalizado;
    @FXML private TextField txtTipoNivelEstudiante;
    @FXML private ComboBox<Profesor> cbProfesor;
    @FXML private ComboBox<Estudiante> cbEstudiante;

    @FXML private TextField txtModalidadEnsenanza;

    @FXML private TableView<Curso> tablaCursos;
    @FXML private TableColumn<Curso, String> colCodigo;
    @FXML private TableColumn<Curso, String> colTipo;
    @FXML private TableColumn<Curso, String> colNombre;
    @FXML private TableColumn<Curso, String> colIdioma;
    @FXML private TableColumn<Curso, Integer> colDuracion;
    @FXML private TableColumn<Curso, Double> colValor;
    @FXML private TableColumn<Curso, String> colEstado;
    @FXML private TableColumn<Curso, String> colDetalle;

    @FXML private Label lblMensaje;

    @FXML
    public void initialize() {
        cbTipoCurso.setItems(FXCollections.observableArrayList(
                "Curso de Inglés", "Curso de Francés", "Curso de Portugués", "Curso Personalizado"
        ));
        cbTipoCurso.setValue("Curso de Inglés");
        txtIdioma.setText("Inglés");

        cbEstadoCurso.setItems(FXCollections.observableArrayList(EstadoCurso.values()));
        cbEstadoCurso.setValue(EstadoCurso.ACTIVO);

        cbProfesor.setItems(App.getListaProfesores());
        cbProfesor.setConverter(new StringConverter<>() {
            @Override
            public String toString(Profesor p) {
                return p == null ? "" : p.getIdProfesor() + " - " + p.getNombreProfesor();
            }
            @Override
            public Profesor fromString(String s) { return null; }
        });

        cbEstudiante.setItems(App.getListaEstudiantes());
        cbEstudiante.setConverter(new StringConverter<>() {
            @Override
            public String toString(Estudiante e) {
                return e == null ? "" : e.getDocumentoEstudiante() + " - " + e.getNombreEstudiante();
            }
            @Override
            public Estudiante fromString(String s) { return null; }
        });

        cbTipoCurso.valueProperty().addListener((obs, oldVal, newVal) -> actualizarVisibilidadFormulario(newVal));
        actualizarVisibilidadFormulario("Curso de Inglés");

        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigoCurso()));
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(obtenerNombreSubclase(c.getValue())));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreCurso()));
        colIdioma.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdiomaCurso()));
        colDuracion.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getDuracionMesesCurso()).asObject());
        colValor.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getValorMensualCurso()).asObject());
        colEstado.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getEstadoCurso() != null ? c.getValue().getEstadoCurso().name() : ""));
        colDetalle.setCellValueFactory(c -> new SimpleStringProperty(obtenerDetalleEspecifico(c.getValue())));

        tablaCursos.setItems(App.getListaCursos());

        tablaCursos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cargarCursoEnFormulario(newSel);
            }
        });
    }

    private void actualizarVisibilidadFormulario(String tipo) {
        boolean esPersonalizado = "Curso Personalizado".equals(tipo);
        boxCursoPersonalizado.setVisible(esPersonalizado);
        boxCursoPersonalizado.setManaged(esPersonalizado);
        boxCursoRegular.setVisible(!esPersonalizado);
        boxCursoRegular.setManaged(!esPersonalizado);

        if ("Curso de Inglés".equals(tipo)) txtIdioma.setText("Inglés");
        else if ("Curso de Francés".equals(tipo)) txtIdioma.setText("Francés");
        else if ("Curso de Portugués".equals(tipo)) txtIdioma.setText("Portugués");
    }

    private String obtenerNombreSubclase(Curso c) {
        if (c instanceof CursoIngles) return "Inglés";
        if (c instanceof CursoFrances) return "Francés";
        if (c instanceof CursoPortugues) return "Portugués";
        if (c instanceof CursoPersonalizado) return "Personalizado";
        return "General";
    }

    private String obtenerDetalleEspecifico(Curso c) {
        if (c instanceof CursoIngles ci) {
            return "Nivel: " + ci.getNivelIngles() + " | Horario: " + ci.getHorarioIngles();
        }
        if (c instanceof CursoFrances cf) {
            return "Nivel: " + cf.getNivelFrances() + " | Horario: " + cf.getHorarioFrances();
        }
        if (c instanceof CursoPortugues cp) {
            return "Nivel: " + cp.getNivelPortugues() + " | Horario: " + cp.getHorarioPortugues();
        }
        if (c instanceof CursoPersonalizado cpers) {
            return cpers.mostrarRelacionCursoPersonalizado();
        }
        return "";
    }

    private void cargarCursoEnFormulario(Curso c) {
        txtCodigo.setText(c.getCodigoCurso());
        txtNombre.setText(c.getNombreCurso());
        txtIdioma.setText(c.getIdiomaCurso());
        txtDescripcion.setText(c.getDescripcionCurso());
        txtDuracion.setText(String.valueOf(c.getDuracionMesesCurso()));
        txtValorMensual.setText(String.valueOf(c.getValorMensualCurso()));
        cbEstadoCurso.setValue(c.getEstadoCurso());

        if (c instanceof CursoIngles ci) {
            cbTipoCurso.setValue("Curso de Inglés");
            txtNivelRegular.setText(ci.getNivelIngles());
            txtHorarioRegular.setText(ci.getHorarioIngles());
        } else if (c instanceof CursoFrances cf) {
            cbTipoCurso.setValue("Curso de Francés");
            txtNivelRegular.setText(cf.getNivelFrances());
            txtHorarioRegular.setText(cf.getHorarioFrances());
        } else if (c instanceof CursoPortugues cp) {
            cbTipoCurso.setValue("Curso de Portugués");
            txtNivelRegular.setText(cp.getNivelPortugues());
            txtHorarioRegular.setText(cp.getHorarioPortugues());
        } else if (c instanceof CursoPersonalizado cpers) {
            cbTipoCurso.setValue("Curso Personalizado");
            txtCantidadSesiones.setText(String.valueOf(cpers.getCantidadSesiones()));
            txtNivelPersonalizado.setText(cpers.getNivelReferencia());
            txtTipoNivelEstudiante.setText(cpers.getTipoNivelEstudiante());
            cbProfesor.setValue(cpers.getProfesor());
            cbEstudiante.setValue(cpers.getEstudiante());
        }
    }

    @FXML
    public void registrarCurso() {
        try {
            if (txtCodigo.getText().isBlank() || txtNombre.getText().isBlank() ||
                txtDuracion.getText().isBlank() || txtValorMensual.getText().isBlank() ||
                cbEstadoCurso.getValue() == null) {
                mostrarMensaje("Complete todos los campos obligatorios del curso.", true);
                return;
            }
            if (App.getAcademia().buscarCurso(txtCodigo.getText().trim()) != null) {
                mostrarMensaje("Ya existe un curso con el código " + txtCodigo.getText(), true);
                return;
            }

            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            String idioma = txtIdioma.getText().trim();
            String desc = txtDescripcion.getText().trim();
            int duracion = Integer.parseInt(txtDuracion.getText().trim());
            double valor = Double.parseDouble(txtValorMensual.getText().trim());
            EstadoCurso estado = cbEstadoCurso.getValue();

            String tipo = cbTipoCurso.getValue();
            Curso nuevoCurso;

            if ("Curso Personalizado".equals(tipo)) {
                if (cbProfesor.getValue() == null || cbEstudiante.getValue() == null || txtCantidadSesiones.getText().isBlank()) {
                    mostrarMensaje("Para Curso Personalizado seleccione Profesor, Estudiante y Cantidad de Sesiones.", true);
                    return;
                }
                int sesiones = Integer.parseInt(txtCantidadSesiones.getText().trim());
                nuevoCurso = new CursoPersonalizado(
                        codigo, nombre, idioma, desc, duracion, valor, estado,
                        sesiones, txtNivelPersonalizado.getText().trim(),
                        txtTipoNivelEstudiante.getText().trim(),
                        cbProfesor.getValue(), cbEstudiante.getValue()
                );
            } else if ("Curso de Francés".equals(tipo)) {
                nuevoCurso = new CursoFrances(
                        codigo, nombre, idioma, desc, duracion, valor, estado,
                        txtNivelRegular.getText().trim(), txtHorarioRegular.getText().trim()
                );
            } else if ("Curso de Portugués".equals(tipo)) {
                nuevoCurso = new CursoPortugues(
                        codigo, nombre, idioma, desc, duracion, valor, estado,
                        txtNivelRegular.getText().trim(), txtHorarioRegular.getText().trim()
                );
            } else {
                nuevoCurso = new CursoIngles(
                        codigo, nombre, idioma, desc, duracion, valor, estado,
                        txtNivelRegular.getText().trim(), txtHorarioRegular.getText().trim()
                );
            }

            App.registrarCurso(nuevoCurso);
            tablaCursos.refresh();
            limpiarCampos();
            mostrarMensaje("Curso '" + nombre + "' registrado exitosamente.", false);
        } catch (NumberFormatException ex) {
            mostrarMensaje("Duración, valor mensual y sesiones deben ser valores numéricos.", true);
        }
    }

    @FXML
    public void buscarCurso() {
        String cod = txtCodigo.getText().trim();
        if (cod.isEmpty()) {
            mostrarMensaje("Ingrese un código de curso para buscar.", true);
            return;
        }
        Curso encontrado = App.getAcademia().buscarCurso(cod);
        if (encontrado != null) {
            cargarCursoEnFormulario(encontrado);
            tablaCursos.getSelectionModel().select(encontrado);
            mostrarMensaje("Curso encontrado: " + encontrado.getNombreCurso(), false);
        } else {
            mostrarMensaje("No se encontró curso con código: " + cod, true);
        }
    }

    @FXML
    public void eliminarCurso() {
        String cod = txtCodigo.getText().trim();
        if (cod.isEmpty()) {
            mostrarMensaje("Seleccione o ingrese el código del curso a eliminar.", true);
            return;
        }
        App.eliminarCurso(cod);
        tablaCursos.refresh();
        limpiarCampos();
        mostrarMensaje("Curso con código " + cod + " eliminado.", false);
    }

    @FXML
    public void asignarModalidadEnsenanza() {
        Curso seleccionado = tablaCursos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarMensaje("Seleccione un curso de la tabla para asignarle modalidad.", true);
            return;
        }
        String modalidad = txtModalidadEnsenanza.getText().trim();
        if (modalidad.isEmpty()) {
            mostrarMensaje("Ingrese la modalidad (Ej: Virtual, Presencial, Híbrida).", true);
            return;
        }
        seleccionado.asignarTipoEnsenanza(modalidad);
        mostrarMensaje("Modalidad '" + modalidad + "' asignada al curso " + seleccionado.getNombreCurso() + ".", false);
    }

    @FXML
    public void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtDuracion.clear();
        txtValorMensual.clear();
        txtNivelRegular.clear();
        txtHorarioRegular.clear();
        txtCantidadSesiones.clear();
        txtNivelPersonalizado.clear();
        txtTipoNivelEstudiante.clear();
        cbProfesor.setValue(null);
        cbEstudiante.setValue(null);
        tablaCursos.getSelectionModel().clearSelection();
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
