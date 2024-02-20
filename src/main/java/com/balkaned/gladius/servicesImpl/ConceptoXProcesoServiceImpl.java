package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.dao.ConceptoXProcesoDao;
import com.balkaned.gladius.services.ConceptoXProcesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConceptoXProcesoServiceImpl implements ConceptoXProcesoService {
    @Autowired
    ConceptoXProcesoDao dao;

    @Override
    public List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto) {
        return dao.listarTipconCtb(xcodcia, idProceso, slc_grpconcepto);
    }

    public ConceptoXProceso recuperar(Integer idproceso, String idconcepto){
        return dao.recuperar(idproceso,idconcepto);
    }

    public void eliminar(Integer idproceso, String idconcepto){
        dao.eliminar(idproceso,idconcepto);
    }
}
