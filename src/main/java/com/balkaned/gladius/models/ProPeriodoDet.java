package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ProPeriodoDet {

    @Id
    private Integer iexcodcia;
    private Integer iexcodpro;
    private String iexnroper;
    private String iexpermes;
    private Integer iexcodtra;
    private String codcon;
    private String varcon;
    private String tipocon;
    private Double value;
    private String Keyval;

}
