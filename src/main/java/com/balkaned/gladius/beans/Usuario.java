package com.balkaned.gladius.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Blob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Integer idUsuario;
    private String usuario;
    private String password;
    private Integer idUsuarioCrea;
    private String desUsuarioCrea;
    private Integer idUsuarioMod;
    private String desUsuarioMod;
    private String fechaCrea;
    private String fechaModfica;
    private String estado;
    private Integer idUsuMat;
    private String email;
    private String urlfoto;
    private Blob FotoUser;
    private byte[] foto;
    private Integer idCia;
    private Integer idRol;

}
