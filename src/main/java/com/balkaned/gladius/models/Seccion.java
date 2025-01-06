package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Seccion {

    @Id
    private Integer iexcodsec;
    private String iexdessec;
    private Integer iexordsec;
    private Integer iexcodsys;
    private String dessys;
    private String iexsecurl;
    private String iexsecimg;
    private String iexsecobs;
    private String iexactiondef;

}
