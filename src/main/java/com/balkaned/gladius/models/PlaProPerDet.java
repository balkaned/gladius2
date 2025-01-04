package com.balkaned.gladius.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PlaProPerDet {

    @Id
    private Integer iexcodcia;
    private Integer iexcodpro;
    private String descodpro;
    private String iexnroper;
    private String iexpermes;
    private Integer iexcodtra;
    private String destra;
    private String grppro;
    private Integer iexcorrel;
    private String fecini;
    private String fecfin;
    private Double nrodia;
    private String feccuota;
    private Integer idcuota;
    private Double cuota;
    private String tiporegistro;
    private String codcon;
    private String periodo_proceso;
    private String periodo_anterior;
    private String codcon_final;
    private String codcon_origen;
    private Double valor_con;

}
