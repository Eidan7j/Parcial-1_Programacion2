package co.edu.uniquindio.poo.parcial1programacion2.model;

import java.time.LocalDate;
import java.util.Objects;

public class Pago {

    private String idPago;
    private double monto;
    private LocalDate fechaPago;
    private String metodoPago;

    public Pago(String idPago, double monto, LocalDate fechaPago, String metodoPago) {
        this.idPago = idPago;
        this.monto = monto;
        this.fechaPago = fechaPago != null ? fechaPago : LocalDate.now();
        this.metodoPago = metodoPago != null ? metodoPago : "Efectivo";
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pago pago = (Pago) o;
        return Objects.equals(idPago, pago.idPago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago);
    }

    @Override
    public String toString() {
        return "Pago #" + idPago + " ($" + monto + " - " + fechaPago + " [" + metodoPago + "])";
    }
}
