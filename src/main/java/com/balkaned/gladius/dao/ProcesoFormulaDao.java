package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.beans.FormulaXConcepto;
import com.balkaned.gladius.beans.Proceso;
import com.balkaned.gladius.beans.ProcesoForm;

import java.util.List;

public interface ProcesoFormulaDao {
    List<ProcesoForm> listProcesoFormula();
    List<FormulaXConcepto> listFormulaXConcepto();
    List<Proceso> listConcepto(String id);
    List<ConceptoXProceso> listConceptoXProceso(String id);
    ConceptoXProceso getConceptoXProceso(Integer idproceso, String idconcepto);
    void editarConceptoXProceso(ConceptoXProceso cxp);
    void insertarProcesoFormula(ProcesoForm proFo);
    void eliminarProcesoFormula(Integer id);
}
