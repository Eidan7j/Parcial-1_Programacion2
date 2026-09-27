module co.edu.uniquindio.poo.parcial1programacion2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.parcial1programacion2 to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial1programacion2;
    exports co.edu.uniquindio.poo.parcial1programacion2.controllers;
    opens co.edu.uniquindio.poo.parcial1programacion2.controllers to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial1programacion2.model;
    opens co.edu.uniquindio.poo.parcial1programacion2.model to javafx.base, javafx.fxml;
    exports co.edu.uniquindio.poo.parcial1programacion2.viewController;
    opens co.edu.uniquindio.poo.parcial1programacion2.viewController to javafx.fxml;

}