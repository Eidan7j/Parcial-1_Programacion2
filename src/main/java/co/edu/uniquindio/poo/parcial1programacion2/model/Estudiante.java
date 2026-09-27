package co.edu.uniquindio.poo.parcial1programacion2.model;
//debemos importar este para que coja builder
import javafx.util.Builder;
import java.util.Date;
/**
 * Clase que representa a un estudiante dentro de la academia.
 *
 * // PRINCIPIO SOLID [SRP]: Su única responsabilidad es almacenar y gestionar
 * // la información de un estudiante.
 *
 * // PATRÓN DE DISEÑO [Prototype]: Permite crear copias de objetos existentes
 */
public class Estudiante implements Cloneable {

        // Atributos
        private String nombreEstudiante;
        private String documentoEstudiante;
        private String telefonoEstudiante;
        private String correoElectronicoEstudiante;
        private int edadEstudiante;
        private Date fechaRegistroEstudiante;

        // Relación con enums
        private NivelReferencia nivelReferencia;
        private TipoEstudiante tipoEstudiante;

        //  Constructor
        private Estudiante(Builder builder) {
            this.nombreEstudiante = builder.nombreEstudiante;
            this.documentoEstudiante = builder.documentoEstudiante;
            this.telefonoEstudiante = builder.telefonoEstudiante;
            this.correoElectronicoEstudiante = builder.correoElectronicoEstudiante;
            this.edadEstudiante = builder.edadEstudiante;
            this.fechaRegistroEstudiante = builder.fechaRegistroEstudiante;
            this.nivelReferencia = builder.nivelReferencia;
            this.tipoEstudiante = builder.tipoEstudiante;
        }
        //Prototype metodo (clonación)
        @Override
        public Estudiante clone() {
            return new Estudiante.Builder()
                    .setNombreEstudiante(this.nombreEstudiante)
                    .setDocumentoEstudiante(this.documentoEstudiante)
                    .setTelefonoEstudiante(this.telefonoEstudiante)
                    .setCorreoElectronicoEstudiante(this.correoElectronicoEstudiante)
                    .setEdadEstudiante(this.edadEstudiante)
                    .setFechaRegistroEstudiante(this.fechaRegistroEstudiante)
                    .setNivelReferencia(this.nivelReferencia)
                    .setTipoEstudiante(this.tipoEstudiante)
                    .build();
        }

    // Clase interna Builder
    public static class Builder {
        private String nombreEstudiante;
        private String documentoEstudiante;
        private String telefonoEstudiante;
        private String correoElectronicoEstudiante;
        private int edadEstudiante;
        private Date fechaRegistroEstudiante;
        private NivelReferencia nivelReferencia;
        private TipoEstudiante tipoEstudiante;

        public Builder setNombreEstudiante(String nombreEstudiante) {
            this.nombreEstudiante = nombreEstudiante;
            return this;
        }

        public Builder setDocumentoEstudiante(String documentoEstudiante) {
            this.documentoEstudiante = documentoEstudiante;
            return this;
        }

        public Builder setTelefonoEstudiante(String telefonoEstudiante) {
            this.telefonoEstudiante = telefonoEstudiante;
            return this;
        }

        public Builder setCorreoElectronicoEstudiante(String correoElectronicoEstudiante) {
            this.correoElectronicoEstudiante = correoElectronicoEstudiante;
            return this;
        }

        public Builder setEdadEstudiante(int edadEstudiante) {
            this.edadEstudiante = edadEstudiante;
            return this;
        }

        public Builder setFechaRegistroEstudiante(Date fechaRegistroEstudiante) {
            this.fechaRegistroEstudiante = fechaRegistroEstudiante;
            return this;
        }

        public Builder setNivelReferencia(NivelReferencia nivelReferencia) {
            this.nivelReferencia = nivelReferencia;
            return this;
        }

        public Builder setTipoEstudiante(TipoEstudiante tipoEstudiante) {
            this.tipoEstudiante = tipoEstudiante;
            return this;
        }

        public Estudiante build() {
            return new Estudiante(this);
        }
    }

        // Método de actualización de datos de estudiante con condicionales
        public void actualizarDato(String campo, String nuevoValor) {
            if (campo.equalsIgnoreCase("telefono")) {
                this.telefonoEstudiante = nuevoValor;
            }
            else if (campo.equalsIgnoreCase("correo")) {
                this.correoElectronicoEstudiante = nuevoValor;
            }
            else if (campo.equalsIgnoreCase("nombre")) {
                this.nombreEstudiante = nuevoValor;
            }
            else if (campo.equalsIgnoreCase("documento")) {
                this.documentoEstudiante = nuevoValor;
            }
            else if (campo.equalsIgnoreCase("edad")) {
                this.edadEstudiante = Integer.parseInt(nuevoValor);
            }
            else if (campo.equalsIgnoreCase("fechaRegistro")) {
                // guardamos la fecha como texto en lugar de convertirla
                this.fechaRegistroEstudiante = new java.util.Date(nuevoValor);
            }
            else {
                System.out.println("Campo no válido: " + campo);
            }
        }

        // Getters
        public String getNombreEstudiante() { return nombreEstudiante; }
        public String getDocumentoEstudiante() { return documentoEstudiante; }
        public String getTelefonoEstudiante() { return telefonoEstudiante; }
        public String getCorreoElectronicoEstudiante() { return correoElectronicoEstudiante; }
        public int getEdadEstudiante() { return edadEstudiante; }
        public Date getFechaRegistroEstudiante() { return fechaRegistroEstudiante; }
        public NivelReferencia getNivelReferencia() { return nivelReferencia; }
        public TipoEstudiante getTipoEstudiante() { return tipoEstudiante; }
    }

