package com.balkaned.gladius.locales.Domain;

import java.util.List;

public interface LocalService {

    public List<Local> listarLocales(Integer codcia, String text);

    public Local getLocales(Integer codcia, String codubicacion);

    public Integer getIdUbicaion(Integer codcia);

    public void insertarUbicacion(Local ubic);

    public void actualizarUbicaion(Local ubic);

    public void eliminarUbicacion(Local ubic);

}
