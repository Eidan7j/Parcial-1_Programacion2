package co.edu.uniquindio.poo.parcial1programacion2.model;

import java.util.Objects;

public class Profesor {

    private String identificacion;
    private String nombre;
    private String idioma;
    private String telefono;
    private double tarifaSesion;

    public Profesor(String identificacion, String nombre, String idioma, String telefono, double tarifaSesion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.idioma = idioma;
        this.telefono = telefono;
        this.tarifaSesion = tarifaSesion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getTarifaSesion() {
        return tarifaSesion;
    }

    public void setTarifaSesion(double tarifaSesion) {
        this.tarifaSesion = tarifaSesion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profesor profesor = (Profesor) o;
        return Objects.equals(identificacion, profesor.identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacion);
    }

    @Override
    public String toString() {
        return nombre + " (" + idioma + " - $" + tarifaSesion + "/sesión)";
    }
}
