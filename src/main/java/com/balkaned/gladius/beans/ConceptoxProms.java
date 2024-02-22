package com.balkaned.gladius.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConceptoxProms {

    private Integer idproceso;
    private String codconcepto;
    private String codconceptaux;
    private Integer idprocesoaux;
    private String desconceptaux;
    private String desprocesoaux;

}
