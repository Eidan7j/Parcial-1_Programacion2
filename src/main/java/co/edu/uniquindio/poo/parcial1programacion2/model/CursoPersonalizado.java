package co.edu.uniquindio.poo.parcial1programacion2.model;


/**
 * Subclase concreta que representa un Curso Personalizado.
 *
 * // PRINCIPIO SOLID [LSP]: Esta clase puede sustituir a la clase abstracta Curso
 * // sin alterar el comportamiento esperado.
 *
 * // PRINCIPIO SOLID [SRP]: Su única responsabilidad es definir las características
 * // y relaciones específicas de los cursos personalizados.
 *
 * // PATRÓN DE DISEÑO [Asociación múltiple]: Se relaciona con Profesor y Estudiante,
 * // indicando que un curso personalizado tiene un profesor asignado y un estudiante inscrito.
 */
public class CursoPersonalizado extends Curso {

    // Atributos específicos
    private int cantidadSesiones;
    private String nivelReferencia;
    private String tipoNivelEstudiante;

    // Relaciones
    private Profesor profesor;
    private Estudiante estudiante;

    //  Constructor
    public CursoPersonalizado(String codigoCurso, String nombreCurso, String idiomaCurso,
                              String descripcionCurso, int duracionMesesCurso,
                              double valorMensualCurso, EstadoCurso estadoCurso,
                              int cantidadSesiones, String nivelReferencia,
                              String tipoNivelEstudiante, Profesor profesor,
                              Estudiante estudiante) {
        super(codigoCurso, nombreCurso, idiomaCurso, descripcionCurso,
                duracionMesesCurso, valorMensualCurso, estadoCurso);
        this.cantidadSesiones = cantidadSesiones;
        this.nivelReferencia = nivelReferencia;
        this.tipoNivelEstudiante = tipoNivelEstudiante;
        this.profesor = profesor;
        this.estudiante= estudiante;
    }

    // Implementación del método abstracto
    @Override
    public void asignarTipoEnsenanza(String tipo) {
        System.out.println("El curso personalizado se impartirá en modalidad: " + tipo);
    }


    // Método de la relacion con informacion de profesor con estudiante y curso
    public String mostrarRelacionCursoPersonalizado() {
        return "Curso personalizado de " + getNombreCurso()+
                " impartido por el profesor " + profesor.getNombreProfesor() +
                " al estudiante " + estudiante.getNombreEstudiante() +
                " con nivel " + nivelReferencia + " y " + cantidadSesiones + " sesiones.";
    }

    // Getters
    public int getCantidadSesiones() { return cantidadSesiones; }
    public String getNivelReferencia() { return nivelReferencia; }
    public String getTipoNivelEstudiante() { return tipoNivelEstudiante; }
    public Profesor getProfesor() { return profesor; }
    public Estudiante getEstudiante() { return estudiante; }
}


