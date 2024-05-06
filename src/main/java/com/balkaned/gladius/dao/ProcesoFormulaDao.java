package com.balkaned.gladius.dao;

import com.balkaned.gladius.models.*;

import java.util.List;

public interface ProcesoFormulaDao {
    public List<ProcesoForm> listProcesoFormula();

    public List<FormulaXConcepto> listFormulaXConcepto();

    public List<Proceso> listConcepto(String id);

    public List<ConceptoXProceso> listConceptoXProceso(Integer idproceso, String tipcon);

    public ConceptoXProceso getConceptoXProceso(Integer idproceso, String idconcepto);

    public void insertar(ConceptoXProceso cproceso);

    public void actualizar(ConceptoXProceso cproceso);

    public void insertarProcesoFormula(ProcesoForm proFo);

    public void eliminarProcesoFormula(Integer id);

    public ProcesoPlanilla recuperar(Integer id);

    public void actualizar(ProcesoPlanilla pplanilla);
}
