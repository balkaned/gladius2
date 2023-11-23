package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.Puesto;

import java.util.List;

public interface PuestoService {
    public List<Puesto> listarPuesto(Integer codcia, String text);

    public Puesto getPuesto(Integer codcia, String codpuesto);

    public Integer getIdPuesto(Integer codcia);

    public void insertarPuesto(Puesto puesto);

    public void actualizarPuesto(Puesto puesto);

}
