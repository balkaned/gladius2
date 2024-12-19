package com.balkaned.gladius.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class FormulaPlanilla {

    @Id
    private Integer idProceso;
    private Integer idFormula;
    private String desGlosa;
    private String desFormula;
    private String idConcepto;
    private String desConcepto;
    private String flgEstado;
    private Integer nroOrden;
    private String tipOut;
    private String desVar;
    private String usuCrea;
    private String fecCrea;
    private String usuMod;
    private String fecMod;
    private String cooforVar;
    private String sqlprogram;
    private String grpeje;

}
