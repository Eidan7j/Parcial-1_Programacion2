package co.edu.uniquindio.poo.parcial1programacion2.model;

import co.edu.uniquindio.poo.parcial1programacion2.model.enums.Disponibilidad;
import co.edu.uniquindio.poo.parcial1programacion2.model.enums.EstadoCurso;
import co.edu.uniquindio.poo.parcial1programacion2.model.enums.Nivel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LenguajeCafeteroTest {

    private LenguajeCafetero academia = new LenguajeCafetero(
            "Academia de Idiomas LenguajeCafetero",
            "NIT 901.847.231-5",
            "Carrera 14 # 21-35",
            "(606) 745-9820",
            "contacto@lenguajecafetero.edu.co",
            "www.lenguajecafetero.edu.co"
    );

    @BeforeEach
    public void setUp() {
        academia = new LenguajeCafetero(
                "Academia de Idiomas LenguajeCafetero",
                "NIT 901.847.231-5",
                "Carrera 14 # 21-35",
                "(606) 745-9820",
                "contacto@lenguajecafetero.edu.co",
                "www.lenguajecafetero.edu.co"
        );
    }

    @Test
    public void testRegistrarYBuscarEstudiantePorDocumento() {
        Estudiante estudiante = new Estudiante(
                "1094909999",
                "Carlos Andrés Restrepo",
                "3101234567",
                "carlos@gmail.com",
                22,
                LocalDate.of(2026, 3, 1)
        );

        assertTrue(academia.registrarEstudiante(estudiante));

        // Intento de registro duplicado
        assertFalse(academia.registrarEstudiante(estudiante));

        // Búsqueda por documento (Requerimiento de Consulta 1)
        Estudiante encontrado = academia.buscarEstudiante("1094909999");
        assertNotNull(encontrado);
        assertEquals("Carlos Andrés Restrepo", encontrado.getNombreCompleto());
        assertEquals(22, encontrado.getEdad());

        // Búsqueda de documento inexistente
        assertNull(academia.buscarEstudiante("0000000000"));
    }

    @Test
    public void testCostoCursoRegular() {
        CursoRegular curso = new CursoRegular(
                "CUR-REG-01",
                "Inglés Básico",
                "Inglés",
                "Curso regular de inglés",
                6, // 6 meses
                200000.0, // 200.000 por mes
                EstadoCurso.ACTIVO
        );

        // 6 * 200.000 = 1.200.000
        assertEquals(1200000.0, curso.calcularCostoBase(null));
        assertTrue(curso.getBeneficios().contains("Acceso a la plataforma virtual"));
    }

    @Test
    public void testCostoCursoPersonalizadoConProfesor() {
        Profesor profesor = new Profesor("P-01", "Emma Watson", "Inglés", "3001234567", 50000.0);
        CursoPersonalizado cursoPersonalizado = new CursoPersonalizado(
                "CUR-PER-01",
                "Inglés de Negocios Avanzado",
                "Inglés",
                "Preparación individual para ejecutivos",
                4, // 4 meses
                300000.0, // 300.000 mensual -> 1.200.000
                EstadoCurso.ACTIVO,
                10, // 10 sesiones con profesor
                Nivel.C1,
                "Negociación y presentaciones internacionales"
        );

        // Costo = (4 * 300.000) + (10 * 50.000) = 1.200.000 + 500.000 = 1.700.000
        double costoEsperado = (4 * 300000.0) + (10 * 50000.0);
        assertEquals(costoEsperado, cursoPersonalizado.calcularCostoBase(profesor));
    }

    @Test
    public void testMatriculaConServiciosAdicionalesYDescuento() {
        Estudiante est = new Estudiante("1094901111", "Laura Gómez", "3111111111", "laura@gmail.com", 20, LocalDate.now());
        CursoRegular curso = new CursoRegular("CUR-01", "Portugués", "Portugués", "Desc", 3, 150000.0, EstadoCurso.ACTIVO);
        // Costo base curso: 3 * 150.000 = 450.000

        ServicioAdicional srv1 = new ServicioAdicional("SRV-01", "Simulacro", "Desc", 80000.0, Disponibilidad.DISPONIBLE);
        ServicioAdicional srv2 = new ServicioAdicional("SRV-02", "Material Impreso", "Desc", 50000.0, Disponibilidad.DISPONIBLE);

        // Descuento del 10%
        Matricula matricula = new Matricula("MAT-001", LocalDate.now(), est, curso, null, 10.0);
        matricula.agregarServicioAdicional(srv1);
        matricula.agregarServicioAdicional(srv2);

        // Costo curso: 450.000
        // Costo servicios: 80.000 + 50.000 = 130.000
        // Subtotal = 580.000
        // Descuento 10% = 58.000
        // Total = 522.000
        assertEquals(450000.0, matricula.calcularCostoCurso());
        assertEquals(130000.0, matricula.calcularCostoServicios());
        assertEquals(580000.0, matricula.calcularSubtotal());
        assertEquals(58000.0, matricula.calcularMontoDescuento());
        assertEquals(522000.0, matricula.getValorTotal());
    }

    @Test
    public void testPagosMatriculaYSaldo() {
        Estudiante est = new Estudiante("1094902222", "Daniel Toro", "3122222222", "daniel@gmail.com", 24, LocalDate.now());
        CursoRegular curso = new CursoRegular("CUR-02", "Inglés", "Inglés", "Desc", 2, 200000.0, EstadoCurso.ACTIVO);
        Matricula matricula = new Matricula("MAT-002", LocalDate.now(), est, curso, null, 0.0);

        // Total: 400.000
        assertEquals(400000.0, matricula.getValorTotal());
        assertEquals(400000.0, matricula.getSaldoPendiente());

        // Registrar pago parcial de 150.000
        Pago pago1 = new Pago("PAG-01", 150000.0, LocalDate.now(), "Efectivo");
        matricula.agregarPago(pago1);

        assertEquals(150000.0, matricula.getTotalPagado());
        assertEquals(250000.0, matricula.getSaldoPendiente());

        // Registrar segundo pago de 250.000
        Pago pago2 = new Pago("PAG-02", 250000.0, LocalDate.now(), "Transferencia");
        matricula.agregarPago(pago2);

        assertEquals(400000.0, matricula.getTotalPagado());
        assertEquals(0.0, matricula.getSaldoPendiente());
    }

    @Test
    public void testCalcularIngresosPeriodo() {
        // Cargar datos de prueba que tienen matrículas en febrero y marzo de 2026
        academia.cargarDatosPrueba();

        List<Matricula> todas = academia.getMatriculas();
        assertFalse(todas.isEmpty());

        // Periodo de febrero 2026 (1 de febrero al 28 de febrero)
        LocalDate fIniFeb = LocalDate.of(2026, 2, 1);
        LocalDate fFinFeb = LocalDate.of(2026, 2, 28);

        double ingresosFeb = academia.calcularIngresosPeriodo(fIniFeb, fFinFeb);
        assertTrue(ingresosFeb > 0);

        Reporte reporteFeb = academia.generarReporteIngresos(fIniFeb, fFinFeb);
        assertEquals(ingresosFeb, reporteFeb.getTotalIngresos());
        assertEquals(2, reporteFeb.getCantidadMatriculas()); // m1 y m2 registradas en febrero

        // Periodo de todo el año 2026
        LocalDate fIniAnio = LocalDate.of(2026, 1, 1);
        LocalDate fFinAnio = LocalDate.of(2026, 12, 31);
        double ingresosAnio = academia.calcularIngresosPeriodo(fIniAnio, fFinAnio);

        // Debe sumar todas las matrículas
        double sumaManual = todas.stream().mapToDouble(Matricula::getValorTotal).sum();
        assertEquals(sumaManual, ingresosAnio);

        // Periodo en el pasado donde no hay matrículas (ej: año 2020)
        double ingresosCero = academia.calcularIngresosPeriodo(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 31));
        assertEquals(0.0, ingresosCero);
    }
}
