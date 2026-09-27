package co.edu.uniquindio.poo.parcial1programacion2.model;

/**
 * Subclase concreta que representa un Curso de Inglés.
 *
 * // PRINCIPIO SOLID [LSP]: Esta clase puede sustituir a la clase abstracta Curso
 * // sin alterar el comportamiento esperado, cumpliendo el Principio de Sustitución de Liskov.
 *
 * // PRINCIPIO SOLID [SRP]: Su única responsabilidad es definir las características
 * // específicas de un curso de Inglés.
 */
public class CursoIngles extends Curso {

    // Atributos
    private String nivelIngles;
    private String horarioIngles;

    // Constructor
    public CursoIngles(String codigoCurso, String nombreCurso, String idiomaCurso,
                       String descripcionCurso, int duracionMesesCurso,
                       double valorMensualCurso, EstadoCurso estadoCurso,
                       String nivelIngles, String horarioIngles) {
        super(codigoCurso, nombreCurso, idiomaCurso, descripcionCurso,
                duracionMesesCurso, valorMensualCurso, estadoCurso);
        this.nivelIngles = nivelIngles;
        this.horarioIngles = horarioIngles;
    }

    //  Implementación del método abstracto
    @Override
    public void asignarTipoEnsenanza(String tipo) {
        System.out.println("El curso de Inglés se impartirá en modalidad: " + tipo);
    }

    // 🔹 Getters
    public String getNivelIngles() { return nivelIngles; }
    public String getHorarioIngles() { return horarioIngles; }
}

