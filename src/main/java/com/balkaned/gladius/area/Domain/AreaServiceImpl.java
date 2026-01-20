package com.balkaned.gladius.area.Domain;

import com.balkaned.gladius.area.Infrastructure.AreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaDao dao;

    public List<Area> listarArea(Integer codcia, String text) {
        return dao.listarArea(codcia, text);
    }

    public Area getArea(Integer codcia, String codarea) {
        return dao.getArea(codcia, codarea);
    }

    public Integer getIdArea(Integer codcia) {
        return dao.getIdArea(codcia);
    }

    public void insertarArea(Area area) {
        dao.insertarArea(area);
    }

    public void actualizarArea(Area area) {
        dao.actualizarArea(area);
    }

    public void eliminarArea(Area area) {
        dao.eliminarArea(area);
    }

}
