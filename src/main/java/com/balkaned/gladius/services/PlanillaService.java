package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.ConceptoxProcesoxTra;
import com.balkaned.gladius.beans.PlaProPeriodo;

import java.util.List;

public interface PlanillaService {
    public List<PlaProPeriodo> listPla5ta(Integer codcia, String anio, Integer codtra);

    public List<ConceptoxProcesoxTra> listPlaProperDetCon(Integer codcia, Integer idproceso, String perpro, String codcon);

    public List<PlaProPeriodo> listAllPlaPerTra(Integer codcia, Integer codtra, String perini, String perfin);

    public List<PlaProPeriodo> listAllPlaPerTraPro(Integer codcia, Integer codtra, Integer codpro, String perini, String perfin);

    public void PlameExe(Integer codcia, String permes , String file ) ;
    public List<String> PlameMes(Integer codcia, String permes , String file  );
    public void AfpNetExe(Integer codcia, String permes  );
}

