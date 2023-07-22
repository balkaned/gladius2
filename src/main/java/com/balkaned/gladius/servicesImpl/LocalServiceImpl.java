package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Local;
import com.balkaned.gladius.dao.LocalDao;
import com.balkaned.gladius.services.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocalServiceImpl implements LocalService {
    @Autowired
    LocalDao dao;

    public List<Local> listarLocales(Integer codcia, String text){
        return dao.listarLocales(codcia,text);
    }
    public Local getLocales(Integer codcia, String codarea){
        return dao.getLocales(codcia,codarea);
    }

}
