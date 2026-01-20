package com.balkaned.gladius.conceptoXProceso.Domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ConceptoxProcesoxTra {

    @Id
    private Integer iexcodcia;
    private Integer procodpro;
    private String iexnroper;
    private Integer iexcodtra;
    private String procodcon;
    private Integer correl;
    private String coodescon;
    private String proflgbol;
    private Integer proorden;
    private Double provalor;
    private String protipcon;
    private String prodescustom;
    private String despro;
    private String codcon;
    private Double cantidad;
    private String destra;

}
