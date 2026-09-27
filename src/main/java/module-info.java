module co.edu.uniquindio.poo.parcial1programacion2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.parcial1programacion2 to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial1programacion2.controller to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial1programacion2.model to javafx.base;

    exports co.edu.uniquindio.poo.parcial1programacion2;
    exports co.edu.uniquindio.poo.parcial1programacion2.model;
    exports co.edu.uniquindio.poo.parcial1programacion2.model.enums;
    exports co.edu.uniquindio.poo.parcial1programacion2.controller;
}