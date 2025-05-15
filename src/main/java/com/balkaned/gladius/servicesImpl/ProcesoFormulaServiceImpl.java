package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.models.*;
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
    public List<FormulaXConcepto> listFormulaXConcepto(String codpro) {
        return dao.listFormulaXConcepto(codpro);
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

    public void insertar(ConceptoXProceso cproceso){
        dao.insertar(cproceso);
    }

    public void actualizar(ConceptoXProceso cproceso){
        dao.actualizar(cproceso);
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
