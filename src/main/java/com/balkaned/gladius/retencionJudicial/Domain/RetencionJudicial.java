package com.balkaned.gladius.retencionJudicial.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class RetencionJudicial {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private Integer iexcorrel;
    private Integer iexcodpro;
    private String descodpro;
    private String iextipretjud;
    private String destipretjud;
    private String  iexresolucion;
    private String iexfecini;
    private String iexfecfin;
    private Double iexpordesct;
    private Double ieximpfijo;
    private String iexusucrea;
    private String iexfeccrea;
    private String iexusumod;
    private String iexfecmod;

}
