package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.beans.FormulaPlanilla;
import com.balkaned.gladius.dao.FormulaPlanillaDao;
import com.balkaned.gladius.services.FormulaPlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FormulaPlanillaServiceImpl implements FormulaPlanillaService {

    @Autowired
    FormulaPlanillaDao dao;

    public List<FormulaPlanilla> listar(String text){
        return dao.listar(text);
    }

    public String getListVars(Integer idprod, String script){
        return dao.getListVars(idprod,script);
    }

    public List<ConceptoXProceso> obtenerListVariables_glb(Integer idprod, String script){
        return dao.obtenerListVariables_glb(idprod,script);
    }
    public Double realEjecucion(String v_script_dec, String v_script_ini , String v_script_body){
        return dao.realEjecucion(v_script_dec,v_script_ini,v_script_body);
    }

}
