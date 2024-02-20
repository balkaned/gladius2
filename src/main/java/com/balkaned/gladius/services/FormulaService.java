package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.FormulaPlanilla;

public interface FormulaService {
    public FormulaPlanilla getByIdProcesoIdFormula(Integer idprod, Integer idformula);

    public void actualizar(FormulaPlanilla fplanilla);

    public void insertar(FormulaPlanilla fplanilla);

    public void eliminar(Integer idprod, Integer idfor);

    public FormulaPlanilla recuperar(Integer idprod, Integer idformula);

    public String obtenerVariables(String script);

    public String testEjecucion(String script);

    public void grabaVariableResultado(Integer idprod, Integer idformula, String Variable, String resultado);


}
