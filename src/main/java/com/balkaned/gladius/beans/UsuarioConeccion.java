package com.balkaned.gladius.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioConeccion {

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
