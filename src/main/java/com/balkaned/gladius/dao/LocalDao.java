package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Local;
import java.util.List;

public interface LocalDao {
    public List<Local> listarLocales(Integer codcia, String text);
    public Local getLocales(Integer codcia, String codarea);
    public Integer getIdUbicaion(Integer codcia);
    public void insertarUbicacion(Local ubic);
}
