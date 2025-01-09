package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Ubigeo {

    @Id
    private String idpais;
    private String iddepartamento;
    private String desdepartamento;
    private String idprovincia;
    private String desprovincia;
    private String iddistrito;
    private String desdistrito;

}
