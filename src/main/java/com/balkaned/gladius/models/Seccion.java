package com.balkaned.gladius.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seccion {

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
