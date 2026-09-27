package co.edu.uniquindio.poo.parcial1programacion2;

import co.edu.uniquindio.poo.parcial1programacion2.model.*;
import java.util.Date;

public class MainPruebaPatronDeDiseño {
    public static void main(String[] args) {
        //probando el patron *Prototype* implementado en Estudiante
        // Crear estudiante con implementacion del patron *Builder* para facilitar
        //la lectura de los atributos y saber a que se refiere cada uno en orden etc.

        Estudiante estudianteOriginal = new Estudiante.Builder()
                .setNombreEstudiante("Juan Pérez")
                .setDocumentoEstudiante("123456789")
                .setTelefonoEstudiante("3124567890")
                .setCorreoElectronicoEstudiante("juanperez@gmail.com")
                .setEdadEstudiante(20)
                .setFechaRegistroEstudiante(new Date())
                .setNivelReferencia(NivelReferencia.B1)
                .setTipoEstudiante(TipoEstudiante.ADOLESCENTE)
                .build();

        System.out.println("Estudiante creado con Builder: " + estudianteOriginal.getNombreEstudiante());


        // Clonar con el patrón Prototype
        Estudiante estudianteClonado = estudianteOriginal.clone();

        // Modificar el clon
        estudianteClonado.actualizarDato("nombre", "Carlos Gómez");
        estudianteClonado.actualizarDato("documento", "987654321");

        // Mostrar resultados
        System.out.println("Original: " + estudianteOriginal.getNombreEstudiante());
        System.out.println("Clonado: " + estudianteClonado.getNombreEstudiante());
    }
}



