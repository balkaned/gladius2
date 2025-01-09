package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class RegimenLaboral {

    @Id
    private Integer idRegimenLab;
    private String  desRegimenLab;

}
