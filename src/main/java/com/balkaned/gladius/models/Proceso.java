package com.balkaned.gladius.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Proceso {

    @Id
    private String procodcon;
    private String coodescon;

}
