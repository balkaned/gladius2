package com.balkaned.gladius.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcesoPlanilla {

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
