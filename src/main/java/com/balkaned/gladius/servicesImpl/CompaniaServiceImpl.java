package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Ciaxcon;
import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.dao.CompaniaDao;
import com.balkaned.gladius.dao.UsuarioConeccionDao;
import com.balkaned.gladius.services.CompaniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniaServiceImpl implements CompaniaService {

    @Autowired
    CompaniaDao dao;

    @Override
    public Compania getCompaniaAll(Integer id) {
        return dao.getCompaniaAll(id);
    }

    public void logoCompania(Compania com) {
        dao.logoCompania(com);
    }

    public List<Compania> listarTodo() {
        return dao.listarTodo();
    }

    public void insertarCompania(Compania com) {
        dao.insertarCompania(com);
    }

    public Compania getCompania(Integer codcia) {
        return dao.getCompania(codcia);
    }

    public List<Ciaxcon> listarCiaxcon(Integer codcia, String flgtipreg) {
        return dao.listarCiaxcon(codcia, flgtipreg);
    }

    public void actualizarCompania(Compania com) {
        dao.actualizarCompania(com);
    }

    public void insertarCiaxcion(Integer codcia, String codcon, String tipreg) {
        dao.insertarCiaxcon(codcia, codcon, tipreg);
    }

    public void deleteCiaxcon(Integer codcia, String codcon) {
        dao.deleteCiaxcon(codcia, codcon);
    }
}
