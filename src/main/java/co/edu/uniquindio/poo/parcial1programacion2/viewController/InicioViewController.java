package co.edu.uniquindio.poo.parcial1programacion2.viewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import co.edu.uniquindio.poo.parcial1programacion2.controllers.InicioController;
public class InicioViewController {



        @FXML private Label lblTotalEstudiantes;
        @FXML private Label lblTotalProfesores;
        @FXML private Label lblTotalCursos;
        @FXML private Label lblTotalServicios;
        @FXML private Label lblTotalMatriculas;
        @FXML private Label lblResumenIngresos;

        // Instancia del controlador lógico
        private final InicioController inicioController = new InicioController();

        // Métodos de navegación que delegan en InicioController
        @FXML
        private void irAEstudiantes() { inicioController.irAEstudiantes(); }

        @FXML
        private void irAProfesores() { inicioController.irAProfesores(); }

        @FXML
        private void irACursos() { inicioController.irACursos(); }

        @FXML
        private void irAServicios() { inicioController.irAServicios(); }

        @FXML
        private void irAMatriculas() { inicioController.irAMatriculas(); }

        @FXML
        private void irAPagos() { inicioController.irAPagos(); }

        @FXML
        private void irAReportes() { inicioController.irAReportes(); }
    }


