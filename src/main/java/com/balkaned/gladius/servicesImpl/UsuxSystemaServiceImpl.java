package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.UsuxSys;
import com.balkaned.gladius.dao.UsuxSystemaDao;
import com.balkaned.gladius.services.UsuxSystemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuxSystemaServiceImpl implements UsuxSystemaService {

    @Autowired
    UsuxSystemaDao dao;

    public UsuxSys eligeSystema(Integer codcia, Integer codusu, Integer sys){
        return dao.eligeSystema(codcia,codusu,sys);
    }


}
