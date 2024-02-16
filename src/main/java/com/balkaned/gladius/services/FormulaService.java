package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.FormulaPlanilla;

public interface FormulaService {
	public FormulaPlanilla getByIdProcesoIdFormula(Integer idprod, Integer idformula);

	public void actualizar(FormulaPlanilla fplanilla);
}
