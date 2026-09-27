package co.edu.uniquindio.poo.parcial1programacion2.controllers;

import co.edu.uniquindio.poo.parcial1programacion2.App;
import co.edu.uniquindio.poo.parcial1programacion2.model.Disponibilidad;
import co.edu.uniquindio.poo.parcial1programacion2.model.ServicioAdicional;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ServicioAdicionalController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtPrecio;
    @FXML private ComboBox<Disponibilidad> cbDisponibilidad;

    @FXML private TableView<ServicioAdicional> tablaServicios;
    @FXML private TableColumn<ServicioAdicional, String> colCodigo;
    @FXML private TableColumn<ServicioAdicional, String> colNombre;
    @FXML private TableColumn<ServicioAdicional, String> colDescripcion;
    @FXML private TableColumn<ServicioAdicional, Double> colDuracion;
    @FXML private TableColumn<ServicioAdicional, Double> colPrecio;
    @FXML private TableColumn<ServicioAdicional, String> colDisponibilidad;

    @FXML private Label lblMensaje;

    @FXML
    public void initialize() {
        cbDisponibilidad.setItems(FXCollections.observableArrayList(Disponibilidad.values()));
        cbDisponibilidad.setValue(Disponibilidad.DISPONIBLE);

        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigoServicioAdicional()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreServicioAdicional()));
        colDescripcion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcionServicioAdicional()));
        colDuracion.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getDuracionServicioAdicional()).asObject());
        colPrecio.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrecioUnitarioServicioAdicional()).asObject());
        colDisponibilidad.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getDisponibilidadServicioAdicional() != null ? c.getValue().getDisponibilidadServicioAdicional().name() : ""));

        tablaServicios.setItems(App.getListaServicios());

        tablaServicios.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cargarEnFormulario(newSel);
            }
        });
    }

    private void cargarEnFormulario(ServicioAdicional s) {
        txtCodigo.setText(s.getCodigoServicioAdicional());
        txtNombre.setText(s.getNombreServicioAdicional());
        txtDescripcion.setText(s.getDescripcionServicioAdicional());
        txtDuracion.setText(String.valueOf(s.getDuracionServicioAdicional()));
        txtPrecio.setText(String.valueOf(s.getPrecioUnitarioServicioAdicional()));
        cbDisponibilidad.setValue(s.getDisponibilidadServicioAdicional());
    }

    @FXML
    public void registrarServicio() {
        try {
            if (txtCodigo.getText().isBlank() || txtNombre.getText().isBlank() ||
                txtDuracion.getText().isBlank() || txtPrecio.getText().isBlank() ||
                cbDisponibilidad.getValue() == null) {
                mostrarMensaje("Complete todos los campos del servicio adicional.", true);
                return;
            }
            if (App.getAcademia().buscarServicioAdicional(txtCodigo.getText().trim()) != null) {
                mostrarMensaje("Ya existe un servicio con el código " + txtCodigo.getText(), true);
                return;
            }

            double duracion = Double.parseDouble(txtDuracion.getText().trim());
            double precio = Double.parseDouble(txtPrecio.getText().trim());

            ServicioAdicional nuevo = new ServicioAdicional(
                    txtCodigo.getText().trim(),
                    txtNombre.getText().trim(),
                    txtDescripcion.getText().trim(),
                    duracion,
                    precio,
                    cbDisponibilidad.getValue()
            );

            App.registrarServicioAdicional(nuevo);
            tablaServicios.refresh();
            limpiarCampos();
            mostrarMensaje("Servicio adicional registrado con éxito.", false);
        } catch (NumberFormatException ex) {
            mostrarMensaje("Duración y precio unitario deben ser valores numéricos.", true);
        }
    }

    @FXML
    public void buscarServicio() {
        String cod = txtCodigo.getText().trim();
        if (cod.isEmpty()) {
            mostrarMensaje("Ingrese el código del servicio a buscar.", true);
            return;
        }
        ServicioAdicional encontrado = App.getAcademia().buscarServicioAdicional(cod);
        if (encontrado != null) {
            cargarEnFormulario(encontrado);
            tablaServicios.getSelectionModel().select(encontrado);
            mostrarMensaje("Servicio encontrado: " + encontrado.getNombreServicioAdicional(), false);
        } else {
            mostrarMensaje("No se encontró servicio con código: " + cod, true);
        }
    }

    @FXML
    public void eliminarServicio() {
        String cod = txtCodigo.getText().trim();
        if (cod.isEmpty()) {
            mostrarMensaje("Ingrese o seleccione el código del servicio a eliminar.", true);
            return;
        }
        App.eliminarServicioAdicional(cod);
        tablaServicios.refresh();
        limpiarCampos();
        mostrarMensaje("Servicio adicional " + cod + " eliminado.", false);
    }

    @FXML
    public void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtDuracion.clear();
        txtPrecio.clear();
        cbDisponibilidad.setValue(Disponibilidad.DISPONIBLE);
        tablaServicios.getSelectionModel().clearSelection();
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
