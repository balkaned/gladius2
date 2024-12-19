package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Retirados {

    @Id
    private int iexcodtra;
    private String iexnomtra;
    private String iexapepat;
    private String iexapemat;
    private String iexfecret;

}
