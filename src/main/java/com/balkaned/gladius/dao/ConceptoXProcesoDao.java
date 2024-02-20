package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.ConceptoXProceso;
import java.util.List;

public interface ConceptoXProcesoDao {
    List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto);

    public ConceptoXProceso recuperar(Integer idproceso, String idconcepto);

    public void eliminar(Integer idproceso, String idconcepto);
}
