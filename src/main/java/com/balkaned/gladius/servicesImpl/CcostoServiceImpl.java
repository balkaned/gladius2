package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.CentroCosto;
import com.balkaned.gladius.dao.CcostoDao;
import com.balkaned.gladius.services.CcostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CcostoServiceImpl implements CcostoService {

    @Autowired
    CcostoDao dao;

    public List<CentroCosto> listarCentroCosto(Integer codcia, String text){
        return dao.listarCentroCosto(codcia,text);
    }
    public CentroCosto getCentroCosto(Integer codcia, String codccosto){
        return dao.getCentroCosto(codcia,codccosto);
    }

}
