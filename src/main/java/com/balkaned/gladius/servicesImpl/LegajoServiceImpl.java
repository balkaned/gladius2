package com.balkaned.gladius.servicesImpl;


import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.Grpfile;
import com.balkaned.gladius.beans.RetencionJudicial;
import com.balkaned.gladius.dao.LegajoDao;
import com.balkaned.gladius.dao.RetJudicialDao;
import com.balkaned.gladius.services.LegajoService;
import com.balkaned.gladius.services.RetJudicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegajoServiceImpl implements LegajoService {

    @Autowired
    LegajoDao dao;
    public List<Grpfile> listarGrpfile(Integer codcia, Integer codtra, String grpfile){return dao.listarGrpfile(codcia,codtra,grpfile);}
    public Integer obtieneIdGrpFile(Grpfile grpfile){return dao.obtieneIdGrpFile(grpfile);}
    public void insertarGrpFile(Grpfile grpfile){dao.insertarGrpFile(grpfile);}

}