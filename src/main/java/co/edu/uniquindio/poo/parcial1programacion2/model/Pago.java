package co.edu.uniquindio.poo.parcial1programacion2.model;

import java.util.Date;

/**
 * Clase que representa un pago realizado por una matrícula.
 *
 * // PRINCIPIO SOLID [SRP]: Su única responsabilidad es almacenar la información
 * // de un pago individual.
 */
public class Pago {

    // Atributos
    private String idPago;
    private double montoPago;
    private Date fechaPago;

    //Constructor
    public Pago(String idPago, double montoPago, Date fechaPago) {
        this.idPago = idPago;
        this.montoPago = montoPago;
        this.fechaPago = fechaPago;
    }

    //Método registrarPago
    //este metodo lo que hace es registrar un pago con sus datos ya sea nuevo lo crea
    //si es viejo lo actualiza con su nuevo monto, en pocas palabras sirve para construir pagos y modificarlos
    //el metodo de matricula de
    public void registrarPago(String idPago, double monto, Date fecha) {
        this.idPago = idPago;
        this.montoPago = monto;
        this.fechaPago = fecha;
        System.out.println("Pago registrado con ID: " + idPago);
    }

    //Getters
    public String getIdPago() { return idPago; }
    public double getMontoPago() { return montoPago; }
    public Date getFechaPago() { return fechaPago; }
}
