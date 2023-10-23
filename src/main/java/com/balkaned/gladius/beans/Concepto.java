package com.balkaned.gladius.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Concepto {
    Integer idProceso;
    String codConcepto;
    String desConcepto;
    String desVariable;
    String descripcion;
    String desAbreviacion;
}
