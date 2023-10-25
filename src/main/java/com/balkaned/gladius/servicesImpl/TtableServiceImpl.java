package com.balkaned.gladius.servicesImpl;


import com.balkaned.gladius.beans.TTablaCabecera;
import com.balkaned.gladius.dao.TtableDao;
import com.balkaned.gladius.services.TtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TtableServiceImpl implements TtableService {

    @Autowired
    TtableDao dao;

    public List<TTablaCabecera> listarTTablac(String text) {
        return dao.listarTTablac(text);
    }

    public void insertarTtablac(TTablaCabecera ttc) {
        dao.insertarTtablac(ttc);
    }

}
