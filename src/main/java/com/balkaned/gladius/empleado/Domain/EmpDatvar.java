package com.balkaned.gladius.empleado.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class EmpDatvar {

    @Id
    private Integer iexcodcia;
    private Integer iexcodpro;
    private String iexnroper;
    private Integer iexcorrel;
    private Integer iexcodtra;
    private String iexcodcon;
    private String nomdestra;
    private String coodescon;
    private Double iexvalcon;
    private String iexflgest;
    private String iexusucrea;
    private String iexfeccrea;
    private String iexusumod;
    private String iexfecmod;
    private String iexcodtra2;

}
