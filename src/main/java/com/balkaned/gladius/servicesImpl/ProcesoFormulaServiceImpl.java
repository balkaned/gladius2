package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.*;
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
    public List<ConceptoXProceso> listConceptoXProceso(Integer idproceso, String tipcon) {
        return dao.listConceptoXProceso(idproceso, tipcon);
    }

    @Override
    public ConceptoXProceso getConceptoXProceso(Integer idproceso, String idconcepto) {
        return dao.getConceptoXProceso(idproceso, idconcepto);
    }

    @Override
    public void insertarConceptoXProceso(ConceptoXProceso cxp) {

        dao.insertarConceptoXProceso(cxp);
    }

    @Override
    public void editarConceptoXProceso(ConceptoXProceso cxp) {

        dao.editarConceptoXProceso(cxp);
    }

    @Override
    public void insertarProcesoFormula(ProcesoForm proFo) {

        dao.insertarProcesoFormula(proFo);
    }

    @Override
    public void eliminarProcesoFormula(Integer id) {

        dao.eliminarProcesoFormula(id);
    }

    public ProcesoPlanilla recuperar(Integer id) {
        return dao.recuperar(id);
    }

    public void actualizar(ProcesoPlanilla pplanilla) {
        dao.actualizar(pplanilla);

    }
}
