package com.balkaned.gladius.secciones.Domain;


import com.balkaned.gladius.secciones.Infrastructure.SeccionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SeccionServiceImpl implements SeccionService {

    @Autowired
    SeccionDao dao;

    public List<Seccion> listarSeccion() {
        return dao.listarSeccion();
    }

    public Integer getIdSeccion() {
        return dao.getIdSeccion();
    }

    public void insertarSeccion(Seccion seccion) {
        dao.insertarSeccion(seccion);
    }

    public Seccion getSeccion(Integer codsec) {
        return dao.getSeccion(codsec);
    }

    public void actualizarSeccion(Seccion seccion) {
        dao.actualizarSeccion(seccion);
    }

    public void eliminarSeccion(Seccion seccion) {
        dao.eliminarSeccion(seccion);
    }

}
