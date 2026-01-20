package com.balkaned.gladius.opciones.Domain;


import com.balkaned.gladius.opciones.Infrastructure.OpcionDao;
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
