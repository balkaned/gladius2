package com.balkaned.gladius.parametros.Domain;

import com.balkaned.gladius.parametros.Infrastructure.ParametroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    ParametroDao dao;

    public List<ParametrosGen> listarParametrosGen() {
        return dao.listarParametrosGen();
    }

    public void insertarParametrosGen(ParametrosGen par) {
        dao.insertarParametrosGen(par);
    }

    public ParametrosGen getParametrosGen(String codcon) {
        return dao.getParametrosGen(codcon);
    }

    public void actualizarParametrosGen(ParametrosGen par) {
        dao.actualizarParametrosGen(par);
    }

    public void eliminarParametrosGen(ParametrosGen par){
        dao.eliminarParametrosGen(par);
    }

}
