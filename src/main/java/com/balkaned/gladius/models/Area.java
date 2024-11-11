package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Area {

    @Id
    private Integer iexcodcia;
    private String iexcodarea;
    private String iexdesarea;
    private String iexdesarea_descripcion;
    private String iexcodcat;
    private String descodcat;
    private String iexareapadre;
    private String desareapadre;
    private String iexusucrea;
    private String iexusumod;
    private String iexfeccrea;
    private String iexfecmod;
}
