package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.FormulaPlanilla;

public interface FormulaService {
	FormulaPlanilla getByIdProcesoIdFormula(Integer idprod, Integer idformula);
}
