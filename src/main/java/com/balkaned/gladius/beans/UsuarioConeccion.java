package com.balkaned.gladius.beans;

public class UsuarioConeccion {

    private String id_usuario;
    private String user;
    private String pass;
    private String descripcion;
    private String email;
    private String codCia;
    private String desCia;
    private String sourceDes;

    public UsuarioConeccion(String id_usuario, String user, String pass, String descripcion, String email, String codCia, String desCia, String sourceDes) {
        this.id_usuario = id_usuario;
        this.user = user;
        this.pass = pass;
        this.descripcion = descripcion;
        this.email = email;
        this.codCia = codCia;
        this.desCia = desCia;
        this.sourceDes = sourceDes;
    }

    public UsuarioConeccion() {

    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodCia() {
        return codCia;
    }

    public void setCodCia(String codCia) {
        this.codCia = codCia;
    }

    public String getDesCia() {
        return desCia;
    }

    public void setDesCia(String desCia) {
        this.desCia = desCia;
    }

    public String getSourceDes() {
        return sourceDes;
    }

    public void setSourceDes(String sourceDes) {
        this.sourceDes = sourceDes;
    }
}
