package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UsuarioConeccion {

    @Id
    private String id_usuario;
    private String user;
    private String pass;
    private String descripcion;
    private String email;
    private String codCia;
    private String desCia;
    private String sourceDes;
    private String ruccia;

}
