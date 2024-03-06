package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.beans.FormulaPlanilla;

import java.util.List;

public interface FormulaPlanillaService {
    public List<FormulaPlanilla> listar(String text);
    public String getListVars(Integer idprod, String script);
    public List<ConceptoXProceso> obtenerListVariables_glb(Integer idprod, String script);;
    public Double realEjecucion(String v_script_dec, String v_script_ini , String v_script_body);

}
