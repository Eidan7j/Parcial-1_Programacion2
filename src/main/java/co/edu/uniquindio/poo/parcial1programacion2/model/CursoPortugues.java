package co.edu.uniquindio.poo.parcial1programacion2.model;


/**
 * Subclase concreta que representa un Curso de Portugués.
 *
 * // PRINCIPIO SOLID [LSP]: Esta clase puede sustituir a la clase abstracta Curso
 * // sin alterar el comportamiento esperado, cumpliendo el Principio de Sustitución de Liskov.
 *
 * // PRINCIPIO SOLID [SRP]: Su única responsabilidad es definir las características
 * // específicas de un curso de Portugués.
 */
public class CursoPortugues extends Curso {

    // 🔹 Atributos específicos
    private String nivelPortugues;
    private String horarioPortugues;

    // 🔹 Constructor
    public CursoPortugues(String codigoCurso, String nombreCurso, String idiomaCurso,
                          String descripcionCurso, int duracionMesesCurso,
                          double valorMensualCurso, EstadoCurso estadoCurso,
                          String nivelPortugues, String horarioPortugues) {
        super(codigoCurso, nombreCurso, idiomaCurso, descripcionCurso,
                duracionMesesCurso, valorMensualCurso, estadoCurso);
        this.nivelPortugues = nivelPortugues;
        this.horarioPortugues = horarioPortugues;
    }

    // Implementación del método abstracto
    @Override
    public void asignarTipoEnsenanza(String tipo) {
        System.out.println("El curso de Portugués se impartirá en modalidad: " + tipo);
    }

    // Getters
    public String getNivelPortugues() { return nivelPortugues; }
    public String getHorarioPortugues() { return horarioPortugues; }
}
