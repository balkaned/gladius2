package com.balkaned.gladius.area.Infrastructure;

import com.balkaned.gladius.area.Domain.Area;

import java.util.List;

public interface AreaDao {
    public List<Area> listarArea(Integer codcia, String text);

    public Area getArea(Integer codcia, String codarea);

    public Integer getIdArea(Integer codcia);

    public void insertarArea(Area area);

    public void actualizarArea(Area area);

    public void eliminarArea(Area area);

}
