package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.*;

import java.util.List;

public interface ProcesoFormulaService {
    List<ProcesoForm> listProcesoFormula();

    List<FormulaXConcepto> listFormulaXConcepto();

    List<Proceso> listProceso(String id);

    List<ConceptoXProceso> listConceptoXProceso(Integer idproceso, String tipcon);

    ConceptoXProceso getConceptoXProceso(Integer idproceso, String idconcepto);

    void insertarConceptoXProceso(ConceptoXProceso cxp);

    void editarConceptoXProceso(ConceptoXProceso cxp);

    void insertarProcesoFormula(ProcesoForm proFo);

    void eliminarProcesoFormula(Integer id);

    public ProcesoPlanilla recuperar(Integer id);

    public void actualizar(ProcesoPlanilla pplanilla);
}
