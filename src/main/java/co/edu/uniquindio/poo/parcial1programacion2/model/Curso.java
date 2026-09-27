package co.edu.uniquindio.poo.parcial1programacion2.model;

import co.edu.uniquindio.poo.parcial1programacion2.model.enums.EstadoCurso;
import co.edu.uniquindio.poo.parcial1programacion2.model.enums.TipoCurso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Curso {

    private String codigo;
    private String nombre;
    private String idioma;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private EstadoCurso estadoCurso;
    private TipoCurso tipoCurso;
    private List<String> beneficios;

    public Curso(String codigo, String nombre, String idioma, String descripcion, int duracionMeses,
                 double valorMensual, EstadoCurso estadoCurso, TipoCurso tipoCurso) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idioma = idioma;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estadoCurso = estadoCurso != null ? estadoCurso : EstadoCurso.ACTIVO;
        this.tipoCurso = tipoCurso;
        this.beneficios = new ArrayList<>();
    }

    public abstract double calcularCostoBase(Profesor profesor);

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public double getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(double valorMensual) {
        this.valorMensual = valorMensual;
    }

    public EstadoCurso getEstadoCurso() {
        return estadoCurso;
    }

    public void setEstadoCurso(EstadoCurso estadoCurso) {
        this.estadoCurso = estadoCurso;
    }

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public List<String> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(List<String> beneficios) {
        this.beneficios = beneficios != null ? beneficios : new ArrayList<>();
    }

    public void agregarBeneficio(String beneficio) {
        if (beneficio != null && !beneficio.trim().isEmpty() && !beneficios.contains(beneficio.trim())) {
            beneficios.add(beneficio.trim());
        }
    }

    public String getBeneficiosFormateados() {
        if (beneficios == null || beneficios.isEmpty()) {
            return "Ninguno";
        }
        return String.join(", ", beneficios);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(codigo, curso.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + idioma + " - " + tipoCurso + ")";
    }
}
