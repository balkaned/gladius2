package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.beans.EmpSueldo;
import com.balkaned.gladius.beans.Empleado;
import java.util.List;

public interface SueldoDao {
    public List<EmpSueldo> obtenerEmpSueldo(Empleado empleado);
    public List<Concepto> ListConceptos(Integer codcia, String Tipo);
    public void insertarEmpSueldo(EmpSueldo empsueldo);

}