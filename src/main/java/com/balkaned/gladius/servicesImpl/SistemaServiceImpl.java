package com.balkaned.gladius.servicesImpl;


import com.balkaned.gladius.beans.Sistemas;
import com.balkaned.gladius.dao.SistemaDao;
import com.balkaned.gladius.services.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SistemaServiceImpl implements SistemaService {

    @Autowired
    SistemaDao dao;

    public List<Sistemas> listarSistemas() {
        return dao.listarSistemas();
    }

    public void insertarSistemas(Sistemas sistemas) {
        dao.insertarSistemas(sistemas);
    }

    public Sistemas getSistemas(Integer codsis) {
        return dao.getSistemas(codsis);
    }

    public void actualizarSistemas(Sistemas systema) {
        dao.actualizarSistemas(systema);
    }

    public void eliminarSistemas(Sistemas systema) {
        dao.eliminarSistemas(systema);
    }

}
