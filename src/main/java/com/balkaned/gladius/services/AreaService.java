package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.beans.Empleado;
import java.util.List;

public interface AreaService {

    public List<Area> listarArea(Integer codcia, String text);
    public Area getArea(Integer codcia, String codarea);
}
