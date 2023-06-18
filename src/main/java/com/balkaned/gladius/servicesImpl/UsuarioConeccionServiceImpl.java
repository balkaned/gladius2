package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.dao.UsuarioConeccionDao;
import com.balkaned.gladius.services.UsuarioConeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioConeccionServiceImpl implements UsuarioConeccionService {

    @Autowired
    UsuarioConeccionDao dao;

    public UsuarioConeccion obtenerUsuarioConeccionById(String id) {
        return dao.obtenerUsuarioConeccionById(id);
    }

    @Override
    public UsuarioConeccion obtenerUsuarioConeccionByName(UsuarioConeccion uc) {
        return dao.obtenerUsuarioConeccionByName(uc);
    }
}

