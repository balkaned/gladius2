package com.balkaned.gladius.prestamo.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PrestamoDet {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private Integer iexcorrel;
    private Integer iexidcuota;
    private String iexfecpre ;
    private Double ieximpbru;
    private Double iexinteres;
    private Double ieximptotal;
    private String iexusucrea;
    private String iexfeccrea;
    private String iexusumod;
    private String iexfecmod;
    private String iexflgest;
    private Integer iexcodpropla;
    private String iexcodconpla;
    private Double ieximportepla;
    private String iexglosa;

}
