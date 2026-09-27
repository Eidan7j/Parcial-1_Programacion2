package co.edu.uniquindio.poo.parcial1programacion2.model;

/**
 * Clase que representa a un profesor dentro de la academia.
 *
 * // PRINCIPIO SOLID [SRP]: Su única responsabilidad es almacenar y gestionar
 * // la información de un profesor.
 */
public class Profesor {

    //  Atributos
    private String idProfesor;
    private String nombreProfesor;
    private String idiomaProfesor;
    private String telefonoProfesor;
    private double tarifaSesion;

    // Constructor
    public Profesor(String idProfesor, String nombreProfesor,
                    String idiomaProfesor, String telefonoProfesor,
                    double tarifaSesion) {
        this.idProfesor = idProfesor;
        this.nombreProfesor = nombreProfesor;
        this.idiomaProfesor = idiomaProfesor;
        this.telefonoProfesor = telefonoProfesor;
        this.tarifaSesion = tarifaSesion;
    }

    //  Método actualizarProfesor con condicionales
    public void actualizarProfesor(String campo, String nuevoValor) {
        if (campo.equalsIgnoreCase("nombre")) {
            this.nombreProfesor = nuevoValor;
        } else if (campo.equalsIgnoreCase("idioma")) {
            this.idiomaProfesor = nuevoValor;
        } else if (campo.equalsIgnoreCase("telefono")) {
            this.telefonoProfesor = nuevoValor;
        } else if (campo.equalsIgnoreCase("tarifaSesion")) {
            this.tarifaSesion = Double.parseDouble(nuevoValor);
        } else {
            System.out.println("Campo no válido: " + campo);
        }
    }

    //  Getters
    public String getIdProfesor() { return idProfesor; }
    public String getNombreProfesor() { return nombreProfesor; }
    public String getIdiomaProfesor() { return idiomaProfesor; }
    public String getTelefonoProfesor() { return telefonoProfesor; }
    public double getTarifaSesion() { return tarifaSesion; }
}

