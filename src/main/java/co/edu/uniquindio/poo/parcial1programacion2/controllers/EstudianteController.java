package co.edu.uniquindio.poo.parcial1programacion2.controllers;

import co.edu.uniquindio.poo.parcial1programacion2.App;
import co.edu.uniquindio.poo.parcial1programacion2.model.Estudiante;
import co.edu.uniquindio.poo.parcial1programacion2.model.NivelReferencia;
import co.edu.uniquindio.poo.parcial1programacion2.model.TipoEstudiante;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EstudianteController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtEdad;
    @FXML private DatePicker dpFechaRegistro;
    @FXML private ComboBox<NivelReferencia> cbNivelReferencia;
    @FXML private ComboBox<TipoEstudiante> cbTipoEstudiante;

    @FXML private ComboBox<String> cbCampoActualizar;
    @FXML private TextField txtNuevoValor;

    @FXML private TableView<Estudiante> tablaEstudiantes;
    @FXML private TableColumn<Estudiante, String> colDocumento;
    @FXML private TableColumn<Estudiante, String> colNombre;
    @FXML private TableColumn<Estudiante, String> colTelefono;
    @FXML private TableColumn<Estudiante, String> colCorreo;
    @FXML private TableColumn<Estudiante, Integer> colEdad;
    @FXML private TableColumn<Estudiante, String> colNivel;
    @FXML private TableColumn<Estudiante, String> colTipo;
    @FXML private TableColumn<Estudiante, String> colFecha;

    @FXML private Label lblMensaje;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    public void initialize() {
        cbNivelReferencia.setItems(FXCollections.observableArrayList(NivelReferencia.values()));
        cbTipoEstudiante.setItems(FXCollections.observableArrayList(TipoEstudiante.values()));
        cbCampoActualizar.setItems(FXCollections.observableArrayList("nombre", "documento", "telefono", "correo", "edad"));
        dpFechaRegistro.setValue(LocalDate.now());

        colDocumento.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDocumentoEstudiante()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreEstudiante()));
        colTelefono.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefonoEstudiante()));
        colCorreo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCorreoElectronicoEstudiante()));
        colEdad.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getEdadEstudiante()).asObject());
        colNivel.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getNivelReferencia() != null ? c.getValue().getNivelReferencia().name() : ""));
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getTipoEstudiante() != null ? c.getValue().getTipoEstudiante().name() : ""));
        colFecha.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getFechaRegistroEstudiante() != null ? sdf.format(c.getValue().getFechaRegistroEstudiante()) : ""));

        tablaEstudiantes.setItems(App.getListaEstudiantes());

        tablaEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cargarDatosEnFormulario(newSel);
            }
        });
    }

    private void cargarDatosEnFormulario(Estudiante e) {
        txtNombre.setText(e.getNombreEstudiante());
        txtDocumento.setText(e.getDocumentoEstudiante());
        txtTelefono.setText(e.getTelefonoEstudiante());
        txtCorreo.setText(e.getCorreoElectronicoEstudiante());
        txtEdad.setText(String.valueOf(e.getEdadEstudiante()));
        cbNivelReferencia.setValue(e.getNivelReferencia());
        cbTipoEstudiante.setValue(e.getTipoEstudiante());
        if (e.getFechaRegistroEstudiante() != null) {
            dpFechaRegistro.setValue(e.getFechaRegistroEstudiante().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
    }

    @FXML
    public void registrarEstudiante() {
        try {
            if (txtNombre.getText().isBlank() || txtDocumento.getText().isBlank() ||
                cbNivelReferencia.getValue() == null || cbTipoEstudiante.getValue() == null) {
                mostrarMensaje("Complete nombre, documento, nivel y tipo de estudiante.", true);
                return;
            }
            if (App.getAcademia().buscarEstudiantePorDocumento(txtDocumento.getText().trim()) != null) {
                mostrarMensaje("Ya existe un estudiante con el documento " + txtDocumento.getText(), true);
                return;
            }

            int edad = Integer.parseInt(txtEdad.getText().trim());
            LocalDate localDate = dpFechaRegistro.getValue() != null ? dpFechaRegistro.getValue() : LocalDate.now();
            Date fecha = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Uso del patrón Builder de Estudiante
            Estudiante nuevo = new Estudiante.Builder()
                    .setNombreEstudiante(txtNombre.getText().trim())
                    .setDocumentoEstudiante(txtDocumento.getText().trim())
                    .setTelefonoEstudiante(txtTelefono.getText().trim())
                    .setCorreoElectronicoEstudiante(txtCorreo.getText().trim())
                    .setEdadEstudiante(edad)
                    .setFechaRegistroEstudiante(fecha)
                    .setNivelReferencia(cbNivelReferencia.getValue())
                    .setTipoEstudiante(cbTipoEstudiante.getValue())
                    .build();

            App.registrarEstudiante(nuevo);
            tablaEstudiantes.refresh();
            limpiarCampos();
            mostrarMensaje("Estudiante registrado con éxito (Patrón Builder).", false);
        } catch (NumberFormatException ex) {
            mostrarMensaje("La edad debe ser un número entero válido.", true);
        }
    }

    @FXML
    public void buscarEstudiante() {
        String doc = txtDocumento.getText().trim();
        if (doc.isEmpty()) {
            mostrarMensaje("Ingrese un documento para buscar.", true);
            return;
        }
        Estudiante encontrado = App.getAcademia().buscarEstudiantePorDocumento(doc);
        if (encontrado != null) {
            cargarDatosEnFormulario(encontrado);
            tablaEstudiantes.getSelectionModel().select(encontrado);
            mostrarMensaje("Estudiante encontrado: " + encontrado.getNombreEstudiante(), false);
        } else {
            mostrarMensaje("No se encontró estudiante con documento: " + doc, true);
        }
    }

    @FXML
    public void actualizarDatoEstudiante() {
        Estudiante seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            seleccionado = App.getAcademia().buscarEstudiantePorDocumento(txtDocumento.getText().trim());
        }
        if (seleccionado == null) {
            mostrarMensaje("Seleccione un estudiante de la tabla o ingrese su documento.", true);
            return;
        }
        String campo = cbCampoActualizar.getValue();
        String valor = txtNuevoValor.getText().trim();
        if (campo == null || valor.isEmpty()) {
            mostrarMensaje("Seleccione el campo a actualizar e ingrese el nuevo valor.", true);
            return;
        }
        try {
            seleccionado.actualizarDato(campo, valor);
            tablaEstudiantes.refresh();
            cargarDatosEnFormulario(seleccionado);
            txtNuevoValor.clear();
            mostrarMensaje("Campo '" + campo + "' actualizado correctamente.", false);
        } catch (Exception e) {
            mostrarMensaje("Error al actualizar el campo: verifique el formato del valor.", true);
        }
    }

    @FXML
    public void clonarEstudiantePrototype() {
        Estudiante original = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (original == null) {
            mostrarMensaje("Seleccione un estudiante en la tabla para clonarlo (Patrón Prototype).", true);
            return;
        }
        Estudiante clon = original.clone();
        String nuevoDoc = original.getDocumentoEstudiante() + "-CLON";
        clon.actualizarDato("documento", nuevoDoc);
        clon.actualizarDato("nombre", original.getNombreEstudiante() + " (Clon)");
        App.registrarEstudiante(clon);
        tablaEstudiantes.refresh();
        tablaEstudiantes.getSelectionModel().select(clon);
        mostrarMensaje("Estudiante clonado con éxito usando Patrón Prototype (Doc: " + nuevoDoc + ").", false);
    }

    @FXML
    public void eliminarEstudiante() {
        String doc = txtDocumento.getText().trim();
        if (doc.isEmpty()) {
            mostrarMensaje("Seleccione o ingrese el documento del estudiante a eliminar.", true);
            return;
        }
        App.eliminarEstudiante(doc);
        tablaEstudiantes.refresh();
        limpiarCampos();
        mostrarMensaje("Estudiante con documento " + doc + " eliminado.", false);
    }

    @FXML
    public void limpiarCampos() {
        txtNombre.clear();
        txtDocumento.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtEdad.clear();
        dpFechaRegistro.setValue(LocalDate.now());
        cbNivelReferencia.setValue(null);
        cbTipoEstudiante.setValue(null);
        tablaEstudiantes.getSelectionModel().clearSelection();
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
