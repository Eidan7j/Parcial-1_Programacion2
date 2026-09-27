package co.edu.uniquindio.poo.parcial1programacion2.controllers;

import co.edu.uniquindio.poo.parcial1programacion2.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InicioController {

    @FXML
    private Label lblTotalEstudiantes;

    @FXML
    private Label lblTotalProfesores;

    @FXML
    private Label lblTotalCursos;

    @FXML
    private Label lblTotalServicios;

    @FXML
    private Label lblTotalMatriculas;

    @FXML
    private Label lblResumenIngresos;

    @FXML
    public void initialize() {
        lblTotalEstudiantes.setText(String.valueOf(App.getListaEstudiantes().size()));
        lblTotalProfesores.setText(String.valueOf(App.getListaProfesores().size()));
        lblTotalCursos.setText(String.valueOf(App.getListaCursos().size()));
        lblTotalServicios.setText(String.valueOf(App.getListaServicios().size()));
        lblTotalMatriculas.setText(String.valueOf(App.getListaMatriculas().size()));
        lblResumenIngresos.setText(App.getAcademia().mostrarIngresosGenerados());
    }

    @FXML
    public void irAEstudiantes() {
        App.cambiarVista("estudiante.fxml", "Academia - Creación y Gestión de Estudiantes");
    }

    @FXML
    public void irAProfesores() {
        App.cambiarVista("profesor.fxml", "Academia - Creación y Gestión de Profesores");
    }

    @FXML
    public void irACursos() {
        App.cambiarVista("curso.fxml", "Academia - Creación y Gestión de Cursos");
    }

    @FXML
    public void irAServicios() {
        App.cambiarVista("servicioAdicional.fxml", "Academia - Creación y Gestión de Servicios Adicionales");
    }

    @FXML
    public void irAMatriculas() {
        App.cambiarVista("matricula.fxml", "Academia - Creación y Gestión de Matrículas");
    }

    @FXML
    public void irAPagos() {
        App.cambiarVista("pago.fxml", "Academia - Creación y Gestión de Pagos");
    }

    @FXML
    public void irAReportes() {
        App.cambiarVista("reporte.fxml", "Academia - Reportes Financieros");
    }
}
