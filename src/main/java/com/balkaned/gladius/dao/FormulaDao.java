package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.FormulaPlanilla;

public interface FormulaDao {
	FormulaPlanilla getByIdProcesoIdFormula(Integer idprod, Integer idformula);
}
