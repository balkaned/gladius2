package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.beans.FormulaXConcepto;
import com.balkaned.gladius.beans.Proceso;
import com.balkaned.gladius.beans.ProcesoForm;
import com.balkaned.gladius.dao.ProcesoFormulaDao;
import com.balkaned.gladius.services.ProcesoFormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesoFormulaServiceImpl implements ProcesoFormulaService {
    @Autowired
    ProcesoFormulaDao dao;

    @Override
    public List<ProcesoForm> listProcesoFormula() {
        return dao.listProcesoFormula();
    }

    @Override
    public List<FormulaXConcepto> listFormulaXConcepto() {
        return dao.listFormulaXConcepto();
    }

    @Override
    public List<Proceso> listProceso(String id) {
        return dao.listConcepto(id);
    }

    @Override
    public List<ConceptoXProceso> listConceptoXProceso(String id) {
        return dao.listConceptoXProceso(id);
    }

    @Override
    public void insertarProcesoFormula(ProcesoForm proFo) {
        dao.insertarProcesoFormula(proFo);
    }
}
