package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Area;
import java.util.List;

public interface AreaDao {
    public List<Area> listarArea(Integer codcia, String text);
    public Area getArea(Integer codcia, String codarea);
    public Integer getIdArea(Integer codcia);

}
