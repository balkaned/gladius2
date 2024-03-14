package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.beans.EmpDatvar;
import com.balkaned.gladius.beans.EmpSueldo;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.dao.SueldoDao;
import com.balkaned.gladius.services.SueldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SueldoServiceImpl implements SueldoService {
    @Autowired
    SueldoDao dao;

    public List<EmpSueldo> obtenerEmpSueldo(Empleado empleado) {

        return dao.obtenerEmpSueldo(empleado);
    }

    public List<Concepto> ListConceptos(Integer codcia, String Tipo) {

        return dao.ListConceptos(codcia, Tipo);
    }

    public void insertarEmpSueldo(EmpSueldo empsueldo) {

        dao.insertarEmpSueldo(empsueldo);
    }

    public List<EmpDatvar> obtenerEmpDatvar(Integer cia, Integer codpro, String nroper, Integer codtra, Integer correl) {
        return dao.obtenerEmpDatvar(cia, codpro, nroper, codtra, correl);
    }

    public void insertarEmpDatvar(EmpDatvar empdatvar) {

        dao.insertarEmpDatvar(empdatvar);
    }

    public EmpSueldo obtenerOneEmpSueldo(Empleado empleado , String concepto){
        return dao.obtenerOneEmpSueldo(empleado,concepto);
    }

    public void actualizarEmpSueldo(EmpSueldo empsueldo){

        dao.actualizarEmpSueldo(empsueldo);
    }

    public void eliminarEmpSueldo(EmpSueldo empsueldo){

        dao.eliminarEmpSueldo(empsueldo);
    }

    public EmpDatvar obtenerOneEmpDatvar(Integer cia, Integer codpro, String nroper, Integer codtra, Integer correl, String concepto){
        return dao.obtenerOneEmpDatvar(cia,codpro,nroper,codtra,correl,concepto);
    }

    public void actualizarEmpDatvar(EmpDatvar empdatvar){

        dao.actualizarEmpDatvar(empdatvar);
    }

    public void eliminarEmpDatvar(EmpDatvar empdatvar){

        dao.eliminarEmpDatvar(empdatvar);
    }

    public List<Concepto> ListConcepProVar(Integer codcia, Integer codpro, String Tipo){
        return dao.ListConcepProVar(codcia,codpro,Tipo);
    }

    public List<EmpDatvar> obtenerEmpResvar(Integer cia, Integer codpro, String nroper, Integer correl){
        return dao.obtenerEmpResvar(cia,codpro,nroper,correl);
    }

    public void eliminarAllDatvar(Integer cia, Integer codpro, String nroper, Integer correl){
        dao.eliminarAllDatvar(cia,codpro,nroper,correl);
    }

    public void insertarDatvarmas(List<EmpDatvar> empdatvar){
        dao.insertarDatvarmas(empdatvar);
    }

}
