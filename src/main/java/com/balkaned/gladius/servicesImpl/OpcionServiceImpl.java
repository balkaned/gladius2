package com.balkaned.gladius.servicesImpl;


import com.balkaned.gladius.beans.Opciones;
import com.balkaned.gladius.dao.OpcionDao;
import com.balkaned.gladius.services.OpcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpcionServiceImpl implements OpcionService {

    @Autowired
    OpcionDao dao;

    public List<Opciones> listarOpciones() {
        return dao.listarOpciones();
    }

    public Integer getIdOpciones() {
        return dao.getIdOpciones();
    }

    public void insertarOpciones(Opciones opc) {
        dao.insertarOpciones(opc);
    }

    public Opciones getOpciones(Integer codopc) {
        return dao.getOpciones(codopc);
    }

    public void actualizarOpciones(Opciones opc) {
        dao.actualizarOpciones(opc);
    }

    public void eliminarOpciones(Opciones opc) {
        dao.eliminarOpciones(opc);
    }

}
