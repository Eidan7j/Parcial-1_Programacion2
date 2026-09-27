package co.edu.uniquindio.poo.parcial1programacion2.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal del modelo que representa la Academia.
 *
 * // PATRÓN CREACIONAL [Builder]: Se aplica aquí para construir objetos Academia
 * // de forma flexible y segura, evitando constructores con muchos parámetros.
 *
 * // PRINCIPIO SOLID [SRP]: Esta clase tiene una única responsabilidad: gestionar
 * // la información y operaciones propias de la Academia. lo cual cumple con la letra S
 */
public class Academia {

    //  Atributos

    private String nombreComercial;
    private String NIT;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String paginaWeb;

    //Relaciones con clases
    private List<Estudiante> Listaestudiantes;
    private List<Profesor> listaprofesores;
    private List<Curso> listacursos;
    private List<ServicioAdicional> listaserviciosAdicionales;
    private Reporte reporte;
    private List<Matricula> listamatriculas;




    // Constructor privado (Builder)
    private Academia(Builder builder) {
        this.nombreComercial = builder.nombreComercial;
        this.NIT = builder.NIT;
        this.direccion = builder.direccion;
        this.telefono = builder.telefono;
        this.correoElectronico = builder.correoElectronico;
        this.paginaWeb = builder.paginaWeb;
        this.Listaestudiantes = new ArrayList<>();
        this.listaprofesores = new ArrayList<>();
        this.listacursos = new ArrayList<>();
        this.listaserviciosAdicionales = new ArrayList<>();
        this.reporte = new Reporte();
        this.listamatriculas = new ArrayList<>();
    }


    // Clase  Builder

    public static class Builder {
        private String nombreComercial;
        private String NIT;
        private String direccion;
        private String telefono;
        private String correoElectronico;
        private String paginaWeb;

        public Builder nombreComercial(String nombreComercial) {
            this.nombreComercial = nombreComercial;
            return this;
        }

        public Builder NIT(String NIT) {
            this.NIT = NIT;
            return this;
        }

        public Builder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder correoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
            return this;
        }

        public Builder paginaWeb(String paginaWeb) {
            this.paginaWeb = paginaWeb;
            return this;
        }

        public Academia build() {
            return new Academia(this);
        }
    }

    //metodos *CRUD* que es un principio de gestion en las empresas
    //lo implemente por que la academia debe gestionar estos datos como
    //eliminacion, registro, busqueda la parte de actualizacion delegue esa responsabilidad a cada clase
    //que contenga sus atributos para que ella misma se encargue de la tarea de actualizar

    // Estudiantes
    public void registrarEstudiante(Estudiante estudiante) {
        Listaestudiantes.add(estudiante);
    }

    public void eliminarEstudiante(String documento) {
        Listaestudiantes.removeIf(Estudianteactual -> Estudianteactual.getDocumentoEstudiante().equals(documento));
    }
    //buscando con metodos de navegacion
    public Estudiante buscarEstudiantePorDocumento(String documento) {
        for (Estudiante estudiante : Listaestudiantes) {
            if (estudiante.getDocumentoEstudiante().equals(documento)) {
                return estudiante; // Navegación: Academia → Estudiante
            }
        }
        return null;
    }
    // Profesores
    public void registrarProfesor(Profesor profesor) {
        listaprofesores.add(profesor);
    }

    public void eliminarProfesor(String idProfesor) {
        listaprofesores.removeIf(profesorActual -> profesorActual.getIdProfesor().equals(idProfesor));
    }
    //buscando con metodos de navegacion
    public Profesor buscarProfesor(String idProfesor) {
        for (Profesor profesor  : listaprofesores) {
            if (profesor.getIdProfesor().equals(idProfesor)) {
                return profesor;
            }
        }
        return null;
    }

    // Cursos
    public void registrarCurso(Curso curso) {
        listacursos.add(curso);
    }

    public void eliminarCurso(String codigoCurso) {
        listacursos.removeIf(cursoActual -> cursoActual.getCodigoCurso().equals(codigoCurso));
    }
//metodo de navegacion para actualizar
    public Curso buscarCurso (String codigoCurso) {
        for (Curso curso : listacursos) {
            if (curso.getCodigoCurso().equals(codigoCurso)) {
                return curso;
            }
        }
        return null;
    }

    // Servicios adicionales
    public void registrarServicioAdicional(ServicioAdicional servicio) {
        listaserviciosAdicionales.add(servicio);
    }

    public void eliminarServicioAdicional(String codigoServicio) {
        listaserviciosAdicionales.removeIf(s -> s.getCodigoServicioAdicional().equals(codigoServicio));
    }
//metodo for de navegacion para actualizar
    public ServicioAdicional buscarServicioAdicional(String codigoServicio) {
        for (ServicioAdicional servicioAdicional : listaserviciosAdicionales) {
            if (servicioAdicional.getCodigoServicioAdicional().equals(codigoServicio)) {
                return servicioAdicional;
            }
        }
        return null;
    }

    // Reportes
    //metodo que me da el total de ingresos solamente lo muestr ael proceso de suma lo trae del
    //metodocalcularValorTotalMatricula()
    public String mostrarIngresosGenerados() {
        double acumuladorTotal = 0;

        // Navegar entre todas las matrículas de la academia
        for (Matricula matricula : listamatriculas) {
            // Usar el metodo que se encara de acumular pagos en matricula
            acumuladorTotal += matricula.calcularValorTotalMatricula();
        }
        // Retornar como String
        return "El total de ingresos generados por la academia es: " + acumuladorTotal;
    }



}


