package co.edu.uniquindio.poo.parcial1programacion2.controllers;

import co.edu.uniquindio.poo.parcial1programacion2.App;
import co.edu.uniquindio.poo.parcial1programacion2.model.Matricula;
import co.edu.uniquindio.poo.parcial1programacion2.model.Pago;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PagoController {

    @FXML private ComboBox<Matricula> cbMatricula;
    @FXML private TextField txtIdPago;
    @FXML private TextField txtMontoPago;
    @FXML private DatePicker dpFechaPago;

    @FXML private TableView<Pago> tablaPagos;
    @FXML private TableColumn<Pago, String> colIdPago;
    @FXML private TableColumn<Pago, Double> colMontoPago;
    @FXML private TableColumn<Pago, String> colFechaPago;

    @FXML private Label lblTotalMatricula;
    @FXML private Label lblMensaje;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final ObservableList<Pago> pagosDeMatriculaActual = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        dpFechaPago.setValue(LocalDate.now());

        cbMatricula.setItems(App.getListaMatriculas());
        cbMatricula.setConverter(new StringConverter<>() {
            @Override
            public String toString(Matricula m) {
                if (m == null) return "";
                return m.getCodigoMatricula() + " - " + m.getEstudiante().getNombreEstudiante()
                        + " (" + m.getCurso().getNombreCurso() + ")";
            }
            @Override
            public Matricula fromString(String s) { return null; }
        });

        cbMatricula.valueProperty().addListener((obs, oldVal, newVal) -> actualizarTablaPagos(newVal));
        if (!App.getListaMatriculas().isEmpty()) {
            cbMatricula.setValue(App.getListaMatriculas().get(0));
        }

        colIdPago.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdPago()));
        colMontoPago.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getMontoPago()).asObject());
        colFechaPago.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getFechaPago() != null ? sdf.format(c.getValue().getFechaPago()) : ""));

        tablaPagos.setItems(pagosDeMatriculaActual);

        tablaPagos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtIdPago.setText(newSel.getIdPago());
                txtMontoPago.setText(String.valueOf(newSel.getMontoPago()));
                if (newSel.getFechaPago() != null) {
                    dpFechaPago.setValue(newSel.getFechaPago().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                }
            }
        });
    }

    private void actualizarTablaPagos(Matricula matricula) {
        pagosDeMatriculaActual.clear();
        if (matricula != null) {
            pagosDeMatriculaActual.addAll(matricula.getListaPagos());
            lblTotalMatricula.setText("Total acumulado de la matrícula " + matricula.getCodigoMatricula() + ": $" + matricula.calcularValorTotalMatricula());
        } else {
            lblTotalMatricula.setText("Seleccione una matrícula");
        }
    }

    @FXML
    public void registrarPago() {
        try {
            Matricula matricula = cbMatricula.getValue();
            if (matricula == null) {
                mostrarMensaje("Seleccione una matrícula para asociar el pago.", true);
                return;
            }
            if (txtIdPago.getText().isBlank() || txtMontoPago.getText().isBlank()) {
                mostrarMensaje("Complete el ID del pago y el monto.", true);
                return;
            }
            double monto = Double.parseDouble(txtMontoPago.getText().trim());
            LocalDate localDate = dpFechaPago.getValue() != null ? dpFechaPago.getValue() : LocalDate.now();
            Date fecha = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Pago nuevoPago = new Pago(txtIdPago.getText().trim(), monto, fecha);
            matricula.registrarPagoMatricula(nuevoPago);
            actualizarTablaPagos(matricula);
            limpiarCampos();
            mostrarMensaje("Pago registrado correctamente en la matrícula " + matricula.getCodigoMatricula(), false);
        } catch (NumberFormatException ex) {
            mostrarMensaje("El monto del pago debe ser un número válido.", true);
        }
    }

    @FXML
    public void modificarPagoExistente() {
        try {
            Pago pagoSeleccionado = tablaPagos.getSelectionModel().getSelectedItem();
            if (pagoSeleccionado == null) {
                mostrarMensaje("Seleccione un pago de la tabla para modificarlo.", true);
                return;
            }
            double nuevoMonto = Double.parseDouble(txtMontoPago.getText().trim());
            LocalDate localDate = dpFechaPago.getValue() != null ? dpFechaPago.getValue() : LocalDate.now();
            Date nuevaFecha = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            pagoSeleccionado.registrarPago(txtIdPago.getText().trim(), nuevoMonto, nuevaFecha);
            tablaPagos.refresh();
            actualizarTablaPagos(cbMatricula.getValue());
            mostrarMensaje("Pago actualizado mediante Pago.registrarPago(...).", false);
        } catch (NumberFormatException ex) {
            mostrarMensaje("El monto debe ser un valor numérico válido.", true);
        }
    }

    @FXML
    public void limpiarCampos() {
        txtIdPago.clear();
        txtMontoPago.clear();
        dpFechaPago.setValue(LocalDate.now());
        tablaPagos.getSelectionModel().clearSelection();
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
