package co.edu.uniquindio.poo.parcial1programacion2.model;
/**
 * Clase que representa un Curso dentro de la Academia.
 *
 * // PRINCIPIO SOLID [SRP]: Esta clase tiene una única responsabilidad:
 * // almacenar y gestionar la información propia de un curso.
 *
 * // PRINCIPIO SOLID [OCP]: Si en el futuro se agregan nuevos tipos de curso
 * // (ej. CursoVirtual, CursoPresencial), se pueden extender sin modificar esta clase.
 */
public abstract class Curso {

        // Atributos
        private String codigoCurso;
        private String nombreCurso;
        private String idiomaCurso;
        private String descripcionCurso;
        private int duracionMesesCurso;
        private double valorMensualCurso;
        private EstadoCurso estadoCurso; // este es el Enum

        // Relación con Matrícula (una matrícula puede contener varios cursos)
        protected Matricula matricula;

        // Constructor
        public Curso(String codigoCurso, String nombreCurso, String idiomaCurso,
                     String descripcionCurso, int duracionMesesCurso,
                     double valorMensualCurso, EstadoCurso estadoCurso) {
            this.codigoCurso = codigoCurso;
            this.nombreCurso = nombreCurso;
            this.idiomaCurso = idiomaCurso;
            this.descripcionCurso = descripcionCurso;
            this.duracionMesesCurso = duracionMesesCurso;
            this.valorMensualCurso = valorMensualCurso;
            this.estadoCurso = estadoCurso;
        }


        // Getters
        public String getCodigoCurso() { return codigoCurso; }
        public String getNombreCurso() { return nombreCurso; }
        public String getIdiomaCurso() { return idiomaCurso; }
        public String getDescripcionCurso() { return descripcionCurso; }
        public int getDuracionMesesCurso() { return duracionMesesCurso; }
        public double getValorMensualCurso() { return valorMensualCurso; }
        public EstadoCurso getEstadoCurso() { return estadoCurso; }

    //metodo abstract
    public abstract void asignarTipoEnsenanza(String tipo);
}


