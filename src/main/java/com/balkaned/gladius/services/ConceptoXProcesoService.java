package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.beans.ConceptoxAgrup;
import com.balkaned.gladius.beans.ConceptoxProms;

import java.util.List;

public interface ConceptoXProcesoService {
    List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto);

    public ConceptoXProceso recuperar(Integer idproceso, String idconcepto);

    public void eliminar(Integer idproceso, String idconcepto);

    public List<ConceptoxProms> listarPromCon(Integer idproceso, String idconcepto);

    public List<ConceptoXProceso> listar(Integer idproceso, String text);

    public void insertarProm(ConceptoxProms conxproms);

    public void eliminaProm(ConceptoxProms conxproms);

    public List<ConceptoxAgrup> listarAgrupCon(Integer idproceso, String idconcepto);

    public void insertarAgrup(ConceptoxAgrup conxagrup);

    public void eliminaAgrup(ConceptoxAgrup conxagrup);
}
