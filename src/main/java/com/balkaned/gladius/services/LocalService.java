package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.Local;

import java.util.List;

public interface LocalService {

    public List<Local> listarLocales(Integer codcia, String text);
    public com.balkaned.gladius.beans.Local getLocales(Integer codcia, String codubicacion);
    public Integer getIdUbicaion(Integer codcia);
    public void insertarUbicacion(Local ubic);

}
