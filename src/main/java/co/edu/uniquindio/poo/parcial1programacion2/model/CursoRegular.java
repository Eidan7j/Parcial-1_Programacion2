package co.edu.uniquindio.poo.parcial1programacion2.model;

import co.edu.uniquindio.poo.parcial1programacion2.model.enums.EstadoCurso;
import co.edu.uniquindio.poo.parcial1programacion2.model.enums.TipoCurso;

public class CursoRegular extends Curso {

    public CursoRegular(String codigo, String nombre, String idioma, String descripcion, int duracionMeses,
                        double valorMensual, EstadoCurso estadoCurso) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estadoCurso, TipoCurso.REGULAR);
        agregarBeneficio("Acceso a la plataforma virtual");
    }

    @Override
    public double calcularCostoBase(Profesor profesor) {
        return getDuracionMeses() * getValorMensual();
    }
}
