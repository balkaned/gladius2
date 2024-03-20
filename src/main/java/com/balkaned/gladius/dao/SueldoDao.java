package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.beans.EmpDatvar;
import com.balkaned.gladius.beans.EmpSueldo;
import com.balkaned.gladius.beans.Empleado;
import java.util.List;

public interface SueldoDao {
    public List<EmpSueldo> obtenerEmpSueldo(Empleado empleado);
    public List<Concepto> ListConceptos(Integer codcia, String Tipo);
    public void insertarEmpSueldo(EmpSueldo empsueldo);
    public List<EmpDatvar> obtenerEmpDatvar(Integer cia, Integer codpro, String nroper, Integer codtra, Integer correl);
    public void insertarEmpDatvar(EmpDatvar empdatvar);
    public EmpSueldo obtenerOneEmpSueldo(Empleado empleado , String concepto);
    public void actualizarEmpSueldo(EmpSueldo empsueldo);
    public void eliminarEmpSueldo(EmpSueldo empsueldo);
    public EmpDatvar obtenerOneEmpDatvar(Integer cia, Integer codpro, String nroper, Integer codtra, Integer correl, String concepto);
    public void actualizarEmpDatvar(EmpDatvar empdatvar);
    public void eliminarEmpDatvar(EmpDatvar empdatvar);
    public List<Concepto> ListConcepProVar(Integer codcia, Integer codpro, String Tipo);
    public List<EmpDatvar> obtenerEmpResvar(Integer cia, Integer codpro, String nroper, Integer correl);
    public void eliminarAllDatvar(Integer cia, Integer codpro, String nroper, Integer correl);
    public void insertarDatvarmas(List<EmpDatvar> empdatvar);
    public void eliminarAllDatvarEmp(Integer cia, Integer codpro, String nroper, Integer correl, Integer codtra, String concepto);

}
