package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.FormulaPlanilla;

public interface FormulaDao {
    public FormulaPlanilla getByIdProcesoIdFormula(Integer idprod, Integer idformula);

    public void actualizar(FormulaPlanilla fplanilla);

    public void insertar(FormulaPlanilla fplanilla);

    public void eliminar(Integer idprod, Integer idfor);

    public FormulaPlanilla recuperar(Integer idprod, Integer idformula);

    public void grabaVariableResultado(Integer idprod, Integer idformula, String Variable, String resultado);
}
