package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Puesto {

    @Id
    private Integer iexcodcia;
    private String iexpuesto;
    private String iexdespuesto;
    private String iexcodcat;
    private String descodcat;
    private String iexusucrea;
    private String iexusumod;
    private String iexfeccrea;
    private String iexfecmod;

}
