package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ProcesoPlanilla {

    @Id
    private Integer idProceso;
    private String  desProceso;
    private String  desProcesoCorto;
    private String idRegLab;
    private String  DesGrp;
    private String  DesRegLab;
    private String idTipProceso;
    private String bolProceso;
    private String bolProcesoind;
    private String bolProcesores;

}
