package com.balkaned.gladius.usuarios.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Blob;

@Entity
@Data
public class Usuario {

    @Id
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
