package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.ConceptoxProcesoxTra;
import com.balkaned.gladius.beans.PlaProPeriodo;
import com.balkaned.gladius.dao.PlanillaDAO;
import com.balkaned.gladius.services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanillaServiceImpl implements PlanillaService {

    @Autowired
    PlanillaDAO dao;

    public List<PlaProPeriodo> listPla5ta(Integer codcia, String anio, Integer codtra) {
        return dao.listPla5ta(codcia, anio, codtra);
    }


    public List<ConceptoxProcesoxTra> listPlaProperDetCon(Integer codcia, Integer idproceso, String perpro, String codcon) {
        return dao.listPlaProperDetCon(codcia, idproceso, perpro, codcon);
    }

    public List<PlaProPeriodo> listAllPlaPerTra(Integer codcia, Integer codtra, String perini, String perfin) {
        return dao.listAllPlaPerTra(codcia, codtra, perini, perfin);
    }

    public List<PlaProPeriodo> listAllPlaPerTraPro(Integer codcia, Integer codtra, Integer codpro, String perini, String perfin) {
        return dao.listAllPlaPerTraPro(codcia, codtra, codpro, perfin, perfin);
    }

}
