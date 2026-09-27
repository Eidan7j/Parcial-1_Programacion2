package co.edu.uniquindio.poo.parcial1programacion2.model;

import java.time.LocalDate;
import java.util.Objects;

public class Estudiante {

    private String documentoIdentidad;
    private String nombreCompleto;
    private String telefono;
    private String correoElectronico;
    private int edad;
    private LocalDate fechaRegistro;

    public Estudiante(String documentoIdentidad, String nombreCompleto, String telefono, String correoElectronico, int edad, LocalDate fechaRegistro) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.edad = edad;
        this.fechaRegistro = fechaRegistro != null ? fechaRegistro : LocalDate.now();
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(documentoIdentidad, that.documentoIdentidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentoIdentidad);
    }

    @Override
    public String toString() {
        return nombreCompleto + " (" + documentoIdentidad + ")";
    }
}
