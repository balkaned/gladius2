package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ProcesoPlanillaxCia {

    @Id
    private Integer procodcia;
    private Integer procodpro;
    private String bolproceso;
    private String idtipproceso;
    private String bolproindividual;
    private String bolproresumen;
    private String rep_parameter;
    private String rep_ingresos;
    private String rep_descuentos;
    private String rep_aportes;

}
