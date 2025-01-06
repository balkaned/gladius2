package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UsuxSys {

    @Id
    private Integer idCodCia;
    private Integer idCodUsu;
    private Integer idcodSys;
    private String desSystema;
    private String desRol;
    private Integer nroOrden;

}
