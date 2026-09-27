package co.edu.uniquindio.poo.parcial1programacion2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reporte {

    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private double totalIngresos;
    private int cantidadMatriculas;
    private List<Matricula> matriculas;

    public Reporte(LocalDate fechaInicial, LocalDate fechaFinal, List<Matricula> matriculas) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.matriculas = matriculas != null ? matriculas : new ArrayList<>();
        this.cantidadMatriculas = this.matriculas.size();
        this.totalIngresos = this.matriculas.stream()
                .mapToDouble(Matricula::getValorTotal)
                .sum();
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public double getTotalIngresos() {
        return totalIngresos;
    }

    public int getCantidadMatriculas() {
        return cantidadMatriculas;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }
}
