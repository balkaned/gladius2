package com.balkaned.gladius.puesto.Infrastructure;

import com.balkaned.gladius.puesto.Domain.Puesto;

import java.util.List;

public interface PuestoDao {
    public List<Puesto> listarPuesto(Integer codcia, String text);

    public Puesto getPuesto(Integer codcia, String codarea);

    public Integer getIdPuesto(Integer codcia);

    public void insertarPuesto(Puesto puesto);

    public void actualizarPuesto(Puesto puesto);

    public void eliminarPuesto(Puesto puesto);
}
