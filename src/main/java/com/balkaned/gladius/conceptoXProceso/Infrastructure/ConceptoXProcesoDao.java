package com.balkaned.gladius.conceptoXProceso.Infrastructure;

import com.balkaned.gladius.conceptoXProceso.Domain.ConceptoXProceso;
import com.balkaned.gladius.conceptoXProceso.Domain.ConceptoxAgrup;
import com.balkaned.gladius.conceptoXProceso.Domain.ConceptoxProms;

import java.util.List;

public interface ConceptoXProcesoDao {
    public List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto);

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
