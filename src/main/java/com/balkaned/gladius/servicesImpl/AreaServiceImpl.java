package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.dao.AreaDao;
import com.balkaned.gladius.dao.EmpleadoDao;
import com.balkaned.gladius.services.AreaService;
import com.balkaned.gladius.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaDao dao;

    public List<Area> listarArea(Integer codcia, String text){
        return dao.listarArea(codcia,text);
    }
    public Area getArea(Integer codcia, String codarea){
        return dao.getArea(codcia,codarea);
    }

}
