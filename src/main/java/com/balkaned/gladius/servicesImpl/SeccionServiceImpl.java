package com.balkaned.gladius.servicesImpl;


import com.balkaned.gladius.beans.Seccion;
import com.balkaned.gladius.dao.SeccionDao;
import com.balkaned.gladius.services.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SeccionServiceImpl implements SeccionService {

    @Autowired
    SeccionDao dao;

    public List<Seccion> listarSeccion(){return dao.listarSeccion();}
    public Integer getIdSeccion(){return dao.getIdSeccion();}
    public void insertarSeccion(Seccion seccion){dao.insertarSeccion(seccion);}
    public Seccion getSeccion(Integer codsec){return dao.getSeccion(codsec);}
    public void actualizarSeccion(Seccion seccion){dao.actualizarSeccion(seccion);}

}
