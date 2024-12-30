package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ParametrosGen {

    @Id
    private String iexcodcon;
    private String descon;
    private Double iexvalcon;
    private String iexdesobs;
    private String iexflgest;
    private String iextippar;
    private String destippar;
    private String iexusucrea;
    private String iexusumod;
    private String iexfeccrea;
    private String iexfecmod;

}
