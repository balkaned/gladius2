package com.balkaned.gladius.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ProcesoForm {

    @Id
    private Integer procodpro;
    private String prodespro;
    private String prodescorto;
    private String procodregimenlab;
    private String progrppro;
    private String bolproceso;
    private String idtipproceso;
    private String bolprocesoind;
    private String bolprocesores;
    private Integer diasteo;
}
