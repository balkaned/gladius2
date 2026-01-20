package com.balkaned.gladius.vacaciones.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class VacacionProgramacion {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private Integer iexcorrel;
    private String iexfecini;
    private String iexfecfin;
    private Double iexnrodias;
    private String iextipvac;
    private String destipvac;
    private String iexglosa;
    private String iexpermesini;
    private String iexpermesfin;
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
