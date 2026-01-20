package com.balkaned.gladius.locales.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Local {

    @Id
    private Integer iexcodcia;
    private String iexubicod;
    private String iexubides;
    private String iexusucrea;
    private String iexusumod;
    private String iexfeccrea;
    private String iexfecmod;

}
