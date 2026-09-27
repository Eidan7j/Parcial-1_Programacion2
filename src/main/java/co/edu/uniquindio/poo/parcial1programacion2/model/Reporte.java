package co.edu.uniquindio.poo.parcial1programacion2.model;

import java.util.Date;
import java.util.List;

/**
 * Clase que representa un reporte dentro de la academia.
 *
 * // PRINCIPIO SOLID [SRP]: Su única responsabilidad es generar informes, trabaja con atributos de otras clases
 * // relacionados con ingresos y estudiantes activos.
 */
public class Reporte {

    //  Generar reporte de ingresos
    // en conclusion si la fecha de la matricula s eencuentra entre el rango de fecha inicio
    //o tambien se realizo antes de la fecha fin se considera valida la matricula y se suma en el acumulador su valor de matricula
    public double generarReporteIngresos(Date fechaInicio, Date fechaFin, List<Matricula> listaMatriculas) {
        //acumulador de ingresos
        double ingresosGenerados = 0;

        for (Matricula matricula : listaMatriculas) {
            Date fechaMatricula = matricula.getFechaMatricula();
                //la matrícula se hizo exactamente en la fecha de inicio o tambien la matrícula se hizo después de la fecha de inicio
            if ((fechaMatricula.equals(fechaInicio) || fechaMatricula.after(fechaInicio)) &&
                    // la matrícula se hizo exactamente en la fecha fin o tambien la matrícula se hizo antes de la fecha fin
                    (fechaMatricula.equals(fechaFin) || fechaMatricula.before(fechaFin))) {
                ingresosGenerados += matricula.calcularValorTotalMatricula();
            }

        }

        return ingresosGenerados;
    }

}
