package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class VacacionControl {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private String iexpermesini;
    private String iexpermesfin;
    private String iexfecini;
    private String iexfecfin;
    private Double iexdiasgan;
    private Double iexdiasgoz;
    private Double iexdiasven;
    private Double iexdiasper;
    private Double iexdiascom;
    private Double iexdiassaldo;
    private String iexusucrea;
    private String iexfeccrea;
    private String iexusumod;
    private String iexfecmod;

}
