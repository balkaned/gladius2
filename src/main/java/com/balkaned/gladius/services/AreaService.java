package com.balkaned.gladius.services;

import com.balkaned.gladius.models.Area;

import java.util.List;

public interface AreaService {

    List<Area> listarArea(Integer codcia, String text);

    public Area getArea(Integer codcia, String codarea);

    public Integer getIdArea(Integer codcia);

    public void insertarArea(Area area);

    public void actualizarArea(Area area);

    public void eliminarArea(Area area);

}
