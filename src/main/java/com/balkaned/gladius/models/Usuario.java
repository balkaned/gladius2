package com.balkaned.gladius.models;

import lombok.*;

import java.sql.Blob;

@Data
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
