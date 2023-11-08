package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.FormulaXConcepto;
import com.balkaned.gladius.beans.Proceso;
import com.balkaned.gladius.beans.ProcesoForm;

import java.util.List;

public interface ProcesoFormulaDao {
    List<ProcesoForm> listProcesoFormula();

    List<FormulaXConcepto> listFormulaXConcepto();

    List<Proceso> listConcepto(String id);
}
