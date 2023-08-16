package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Puesto;
import java.util.List;

public interface PuestoDao {
    public List<Puesto> listarPuesto(Integer codcia, String text);
    public Puesto getPuesto(Integer codcia, String codarea);
    public Integer getIdPuesto(Integer codcia);
    public void insertarPuesto(Puesto puesto);
}
