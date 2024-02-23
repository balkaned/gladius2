package com.balkaned.gladius.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Concepto {

    private Integer idProceso;
    private String codConcepto;
    private String desConcepto;
    private String desVariable;
    private String descripcion;
    private String desAbreviacion;
    private String desAbreviacionCapit;

}
