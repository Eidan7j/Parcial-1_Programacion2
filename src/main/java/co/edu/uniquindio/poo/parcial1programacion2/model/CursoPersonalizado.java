package co.edu.uniquindio.poo.parcial1programacion2.model;

import co.edu.uniquindio.poo.parcial1programacion2.model.enums.EstadoCurso;
import co.edu.uniquindio.poo.parcial1programacion2.model.enums.Nivel;
import co.edu.uniquindio.poo.parcial1programacion2.model.enums.TipoCurso;

public class CursoPersonalizado extends Curso {

    private int cantidadSesiones;
    private Nivel nivelReferencia;
    private String objetivosEstudiante;

    public CursoPersonalizado(String codigo, String nombre, String idioma, String descripcion, int duracionMeses,
                              double valorMensual, EstadoCurso estadoCurso, int cantidadSesiones,
                              Nivel nivelReferencia, String objetivosEstudiante) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estadoCurso, TipoCurso.PERSONALIZADO);
        this.cantidadSesiones = cantidadSesiones;
        this.nivelReferencia = nivelReferencia;
        this.objetivosEstudiante = objetivosEstudiante;
        agregarBeneficio("Sesiones 1 a 1 con profesor personalizado");
        agregarBeneficio("Acceso a plataforma virtual");
        agregarBeneficio("Material didáctico adaptado");
        agregarBeneficio("Clubes de conversación");
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public Nivel getNivelReferencia() {
        return nivelReferencia;
    }

    public void setNivelReferencia(Nivel nivelReferencia) {
        this.nivelReferencia = nivelReferencia;
    }

    public String getObjetivosEstudiante() {
        return objetivosEstudiante;
    }

    public void setObjetivosEstudiante(String objetivosEstudiante) {
        this.objetivosEstudiante = objetivosEstudiante;
    }

    @Override
    public double calcularCostoBase(Profesor profesor) {
        double costoMensualTotal = getDuracionMeses() * getValorMensual();
        double costoSesiones = (profesor != null) ? (cantidadSesiones * profesor.getTarifaSesion()) : 0.0;
        return costoMensualTotal + costoSesiones;
    }
}
