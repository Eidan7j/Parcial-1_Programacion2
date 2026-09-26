module co.edu.uniquindio.poo.parcial1programacion2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.parcial1programacion2 to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial1programacion2;
}