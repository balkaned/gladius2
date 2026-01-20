package com.balkaned.gladius.locales.Infrastructure;

import com.balkaned.gladius.locales.Domain.Local;

import java.util.List;

public interface LocalDao {
    public List<Local> listarLocales(Integer codcia, String text);

    public Local getLocales(Integer codcia, String codarea);

    public Integer getIdUbicaion(Integer codcia);

    public void insertarUbicacion(Local ubic);

    public void actualizarUbicaion(Local ubic);

    public void eliminarUbicacion(Local ubic);
}
