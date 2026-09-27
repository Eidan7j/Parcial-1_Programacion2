package co.edu.uniquindio.poo.parcial1programacion2.model;

import co.edu.uniquindio.poo.parcial1programacion2.model.enums.EstadoCurso;
import co.edu.uniquindio.poo.parcial1programacion2.model.enums.TipoCurso;

public class CursoIntensivo extends Curso {

    public CursoIntensivo(String codigo, String nombre, String idioma, String descripcion, int duracionMeses,
                          double valorMensual, EstadoCurso estadoCurso) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estadoCurso, TipoCurso.INTENSIVO);
        agregarBeneficio("Acceso a la plataforma virtual");
        agregarBeneficio("Material didáctico incluido");
        agregarBeneficio("Acceso a clubes de conversación");
    }

    @Override
    public double calcularCostoBase(Profesor profesor) {
        return getDuracionMeses() * getValorMensual();
    }
}
