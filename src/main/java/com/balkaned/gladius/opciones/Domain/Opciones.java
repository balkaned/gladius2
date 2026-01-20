package com.balkaned.gladius.opciones.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Opciones {

    @Id
    private Integer iexcodopc;
    private String iexdesopc;
    private String iexurlopc;
    private String iexurlimg;
    private String iexflgest;
    private Integer iexcodsec;
    private String dessec;
    private String dessys;
    private String iexdescripcion;
    private String iexcodapps;
    private String iexaction;
    private String iexusucre;
    private String iexfeccre;
    private String iexusumod;
    private String iexfecmod;
    private String iexactionspring;

}
