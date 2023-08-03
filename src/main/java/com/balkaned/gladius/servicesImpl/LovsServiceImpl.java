package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.LovsDao;
import com.balkaned.gladius.services.LovsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LovsServiceImpl implements LovsService {

    @Autowired
    LovsDao dao;

    public List<Lovs> getLovs(String id_table , String text ){
        return dao.getLovs(id_table,text);
    }
    public List<RegimenLaboral> getRegimenLab(){
        return dao.getRegimenLab();
    }
    public List<Concepto> getConceptoxProc(Integer id_proc){
        return dao.getConceptoxProc(id_proc);
    }
    public List<Area> getAreaCia(Integer id_cia){
        return dao.getAreaCia(id_cia);
    }
    public List<Puesto> getPuestoCia(Integer id_cia){
        return dao.getPuestoCia(id_cia);
    }
    public List<CentroCosto> getCCostoCia(Integer id_cia){
        return dao.getCCostoCia(id_cia);
    }
    public List<Local> getUbicacionCia( Integer id_cia){
        return dao.getUbicacionCia(id_cia);
    }
    public List<Ubigeo> getUbigeo( String text_buscar){
        return dao.getUbigeo(text_buscar);
    }
    public List<Lovs> getRegimenProc(){
        return dao.getRegimenProc();
    }
    public List<ProcesoPlanilla> getProxRegimen( String regimen){
        return dao.getProxRegimen(regimen);
    }
    public List<ProcesoPeriodo> getPerxproc( Integer codcia, String proceso){
        return dao.getPerxproc(codcia,proceso);
    }
    public List<Lovs> getRegimenProcGrppla(String Grppla){
        return dao.getRegimenProcGrppla(Grppla);
    }
    public List<ProcesoPlanilla> getProxRegimenGrppla( String regimen , String grppla){
        return dao.getProxRegimenGrppla(regimen,grppla);
    }
    public List<Concepto> getConceptoLov(){
        return dao.getConceptoLov();
    }
    public List<Lovs> getLovsDept( String id_table, String id_pais){
        return dao.getLovsDept(id_table,id_pais);
    }
    public List<Lovs> getLovsProv( String id_table, String id_dept){
        return dao.getLovsProv(id_table,id_dept);
    }
    public List<Lovs> getLovsDist( String id_table, String id_prov){
        return dao.getLovsDist(id_table,id_prov);
    }

}
