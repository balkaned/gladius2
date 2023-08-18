package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.ProcesoPlanilla;
import com.balkaned.gladius.dao.ProcesoPlanillaDao;
import com.balkaned.gladius.services.ProcesoPlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProcesoPlanillaServiceImpl implements ProcesoPlanillaService {

    @Autowired
    ProcesoPlanillaDao dao;

    public List<ProcesoPlanilla> listar(String text){
        return dao.listar(text);
    }

}