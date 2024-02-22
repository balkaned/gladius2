package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.beans.ConceptoxProms;

import java.util.List;

public interface ConceptoXProcesoDao {
    List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto);

    public ConceptoXProceso recuperar(Integer idproceso, String idconcepto);

    public void eliminar(Integer idproceso, String idconcepto);

    public List<ConceptoxProms> listarPromCon(Integer idproceso, String idconcepto);

    public List<ConceptoXProceso> listar(Integer idproceso, String text);

    public void insertarProm(ConceptoxProms conxproms);

    public void eliminaProm(ConceptoxProms conxproms);
}
