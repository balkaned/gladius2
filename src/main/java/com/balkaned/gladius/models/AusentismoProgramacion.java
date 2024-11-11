package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class AusentismoProgramacion {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private Integer iexcorrel;
    private String iextipaus;
    private String destipaus;
    private String iexfecini;
    private String iexfecfin;
    private Double iexnrodias;
    private String iexglosa;
    private Double iexmins;
    private String iexusucrea;
    private String iexfeccrea;
    private String iexusumod;
    private String iexfecmod;
    private String desnomtra;
    private String fecing;
    private String nrodoc;
    private String desestado;
    private String fecfinrep;




}
