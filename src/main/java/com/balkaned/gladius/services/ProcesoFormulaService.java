package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.*;

import java.util.List;

public interface ProcesoFormulaService {
    public List<ProcesoForm> listProcesoFormula();

    public List<FormulaXConcepto> listFormulaXConcepto();

    public List<Proceso> listProceso(String id);

    public List<ConceptoXProceso> listConceptoXProceso(Integer idproceso, String tipcon);

    public ConceptoXProceso getConceptoXProceso(Integer idproceso, String idconcepto);

    public void insertar(ConceptoXProceso cproceso);

    public void actualizar(ConceptoXProceso cproceso);

    public void insertarProcesoFormula(ProcesoForm proFo);

    public void eliminarProcesoFormula(Integer id);

    public ProcesoPlanilla recuperar(Integer id);

    public void actualizar(ProcesoPlanilla pplanilla);
}
