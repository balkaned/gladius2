package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.ProcesoForm;
import com.balkaned.gladius.dao.ProFoDao;
import com.balkaned.gladius.services.ProFoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProFoServiceImpl implements ProFoService {
    @Autowired
    ProFoDao dao;

    @Override
    public List<ProcesoForm> listProFos() {
        return dao.listProFos();
    }
}
