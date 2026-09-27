package co.edu.uniquindio.poo.parcial1programacion2;

import co.edu.uniquindio.poo.parcial1programacion2.model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class App extends Application {

    private static Stage primaryStage;
    private static Academia academia;
    private static Reporte reporte = new Reporte();

    private static final ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
    private static final ObservableList<Profesor> listaProfesores = FXCollections.observableArrayList();
    private static final ObservableList<Curso> listaCursos = FXCollections.observableArrayList();
    private static final ObservableList<ServicioAdicional> listaServicios = FXCollections.observableArrayList();
    private static final ObservableList<Matricula> listaMatriculas = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        inicializarDatosPrueba();
        cambiarVista("inicio.fxml", "Academia de Idiomas - Panel Principal");
        primaryStage.setWidth(1050);
        primaryStage.setHeight(720);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void inicializarDatosPrueba() {
        if (academia != null) {
            return;
        }

        // Crear Academia usando el patrón Builder
        academia = new Academia.Builder()
                .nombreComercial("Academia Global Languages UQ")
                .NIT("900.456.789-1")
                .direccion("Av. Bolívar # 12N-34, Armenia")
                .telefono("3157894561")
                .correoElectronico("info@globallanguages.edu.co")
                .paginaWeb("www.globallanguages.edu.co")
                .build();

        // Profesores de prueba
        Profesor prof1 = new Profesor("P001", "Carlos Gómez", "Inglés", "3124567890", 80000);
        Profesor prof2 = new Profesor("P002", "Marie Laurent", "Francés", "3109876543", 95000);
        registrarProfesor(prof1);
        registrarProfesor(prof2);

        // Estudiantes de prueba (usando Builder)
        Estudiante est1 = new Estudiante.Builder()
                .setNombreEstudiante("Juan Pérez")
                .setDocumentoEstudiante("123456789")
                .setTelefonoEstudiante("3124567890")
                .setCorreoElectronicoEstudiante("juanperez@gmail.com")
                .setEdadEstudiante(20)
                .setFechaRegistroEstudiante(new Date())
                .setNivelReferencia(NivelReferencia.B1)
                .setTipoEstudiante(TipoEstudiante.ADOLESCENTE)
                .build();

        Estudiante est2 = new Estudiante.Builder()
                .setNombreEstudiante("Laura Martínez")
                .setDocumentoEstudiante("987654321")
                .setTelefonoEstudiante("3151112233")
                .setCorreoElectronicoEstudiante("laura.martinez@gmail.com")
                .setEdadEstudiante(25)
                .setFechaRegistroEstudiante(new Date())
                .setNivelReferencia(NivelReferencia.A2)
                .setTipoEstudiante(TipoEstudiante.ADULTO)
                .build();

        registrarEstudiante(est1);
        registrarEstudiante(est2);

        // Cursos de prueba
        CursoIngles cursoIngles = new CursoIngles(
                "C001", "Inglés Intensivo", "Inglés", "Curso comunicativo de inglés",
                6, 250000, EstadoCurso.ACTIVO, "B1", "Lunes a Miércoles 6:00 PM - 8:00 PM"
        );
        CursoFrances cursoFrances = new CursoFrances(
                "C002", "Francés Conversacional", "Francés", "Preparación DELF",
                4, 280000, EstadoCurso.ACTIVO, "A2", "Martes y Jueves 4:00 PM - 6:00 PM"
        );
        CursoPersonalizado cursoPers = new CursoPersonalizado(
                "C003", "Inglés de Negocios", "Inglés", "Curso personalizado ejecutivo",
                3, 420000, EstadoCurso.ACTIVO, 12, "B2", "Adulto", prof1, est1
        );

        registrarCurso(cursoIngles);
        registrarCurso(cursoFrances);
        registrarCurso(cursoPers);

        // Servicios adicionales de prueba
        ServicioAdicional serv1 = new ServicioAdicional(
                "S001", "Tutorías personalizadas", "Sesiones extra de apoyo individual",
                2.0, 50000, Disponibilidad.DISPONIBLE
        );
        ServicioAdicional serv2 = new ServicioAdicional(
                "S002", "Simulacro Examen Internacional", "Prueba tipo IELTS / TOEFL",
                3.5, 90000, Disponibilidad.DISPONIBLE
        );
        registrarServicioAdicional(serv1);
        registrarServicioAdicional(serv2);

        // Matrícula y Pago de prueba
        Matricula mat1 = new Matricula("M001", new Date(), est1, cursoIngles);
        mat1.agregarServicioAdicional(serv1);
        Pago pago1 = new Pago("PAG-01", 300000, new Date());
        mat1.registrarPagoMatricula(pago1);
        registrarMatricula(mat1);
    }

    public static void cambiarVista(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
            Parent root = loader.load();
            if (primaryStage.getScene() == null) {
                primaryStage.setScene(new Scene(root));
            } else {
                primaryStage.getScene().setRoot(root);
            }
            primaryStage.setTitle(titulo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Academia getAcademia() {
        return academia;
    }

    public static Reporte getReporte() {
        return reporte;
    }

    public static ObservableList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public static ObservableList<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public static ObservableList<Curso> getListaCursos() {
        return listaCursos;
    }

    public static ObservableList<ServicioAdicional> getListaServicios() {
        return listaServicios;
    }

    public static ObservableList<Matricula> getListaMatriculas() {
        return listaMatriculas;
    }

    // Métodos puente que invocan la lógica de Academia y sincronizan las vistas JavaFX
    public static void registrarEstudiante(Estudiante estudiante) {
        academia.registrarEstudiante(estudiante);
        listaEstudiantes.add(estudiante);
    }

    public static void eliminarEstudiante(String documento) {
        academia.eliminarEstudiante(documento);
        listaEstudiantes.removeIf(e -> e.getDocumentoEstudiante().equals(documento));
    }

    public static void registrarProfesor(Profesor profesor) {
        academia.registrarProfesor(profesor);
        listaProfesores.add(profesor);
    }

    public static void eliminarProfesor(String idProfesor) {
        academia.eliminarProfesor(idProfesor);
        listaProfesores.removeIf(p -> p.getIdProfesor().equals(idProfesor));
    }

    public static void registrarCurso(Curso curso) {
        academia.registrarCurso(curso);
        listaCursos.add(curso);
    }

    public static void eliminarCurso(String codigoCurso) {
        academia.eliminarCurso(codigoCurso);
        listaCursos.removeIf(c -> c.getCodigoCurso().equals(codigoCurso));
    }

    public static void registrarServicioAdicional(ServicioAdicional servicio) {
        academia.registrarServicioAdicional(servicio);
        listaServicios.add(servicio);
    }

    public static void eliminarServicioAdicional(String codigoServicio) {
        academia.eliminarServicioAdicional(codigoServicio);
        listaServicios.removeIf(s -> s.getCodigoServicioAdicional().equals(codigoServicio));
    }

    @SuppressWarnings("unchecked")
    public static void registrarMatricula(Matricula matricula) {
        listaMatriculas.add(matricula);
        try {
            Field field = Academia.class.getDeclaredField("listamatriculas");
            field.setAccessible(true);
            List<Matricula> interna = (List<Matricula>) field.get(academia);
            if (!interna.contains(matricula)) {
                interna.add(matricula);
            }
        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
