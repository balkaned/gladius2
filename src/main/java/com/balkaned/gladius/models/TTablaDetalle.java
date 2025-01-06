package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class TTablaDetalle {

    @Id
    private String iexcodtab;
    private String iexkey;
    private String desdet;
    private String des1det;
    private String des2det;
    private String des3det;
    private String des4det;
    private String des5det;
    private String des6det;
    private String des7det;
    private String des8det;
    private Double val9det;
    private Double val10det;
    private Double val11det;
    private Double val12det;
    private Double val13det;
    private Double val14det;
    private Double val15det;
    private Double val16det;

}
