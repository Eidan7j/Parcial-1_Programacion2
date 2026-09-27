package co.edu.uniquindio.poo.parcial1programacion2.model;

/**
 * Clase que representa un servicio adicional ofrecido por la academia.
 *
 * // PRINCIPIO SOLID [SRP]: Su única responsabilidad es almacenar la información
 * // de un servicio adicional.
 */
public class ServicioAdicional {

    private String codigoServicioAdicional;
    private String nombreServicioAdicional;
    private String descripcionServicioAdicional;
    private double duracionServicioAdicional;
    private double precioUnitarioServicioAdicional;
    private Disponibilidad disponibilidadServicioAdicional;

    public ServicioAdicional(String codigoServicioAdicional, String nombreServicioAdicional,
                             String descripcionServicioAdicional, double duracionServicioAdicional,
                             double precioUnitarioServicioAdicional, Disponibilidad disponibilidadServicioAdicional) {
        this.codigoServicioAdicional = codigoServicioAdicional;
        this.nombreServicioAdicional = nombreServicioAdicional;
        this.descripcionServicioAdicional = descripcionServicioAdicional;
        this.duracionServicioAdicional = duracionServicioAdicional;
        this.precioUnitarioServicioAdicional = precioUnitarioServicioAdicional;
        this.disponibilidadServicioAdicional = disponibilidadServicioAdicional;
    }

    // Getters
    public String getCodigoServicioAdicional() { return codigoServicioAdicional; }
    public String getNombreServicioAdicional() { return nombreServicioAdicional; }
    public String getDescripcionServicioAdicional() { return descripcionServicioAdicional; }
    public double getDuracionServicioAdicional() { return duracionServicioAdicional; }
    public double getPrecioUnitarioServicioAdicional() { return precioUnitarioServicioAdicional; }
    public Disponibilidad getDisponibilidadServicioAdicional() { return disponibilidadServicioAdicional; }
}
