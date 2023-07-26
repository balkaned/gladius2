package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.*;
import java.util.List;

public interface LovsDao {
    public List<Lovs> getLovs(String id_table , String text );
    public List<RegimenLaboral> getRegimenLab();
    public List<Concepto> getConceptoxProc( Integer id_proc);
    public List<Area> getAreaCia(Integer id_cia);
    public List<Puesto> getPuestoCia(Integer id_cia);
    public List<CentroCosto> getCCostoCia(Integer id_cia);
    public List<Local> getUbicacionCia( Integer id_cia);
    public List<Ubigeo> getUbigeo( String text_buscar);
    public List<Lovs> getRegimenProc();
    public List<ProcesoPlanilla> getProxRegimen( String regimen);
    public List<ProcesoPeriodo> getPerxproc( Integer codcia, String proceso);
    public List<Lovs> getRegimenProcGrppla(String Grppla);
    public List<ProcesoPlanilla> getProxRegimenGrppla( String regimen , String grppla);
    public List<Concepto> getConceptoLov();
    public List<Lovs> getLovsDept( String id_table, String id_pais);
    public List<Lovs> getLovsProv( String id_table, String id_dept);
    public List<Lovs> getLovsDist( String id_table, String id_prov);
}
