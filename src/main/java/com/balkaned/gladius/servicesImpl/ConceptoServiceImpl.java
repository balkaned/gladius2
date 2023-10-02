package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.dao.ConceptoDao;
import com.balkaned.gladius.services.ConceptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConceptoServiceImpl implements ConceptoService {

    @Autowired
    ConceptoDao dao;

    public List<Concepto> listardet(){return dao.listardet();}

}