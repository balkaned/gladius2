package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.EmpAcum;
import com.balkaned.gladius.dao.EmpAcumDao;
import com.balkaned.gladius.services.EmpAcumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpAcumServiceImpl implements EmpAcumService {

    @Autowired
    EmpAcumDao dao;

    public EmpAcum getEmpAcum(Integer codcia, Integer codtra, String anio) {
        return dao.getEmpAcum(codcia, codtra, anio);
    }

}
