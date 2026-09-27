package co.edu.uniquindio.poo.parcial1programacion2.model;

/**
 * Subclase concreta que representa un Curso de Francés.
 *
 * // PRINCIPIO SOLID [LSP]: Esta clase puede sustituir a la clase abstracta Curso
 * // sin alterar el comportamiento esperado, cumpliendo el Principio de Sustitución de Liskov.
 *
 * // PRINCIPIO SOLID [SRP]: Su única responsabilidad es definir las características
 * // específicas de un curso de Francés.
 */
public class CursoFrances extends Curso {

    //  Atributos
    private String nivelFrances;
    private String horarioFrances;


    //  Constructor
    public CursoFrances(String codigoCurso, String nombreCurso, String idiomaCurso,
                        String descripcionCurso, int duracionMesesCurso,
                        double valorMensualCurso, EstadoCurso estadoCurso,
                        String nivelFrances, String horarioFrances) {
        super(codigoCurso, nombreCurso, idiomaCurso, descripcionCurso,
                duracionMesesCurso, valorMensualCurso, estadoCurso);
        this.nivelFrances = nivelFrances;
        this.horarioFrances = horarioFrances;
    }


    // 🔹 Implementación del método abstracto de curso
    @Override
    public void asignarTipoEnsenanza(String tipo) {
        System.out.println("El curso de Francés se impartirá en modalidad: " + tipo);
    }


    // 🔹 Getters
    public String getNivelFrances() { return nivelFrances; }
    public String getHorarioFrances() { return horarioFrances; }
}
