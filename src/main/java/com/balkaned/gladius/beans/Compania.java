package com.balkaned.gladius.beans;

public class Compania {
    private String id_companias;
    private String nombre;
    private String ruc;
    private String direccion;
    private String rol;

    private String id_usuario;

    public Compania() {

    }

    public Compania(String id_companias, String nombre, String ruc, String direccion, String rol) {
        this.id_companias = id_companias;
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.rol = rol;
    }

    public String getId_companias() {
        return id_companias;
    }

    public void setId_companias(String id_companias) {
        this.id_companias = id_companias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
}
