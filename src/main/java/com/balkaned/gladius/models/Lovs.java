package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Lovs {

    @Id
    private String idLov;
    private String desLov;

}
