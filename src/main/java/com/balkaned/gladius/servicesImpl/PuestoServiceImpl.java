package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Puesto;
import com.balkaned.gladius.dao.PuestoDao;
import com.balkaned.gladius.services.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PuestoServiceImpl implements PuestoService {

    @Autowired
    PuestoDao dao;

    public List<Puesto> listarPuesto(Integer codcia, String text){
        return dao.listarPuesto(codcia,text);
    }

    public Puesto getPuesto(Integer codcia, String codarea){
        return dao.getPuesto(codcia,codarea);
    }

}
