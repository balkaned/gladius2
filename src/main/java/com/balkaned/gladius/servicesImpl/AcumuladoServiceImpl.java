package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.EmpAcum;
import com.balkaned.gladius.dao.AcumuladoDao;
import com.balkaned.gladius.services.AcumuladoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AcumuladoServiceImpl implements AcumuladoService {

    @Autowired
    AcumuladoDao dao;

    public List<EmpAcum> listarEmpAcum(Integer codcia, Integer codtra){return dao.listarEmpAcum(codcia,codtra);}
    public void insertarEmpAcum(EmpAcum empacu){dao.insertarEmpAcum(empacu);}

}
