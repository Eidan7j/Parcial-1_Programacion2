package co.edu.uniquindio.poo.parcial1programacion2.controllers;

import co.edu.uniquindio.poo.parcial1programacion2.App;
import co.edu.uniquindio.poo.parcial1programacion2.model.Profesor;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProfesorController {

    @FXML private TextField txtIdProfesor;
    @FXML private TextField txtNombreProfesor;
    @FXML private TextField txtIdiomaProfesor;
    @FXML private TextField txtTelefonoProfesor;
    @FXML private TextField txtTarifaSesion;

    @FXML private ComboBox<String> cbCampoActualizar;
    @FXML private TextField txtNuevoValor;

    @FXML private TableView<Profesor> tablaProfesores;
    @FXML private TableColumn<Profesor, String> colId;
    @FXML private TableColumn<Profesor, String> colNombre;
    @FXML private TableColumn<Profesor, String> colIdioma;
    @FXML private TableColumn<Profesor, String> colTelefono;
    @FXML private TableColumn<Profesor, Double> colTarifa;

    @FXML private Label lblMensaje;

    @FXML
    public void initialize() {
        cbCampoActualizar.setItems(FXCollections.observableArrayList("nombre", "idioma", "telefono", "tarifaSesion"));

        colId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdProfesor()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreProfesor()));
        colIdioma.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdiomaProfesor()));
        colTelefono.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefonoProfesor()));
        colTarifa.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getTarifaSesion()).asObject());

        tablaProfesores.setItems(App.getListaProfesores());

        tablaProfesores.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cargarDatosEnFormulario(newSel);
            }
        });
    }

    private void cargarDatosEnFormulario(Profesor p) {
        txtIdProfesor.setText(p.getIdProfesor());
        txtNombreProfesor.setText(p.getNombreProfesor());
        txtIdiomaProfesor.setText(p.getIdiomaProfesor());
        txtTelefonoProfesor.setText(p.getTelefonoProfesor());
        txtTarifaSesion.setText(String.valueOf(p.getTarifaSesion()));
    }

    @FXML
    public void registrarProfesor() {
        try {
            if (txtIdProfesor.getText().isBlank() || txtNombreProfesor.getText().isBlank() ||
                txtIdiomaProfesor.getText().isBlank() || txtTarifaSesion.getText().isBlank()) {
                mostrarMensaje("Complete ID, nombre, idioma y tarifa por sesión.", true);
                return;
            }
            if (App.getAcademia().buscarProfesor(txtIdProfesor.getText().trim()) != null) {
                mostrarMensaje("Ya existe un profesor con el ID " + txtIdProfesor.getText(), true);
                return;
            }

            double tarifa = Double.parseDouble(txtTarifaSesion.getText().trim());
            Profesor nuevo = new Profesor(
                    txtIdProfesor.getText().trim(),
                    txtNombreProfesor.getText().trim(),
                    txtIdiomaProfesor.getText().trim(),
                    txtTelefonoProfesor.getText().trim(),
                    tarifa
            );

            App.registrarProfesor(nuevo);
            tablaProfesores.refresh();
            limpiarCampos();
            mostrarMensaje("Profesor registrado exitosamente.", false);
        } catch (NumberFormatException ex) {
            mostrarMensaje("La tarifa por sesión debe ser un valor numérico válido.", true);
        }
    }

    @FXML
    public void buscarProfesor() {
        String id = txtIdProfesor.getText().trim();
        if (id.isEmpty()) {
            mostrarMensaje("Ingrese el ID del profesor a buscar.", true);
            return;
        }
        Profesor encontrado = App.getAcademia().buscarProfesor(id);
        if (encontrado != null) {
            cargarDatosEnFormulario(encontrado);
            tablaProfesores.getSelectionModel().select(encontrado);
            mostrarMensaje("Profesor encontrado: " + encontrado.getNombreProfesor(), false);
        } else {
            mostrarMensaje("No se encontró profesor con ID: " + id, true);
        }
    }

    @FXML
    public void actualizarDatosProfesor() {
        Profesor seleccionado = tablaProfesores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            seleccionado = App.getAcademia().buscarProfesor(txtIdProfesor.getText().trim());
        }
        if (seleccionado == null) {
            mostrarMensaje("Seleccione un profesor o ingrese su ID.", true);
            return;
        }
        String campo = cbCampoActualizar.getValue();
        String valor = txtNuevoValor.getText().trim();
        if (campo == null || valor.isEmpty()) {
            mostrarMensaje("Seleccione el campo e ingrese el nuevo valor.", true);
            return;
        }
        try {
            seleccionado.actualizarProfesor(campo, valor);
            tablaProfesores.refresh();
            cargarDatosEnFormulario(seleccionado);
            txtNuevoValor.clear();
            mostrarMensaje("Dato '" + campo + "' del profesor actualizado.", false);
        } catch (Exception e) {
            mostrarMensaje("Error al actualizar dato del profesor: verifique el formato.", true);
        }
    }

    @FXML
    public void eliminarProfesor() {
        String id = txtIdProfesor.getText().trim();
        if (id.isEmpty()) {
            mostrarMensaje("Ingrese o seleccione el ID del profesor a eliminar.", true);
            return;
        }
        App.eliminarProfesor(id);
        tablaProfesores.refresh();
        limpiarCampos();
        mostrarMensaje("Profesor con ID " + id + " eliminado.", false);
    }

    @FXML
    public void limpiarCampos() {
        txtIdProfesor.clear();
        txtNombreProfesor.clear();
        txtIdiomaProfesor.clear();
        txtTelefonoProfesor.clear();
        txtTarifaSesion.clear();
        tablaProfesores.getSelectionModel().clearSelection();
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
