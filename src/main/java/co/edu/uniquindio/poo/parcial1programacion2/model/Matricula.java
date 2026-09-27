package co.edu.uniquindio.poo.parcial1programacion2.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una matrícula en la academia.
 *
 * // PRINCIPIO SOLID [SRP]: Su única responsabilidad es gestionar la inscripción
 * // de un estudiante en un curso.
 *
 * // PATRÓN DE DISEÑO [Asociación]: Relaciona Estudiante y Curso.
 */

public class Matricula {

    //atributos
    private String codigoMatricula;
    private Date fechaMatricula;
    private Estudiante estudiante;
    private Curso curso;
    private List<Pago> listaPagos;
    private List<ServicioAdicional> listaServiciosAdicionales; // relación con servicios adicionales

    // Constructor
    public Matricula(String codigoMatricula, Date fechaMatricula,
                     Estudiante estudiante, Curso curso) {
        this.codigoMatricula = codigoMatricula;
        this.fechaMatricula = fechaMatricula;
        this.estudiante = estudiante;
        this.curso = curso;
        this.listaPagos = new ArrayList<>();
        this.listaServiciosAdicionales = new ArrayList<>(); // ✅ nombre correcto
    }

        // Métodos de lógica

        // Registrar un pago asociado a la matrícula
        public void registrarPagoMatricula(Pago pago) {
            listaPagos.add(pago);
            System.out.println("Pago registrado para matrícula " + codigoMatricula);
        }

        // Calcular el total pagado de una sola matricula
        // como funciona Aquí solo suma los pagos asociados a esa matrícula, porque listaPagos pertenece al objeto Matricula.
        //Cada matrícula tiene su propia lista, así que el acumulador se reinicia para cada una nueva matricula
        public double calcularValorTotalMatricula() {
            double acumuladorTotal = 0;
            for (Pago pago : listaPagos) {
                acumuladorTotal += pago.getMontoPago();
            }
            return acumuladorTotal;
        }

    // Agregar un servicio adicional a la matrícula de un estudiante específico, listaServiciosAdicionales es el atributo de matricula
    // que guarda los servicios adicionales pedido por el estudiante
    public void agregarServicioAdicional(ServicioAdicional servicio) {
        if (servicio.getDisponibilidadServicioAdicional() == Disponibilidad.DISPONIBLE) {
            listaServiciosAdicionales.add(servicio);
            System.out.println("Servicio '" + servicio.getNombreServicioAdicional() +
                    "' agregado a la matrícula del estudiante: " +
                    estudiante.getNombreEstudiante());
        } else {
            System.out.println("⚠️ El servicio '" + servicio.getNombreServicioAdicional() +
                    "' no está disponible para el estudiante: " +
                    estudiante.getNombreEstudiante());
        }
    }


    // Getters
        public String getCodigoMatricula() { return codigoMatricula; }
        public Date getFechaMatricula() { return fechaMatricula; }
        public Estudiante getEstudiante() { return estudiante; }
        public Curso getCurso() { return curso; }
        public List<Pago> getListaPagos() { return listaPagos; }
        public List<ServicioAdicional> getListaServiciosAdicionales() { return listaServiciosAdicionales; }

}


