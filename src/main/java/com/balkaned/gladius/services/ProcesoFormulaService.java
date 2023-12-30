package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.beans.FormulaXConcepto;
import com.balkaned.gladius.beans.Proceso;
import com.balkaned.gladius.beans.ProcesoForm;

import java.util.List;

public interface ProcesoFormulaService {
	List<ProcesoForm> listProcesoFormula();
	List<FormulaXConcepto> listFormulaXConcepto();
	List<Proceso> listProceso(String id);
  List<ConceptoXProceso> listConceptoXProceso(String id);
	void insertarProcesoFormula(ProcesoForm proFo);
	void eliminarProcesoFormula(Integer id);
}
