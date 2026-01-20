package com.balkaned.gladius.lovs.Infrastructure;


import com.balkaned.gladius.area.Domain.Area;
import com.balkaned.gladius.concepto.Domain.Concepto;
import com.balkaned.gladius.empleado.Domain.Empleado;
import com.balkaned.gladius.locales.Domain.Local;
import com.balkaned.gladius.lovs.Domain.Lovs;
import com.balkaned.gladius.models.*;
import com.balkaned.gladius.puesto.Domain.Puesto;
import com.balkaned.gladius.vacaciones.Domain.VacacionControl;

import java.util.List;

public interface LovsDao {
    public List<Lovs> getLovs(String id_table , String text );
    public List<RegimenLaboral> getRegimenLab();
    public List<Concepto> getConceptoxProc(Integer id_proc);
    public List<Area> getAreaCia(Integer id_cia);
    public List<Puesto> getPuestoCia(Integer id_cia);
    public List<CentroCosto> getCCostoCia(Integer id_cia);
    public List<Local> getUbicacionCia(Integer id_cia);
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
    public List<VacacionControl> getSaldoVacTra(Integer codcia, Integer codtra , String pervac);
    public List<VacacionControl>  listaSaldoVacTra(Integer codcia, String regimen,  Integer codtra  );
    public List<Empleado>  listaTrabajadoresReg(Integer codcia, String regimen);

    List<Lovs> getLovsCContables();
}
