package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class EmpSueldo {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private String iexcodcon;
    private String descon;
    private Double iexvalcon;
    private String iexflgest;

}
