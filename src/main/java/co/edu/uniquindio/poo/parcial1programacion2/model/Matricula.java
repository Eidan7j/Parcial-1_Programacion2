package co.edu.uniquindio.poo.parcial1programacion2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Matricula {

    private String codigo;
    private LocalDate fecha;
    private Estudiante estudiante;
    private Curso curso;
    private Profesor profesor;
    private List<ServicioAdicional> serviciosAdicionales;
    private double descuentoPorcentaje;
    private List<Pago> pagos;

    public Matricula(String codigo, LocalDate fecha, Estudiante estudiante, Curso curso, Profesor profesor, double descuentoPorcentaje) {
        this.codigo = codigo;
        this.fecha = fecha != null ? fecha : LocalDate.now();
        this.estudiante = estudiante;
        this.curso = curso;
        this.profesor = profesor;
        this.descuentoPorcentaje = Math.max(0.0, Math.min(100.0, descuentoPorcentaje));
        this.serviciosAdicionales = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }

    public double calcularCostoCurso() {
        if (curso == null) return 0.0;
        return curso.calcularCostoBase(profesor);
    }

    public double calcularCostoServicios() {
        if (serviciosAdicionales == null || serviciosAdicionales.isEmpty()) {
            return 0.0;
        }
        return serviciosAdicionales.stream()
                .mapToDouble(ServicioAdicional::getPrecio)
                .sum();
    }

    public double calcularSubtotal() {
        return calcularCostoCurso() + calcularCostoServicios();
    }

    public double calcularMontoDescuento() {
        return calcularSubtotal() * (descuentoPorcentaje / 100.0);
    }

    public double calcularValorTotal() {
        double subtotal = calcularSubtotal();
        double descuento = calcularMontoDescuento();
        return Math.max(0.0, subtotal - descuento);
    }

    public double getValorTotal() {
        return calcularValorTotal();
    }

    public void agregarServicioAdicional(ServicioAdicional servicio) {
        if (servicio != null && !serviciosAdicionales.contains(servicio)) {
            serviciosAdicionales.add(servicio);
        }
    }

    public void removerServicioAdicional(ServicioAdicional servicio) {
        serviciosAdicionales.remove(servicio);
    }

    public void agregarPago(Pago pago) {
        if (pago != null && !pagos.contains(pago)) {
            pagos.add(pago);
        }
    }

    public double getTotalPagado() {
        return pagos.stream().mapToDouble(Pago::getMonto).sum();
    }

    public double getSaldoPendiente() {
        return Math.max(0.0, getValorTotal() - getTotalPagado());
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales != null ? serviciosAdicionales : new ArrayList<>();
    }

    public double getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setDescuentoPorcentaje(double descuentoPorcentaje) {
        this.descuentoPorcentaje = Math.max(0.0, Math.min(100.0, descuentoPorcentaje));
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos != null ? pagos : new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(codigo, matricula.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Matrícula " + codigo + " - " + (estudiante != null ? estudiante.getNombreCompleto() : "")
                + " (" + (curso != null ? curso.getNombre() : "") + ") Total: $" + getValorTotal();
    }
}
