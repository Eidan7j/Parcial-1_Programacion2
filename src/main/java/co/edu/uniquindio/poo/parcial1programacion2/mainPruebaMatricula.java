package co.edu.uniquindio.poo.parcial1programacion2;

import co.edu.uniquindio.poo.parcial1programacion2.model.*;

import java.util.Date;

public class mainPruebaMatricula {
        public static void main(String[] args) {

            // Crear profesor
            Profesor profesor = new Profesor(
                    "P001",              // idProfesor
                    "Carlos Gómez",      // nombreProfesor
                    "Inglés",            // idiomaProfesor
                    "3124567890",        // telefonoProfesor
                    80000                // tarifaSesion
            );


            // Crear estudiante y curso
            Estudiante estudiante = new Estudiante.Builder()
                    .setNombreEstudiante("Juan Pérez")
                    .setDocumentoEstudiante("123456789")
                    .setTelefonoEstudiante("3124567890")
                    .setCorreoElectronicoEstudiante("juanperez@gmail.com")
                    .setEdadEstudiante(20)
                    .setFechaRegistroEstudiante(new Date())
                    .setNivelReferencia(NivelReferencia.B1)
                    .setTipoEstudiante(TipoEstudiante.ADOLESCENTE)
                    .build();

            CursoPersonalizado curso = new CursoPersonalizado(
                    "C001", "Matemáticas", "Español", "Curso básico",
                    6, 120000, EstadoCurso.ACTIVO,
                    10, "B1", "Adolescente", profesor, estudiante
            );


            // Crear matrícula
            Matricula matriculaJuan = new Matricula("M001", new Date(), estudiante, curso);

            // Crear servicio adicional
            ServicioAdicional tutorias = new ServicioAdicional(
                    "S001", "Tutorías personalizadas", "Sesiones extra de apoyo",
                    2.0, 50000, Disponibilidad.DISPONIBLE
            );

            // Agregar servicio a la matrícula de Juan
            matriculaJuan.agregarServicioAdicional(tutorias);

            // Verificar que quedó guardado
            System.out.println("Servicios adicionales registrados: " +
                    matriculaJuan.getListaServiciosAdicionales().size());
        }
}


