package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Ciaxcon {

    @Id
    private Integer iexcodcia;
    private String iexcodcon;
    private String iexdescon;
    private String iexflgest;
    private Double iexdefval;
    private String iextipreg;

}
