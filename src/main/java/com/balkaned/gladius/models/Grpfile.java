package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Grpfile {

    @Id
    private Integer iexcodcia;
    private Integer iexcodgrpfile;
    private Integer iexcodtra;
    private String iexgrpfile;
    private String iexdesgrpfile;
    private String iexestado;
    private String iexusucrea;
    private String iexfeccrea;
    private String iexusumod;
    private String iexfecmod;
    private String desgrangrupo;
    private Integer iexcodimage;
    private String iexurlimage;
    private String iexdesimage;
    private String iexestado_det;
}
