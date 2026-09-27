package co.edu.uniquindio.poo.parcial1programacion2.model;

import co.edu.uniquindio.poo.parcial1programacion2.model.enums.Disponibilidad;
import co.edu.uniquindio.poo.parcial1programacion2.model.enums.EstadoCurso;
import co.edu.uniquindio.poo.parcial1programacion2.model.enums.Nivel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LenguajeCafetero {

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String paginaWeb;

    private List<Estudiante> estudiantes;
    private List<Profesor> profesores;
    private List<Curso> cursos;
    private List<ServicioAdicional> serviciosAdicionales;
    private List<Matricula> matriculas;

    public LenguajeCafetero(String nombreComercial, String nit, String direccion, String telefono, String correoElectronico, String paginaWeb) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.paginaWeb = paginaWeb;

        this.estudiantes = new ArrayList<>();
        this.profesores = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.serviciosAdicionales = new ArrayList<>();
        this.matriculas = new ArrayList<>();
    }

    // -------------------------------------------------------------
    // GESTIÓN DE ESTUDIANTES
    // -------------------------------------------------------------
    public boolean registrarEstudiante(Estudiante estudiante) {
        if (estudiante == null || estudiante.getDocumentoIdentidad() == null) {
            return false;
        }
        if (buscarEstudiantePorDocumento(estudiante.getDocumentoIdentidad()).isPresent()) {
            return false;
        }
        return estudiantes.add(estudiante);
    }

    public Optional<Estudiante> buscarEstudiantePorDocumento(String documento) {
        if (documento == null) return Optional.empty();
        return estudiantes.stream()
                .filter(e -> e.getDocumentoIdentidad().equalsIgnoreCase(documento.trim()))
                .findFirst();
    }

    public boolean actualizarEstudiante(Estudiante estudianteActualizado) {
        if (estudianteActualizado == null) return false;
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getDocumentoIdentidad().equalsIgnoreCase(estudianteActualizado.getDocumentoIdentidad())) {
                estudiantes.set(i, estudianteActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarEstudiante(String documento) {
        return estudiantes.removeIf(e -> e.getDocumentoIdentidad().equalsIgnoreCase(documento));
    }

    public List<Matricula> buscarMatriculasPorEstudiante(String documento) {
        if (documento == null) return new ArrayList<>();
        return matriculas.stream()
                .filter(m -> m.getEstudiante() != null &&
                        m.getEstudiante().getDocumentoIdentidad().equalsIgnoreCase(documento.trim()))
                .collect(Collectors.toList());
    }

    // -------------------------------------------------------------
    // GESTIÓN DE PROFESORES
    // -------------------------------------------------------------
    public boolean registrarProfesor(Profesor profesor) {
        if (profesor == null || profesor.getIdentificacion() == null) {
            return false;
        }
        if (buscarProfesorPorIdentificacion(profesor.getIdentificacion()).isPresent()) {
            return false;
        }
        return profesores.add(profesor);
    }

    public Optional<Profesor> buscarProfesorPorIdentificacion(String identificacion) {
        if (identificacion == null) return Optional.empty();
        return profesores.stream()
                .filter(p -> p.getIdentificacion().equalsIgnoreCase(identificacion.trim()))
                .findFirst();
    }

    public boolean actualizarProfesor(Profesor profesorActualizado) {
        if (profesorActualizado == null) return false;
        for (int i = 0; i < profesores.size(); i++) {
            if (profesores.get(i).getIdentificacion().equalsIgnoreCase(profesorActualizado.getIdentificacion())) {
                profesores.set(i, profesorActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProfesor(String identificacion) {
        return profesores.removeIf(p -> p.getIdentificacion().equalsIgnoreCase(identificacion));
    }

    // -------------------------------------------------------------
    // GESTIÓN DE CURSOS
    // -------------------------------------------------------------
    public boolean registrarCurso(Curso curso) {
        if (curso == null || curso.getCodigo() == null) {
            return false;
        }
        if (buscarCursoPorCodigo(curso.getCodigo()).isPresent()) {
            return false;
        }
        return cursos.add(curso);
    }

    public Optional<Curso> buscarCursoPorCodigo(String codigo) {
        if (codigo == null) return Optional.empty();
        return cursos.stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo.trim()))
                .findFirst();
    }

    public boolean actualizarCurso(Curso cursoActualizado) {
        if (cursoActualizado == null) return false;
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigo().equalsIgnoreCase(cursoActualizado.getCodigo())) {
                cursos.set(i, cursoActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCurso(String codigo) {
        return cursos.removeIf(c -> c.getCodigo().equalsIgnoreCase(codigo));
    }

    // -------------------------------------------------------------
    // GESTIÓN DE SERVICIOS ADICIONALES
    // -------------------------------------------------------------
    public boolean registrarServicioAdicional(ServicioAdicional servicio) {
        if (servicio == null || servicio.getCodigo() == null) {
            return false;
        }
        if (buscarServicioPorCodigo(servicio.getCodigo()).isPresent()) {
            return false;
        }
        return serviciosAdicionales.add(servicio);
    }

    public Optional<ServicioAdicional> buscarServicioPorCodigo(String codigo) {
        if (codigo == null) return Optional.empty();
        return serviciosAdicionales.stream()
                .filter(s -> s.getCodigo().equalsIgnoreCase(codigo.trim()))
                .findFirst();
    }

    public boolean actualizarServicioAdicional(ServicioAdicional servicioActualizado) {
        if (servicioActualizado == null) return false;
        for (int i = 0; i < serviciosAdicionales.size(); i++) {
            if (serviciosAdicionales.get(i).getCodigo().equalsIgnoreCase(servicioActualizado.getCodigo())) {
                serviciosAdicionales.set(i, servicioActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarServicioAdicional(String codigo) {
        return serviciosAdicionales.removeIf(s -> s.getCodigo().equalsIgnoreCase(codigo));
    }

    // -------------------------------------------------------------
    // GESTIÓN DE MATRÍCULAS
    // -------------------------------------------------------------
    public boolean registrarMatricula(Matricula matricula) {
        if (matricula == null || matricula.getCodigo() == null) {
            return false;
        }
        if (buscarMatriculaPorCodigo(matricula.getCodigo()).isPresent()) {
            return false;
        }
        return matriculas.add(matricula);
    }

    public Optional<Matricula> buscarMatriculaPorCodigo(String codigo) {
        if (codigo == null) return Optional.empty();
        return matriculas.stream()
                .filter(m -> m.getCodigo().equalsIgnoreCase(codigo.trim()))
                .findFirst();
    }

    public boolean eliminarMatricula(String codigo) {
        return matriculas.removeIf(m -> m.getCodigo().equalsIgnoreCase(codigo));
    }

    // -------------------------------------------------------------
    // CONSULTAS Y REPORTES DEL ENUNCIADO
    // -------------------------------------------------------------
    /**
     * Consulta 1: Buscar estudiante mediante su documento de identidad.
     */
    public Estudiante buscarEstudiante(String documentoIdentidad) {
        return buscarEstudiantePorDocumento(documentoIdentidad).orElse(null);
    }

    /**
     * Consulta 2: Ingresos generados por las matrículas realizadas durante un periodo determinado.
     * Recorre las matrículas registradas, identifica aquellas realizadas dentro del periodo consultado
     * y acumula el valor total generado.
     */
    public double calcularIngresosPeriodo(LocalDate fechaInicial, LocalDate fechaFinal) {
        if (fechaInicial == null || fechaFinal == null) {
            return 0.0;
        }
        return matriculas.stream()
                .filter(m -> m.getFecha() != null &&
                        !m.getFecha().isBefore(fechaInicial) &&
                        !m.getFecha().isAfter(fechaFinal))
                .mapToDouble(Matricula::getValorTotal)
                .sum();
    }

    /**
     * Genera un reporte detallado con las matrículas del periodo y total acumulado.
     */
    public Reporte generarReporteIngresos(LocalDate fechaInicial, LocalDate fechaFinal) {
        if (fechaInicial == null || fechaFinal == null) {
            return new Reporte(fechaInicial, fechaFinal, new ArrayList<>());
        }
        List<Matricula> filtradas = matriculas.stream()
                .filter(m -> m.getFecha() != null &&
                        !m.getFecha().isBefore(fechaInicial) &&
                        !m.getFecha().isAfter(fechaFinal))
                .collect(Collectors.toList());

        return new Reporte(fechaInicial, fechaFinal, filtradas);
    }

    // -------------------------------------------------------------
    // DATOS DE PRUEBA INICIALES
    // -------------------------------------------------------------
    public void cargarDatosPrueba() {
        // Estudiantes
        Estudiante e1 = new Estudiante("1094901001", "Valentina Gómez Ríos", "3114567890", "valentina.gomez@gmail.com", 21, LocalDate.of(2026, 1, 15));
        Estudiante e2 = new Estudiante("1094902002", "Juan David Restrepo Cardona", "3127891234", "jrestrepo@hotmail.com", 25, LocalDate.of(2026, 2, 10));
        Estudiante e3 = new Estudiante("1094903003", "Camila Andrea Osorio Morales", "3156784321", "camila.osorio@gmail.com", 19, LocalDate.of(2026, 3, 5));
        Estudiante e4 = new Estudiante("1094904004", "Mateo Henao Salazar", "3209876543", "mateo.henao@yahoo.com", 28, LocalDate.of(2026, 3, 20));
        registrarEstudiante(e1);
        registrarEstudiante(e2);
        registrarEstudiante(e3);
        registrarEstudiante(e4);

        // Profesores
        Profesor p1 = new Profesor("P-101", "John Smith", "Inglés", "3001112233", 45000.0);
        Profesor p2 = new Profesor("P-102", "Claire Dupont", "Francés", "3004445566", 50000.0);
        Profesor p3 = new Profesor("P-103", "Thiago Silva Santos", "Portugués", "3007778899", 42000.0);
        Profesor p4 = new Profesor("P-104", "Emma Watson", "Inglés", "3012223344", 48000.0);
        registrarProfesor(p1);
        registrarProfesor(p2);
        registrarProfesor(p3);
        registrarProfesor(p4);

        // Cursos
        CursoRegular cRegIngles = new CursoRegular("CUR-ING-01", "Inglés General", "Inglés",
                "Curso integral de inglés estructurado por niveles", 6, 220000.0, EstadoCurso.ACTIVO);
        cRegIngles.agregarBeneficio("Acceso a plataforma interactiva 24/7");

        CursoIntensivo cIntFrances = new CursoIntensivo("CUR-FRA-02", "Francés Acelerado", "Francés",
                "Inmersión intensiva para viajes y negocios", 3, 380000.0, EstadoCurso.ACTIVO);

        CursoRegular cRegPortugues = new CursoRegular("CUR-POR-03", "Portugués Básico", "Portugués",
                "Fundamentos de portugués brasileño", 4, 190000.0, EstadoCurso.ACTIVO);

        CursoPersonalizado cPerIngles = new CursoPersonalizado("CUR-ING-PER", "Inglés Personalizado TOEFL/IELTS", "Inglés",
                "Preparación personalizada para exámenes internacionales", 3, 280000.0, EstadoCurso.ACTIVO,
                12, Nivel.B2, "Alcanzar banda 7.5 en IELTS académico");

        CursoPersonalizado cPerFrances = new CursoPersonalizado("CUR-FRA-PER", "Francés Personalizado DELF", "Francés",
                "Preparación individual para certificación DELF B1", 2, 300000.0, EstadoCurso.ACTIVO,
                8, Nivel.B1, "Aprobar evaluación DELF para estudios en Francia");

        registrarCurso(cRegIngles);
        registrarCurso(cIntFrances);
        registrarCurso(cRegPortugues);
        registrarCurso(cPerIngles);
        registrarCurso(cPerFrances);

        // Servicios Adicionales
        ServicioAdicional s1 = new ServicioAdicional("SRV-01", "Simulacro de examen de certificación",
                "Simulacro oficial con retroalimentación personalizada de examinador", 85000.0, Disponibilidad.DISPONIBLE);
        ServicioAdicional s2 = new ServicioAdicional("SRV-02", "Tutoría de refuerzo",
                "Sesión individual de 2 horas para resolución de dudas gramaticales", 40000.0, Disponibilidad.DISPONIBLE);
        ServicioAdicional s3 = new ServicioAdicional("SRV-03", "Material impreso",
                "Libro de trabajo físico y guía de ejercicios original", 65000.0, Disponibilidad.DISPONIBLE);
        ServicioAdicional s4 = new ServicioAdicional("SRV-04", "Talleres de conversación",
                "Pase mensual a talleres temáticos de conversación con nativos", 50000.0, Disponibilidad.DISPONIBLE);

        registrarServicioAdicional(s1);
        registrarServicioAdicional(s2);
        registrarServicioAdicional(s3);
        registrarServicioAdicional(s4);

        // Matrículas iniciales
        Matricula m1 = new Matricula("MAT-2026-001", LocalDate.of(2026, 2, 1), e1, cRegIngles, null, 10.0);
        m1.agregarServicioAdicional(s3);
        m1.agregarPago(new Pago("PAG-001", m1.getValorTotal(), LocalDate.of(2026, 2, 2), "Transferencia Bancaria"));

        Matricula m2 = new Matricula("MAT-2026-002", LocalDate.of(2026, 2, 15), e2, cPerIngles, p1, 5.0);
        m2.agregarServicioAdicional(s1);
        m2.agregarServicioAdicional(s4);
        m2.agregarPago(new Pago("PAG-002", 500000.0, LocalDate.of(2026, 2, 16), "Tarjeta de Crédito"));

        Matricula m3 = new Matricula("MAT-2026-003", LocalDate.of(2026, 3, 10), e3, cIntFrances, null, 0.0);
        m3.agregarServicioAdicional(s2);
        m3.agregarPago(new Pago("PAG-003", m3.getValorTotal(), LocalDate.of(2026, 3, 11), "Efectivo"));

        Matricula m4 = new Matricula("MAT-2026-004", LocalDate.of(2026, 3, 25), e4, cPerFrances, p2, 15.0);
        m4.agregarServicioAdicional(s1);

        registrarMatricula(m1);
        registrarMatricula(m2);
        registrarMatricula(m3);
        registrarMatricula(m4);
    }

    // Getters y Setters
    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }
}
