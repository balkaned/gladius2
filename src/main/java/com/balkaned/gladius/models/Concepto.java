package com.balkaned.gladius.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Concepto {

    private Integer idProceso;
    private String codConcepto;
    private String desConcepto;
    private String desVariable;
    private String descripcion;
    private String desAbreviacion;
    private String desAbreviacionCapit;

}
