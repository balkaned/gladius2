package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.ConceptoxProcesoxTra;
import com.balkaned.gladius.beans.PlaProPeriodo;
import com.balkaned.gladius.dao.PlanillaDao;
import com.balkaned.gladius.services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanillaServiceImpl implements PlanillaService {

    @Autowired
    PlanillaDao dao;

    public List<PlaProPeriodo> listPla5ta(Integer codcia, String anio, Integer codtra) {
        return dao.listPla5ta(codcia, anio, codtra);
    }

    public List<ConceptoxProcesoxTra> listPlaProperDetCon(Integer codcia, Integer idproceso, String perpro, String codcon) {
        return dao.listPlaProperDetCon(codcia, idproceso, perpro, codcon);
    }

    public List<PlaProPeriodo> listAllPlaPerTra(Integer codcia, Integer codtra, String perini, String perfin) {
        return dao.listAllPlaPerTra(codcia, codtra, perini, perfin);
    }

    public List<PlaProPeriodo> listAllPlaPerTraPro(Integer codcia, Integer codtra, Integer codpro, String perini, String perfin) {
        return dao.listAllPlaPerTraPro(codcia, codtra, codpro, perfin, perfin);
    }

    public void PlameExe(Integer codcia, String permes , String file ) {

        dao.PlameExe(codcia, permes, file);
    }

    public List<String> PlameMes(Integer codcia, String permes , String file  ){
        return dao.PlameMes(codcia,permes, file);
    }

    public void AfpNetExe(Integer codcia, String permes  ){
        dao.AfpNetExe(codcia,permes);
    }

    public List<PlaProPeriodo> listPlaProper(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String txt){
        return dao.listPlaProper(codcia,idproceso,perpro,codtra,correl,txt);
    }

    public void iniPlaProper(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu){
        dao.iniPlaProper(codcia,idproceso,perpro,codtra,correl,grppla,usu);
    }

    public void calificacion_tiempo_mas(Integer codcia, Integer idproceso, String idPeriodo, Integer codtra, Integer correl){
        dao.calificacion_tiempo_mas(codcia,idproceso,idPeriodo,codtra,correl);
    }

    public void iniPlaProper_proc(Integer codcia,Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu){
        dao.iniPlaProper_proc(codcia,idproceso,perpro,codtra,correl,grppla,usu);
    }

}
