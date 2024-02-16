package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.FormulaPlanilla;

public interface FormulaDao {
	public FormulaPlanilla getByIdProcesoIdFormula(Integer idprod, Integer idformula);

	public void actualizar(FormulaPlanilla fplanilla);
}
